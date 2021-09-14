package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.adapters.AdapterRooms
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.callbacks.SearchListener
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.models.Room
import com.test.syntelatostestapp.tasks.ApiVM
import com.test.syntelatostestapp.utils.LifecycleTextChangeListener
import com.test.syntelatostestapp.utils.SystemUtils


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 */
internal class FragmentRooms : BaseFragment(), SearchListener {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding
    private lateinit var roomsAdapter: AdapterRooms
    private lateinit var apiVm: ApiVM

    /**
     * make API call with help of vm observer to fetch rooms list
     * */
    private fun fetchRooms() {
        if (checkInternet(dataViewBinding.root)) {
            dataViewBinding.loaderOn = true
            apiVm.fetchRooms()
            apiVm.getRoomsObserver().observe(viewLifecycleOwner, {
                dataViewBinding.loaderOn = false
                val rooms = it ?: ArrayList()
                populateRooms(rooms = rooms)
            })
        } else {
            showError(true, resources.getString(R.string.internet_unavailable), true)
        }
    }

    /**
     * show error message on view and hide list view
     * @errorOn errorOn boolean flag to show / hide error view
     * @param errorMessage message tobe displayed
     * @param hideSearch boolean flag to show/hide search view on the top used when error triggered from search
     * */
    private fun showError(errorOn: Boolean, errorMessage: String?, hideSearch: Boolean) {
        if (errorOn) {
            dataViewBinding.tvFragmentDataError.text = errorMessage
            dataViewBinding.tvFragmentDataError.contentDescription =
                StringBuilder().append(resources.getString(R.string.error)).append(" ").append(errorMessage)
            dataViewBinding.tvFragmentDataError.visibility = View.VISIBLE
            dataViewBinding.rvFragmentData.visibility = View.GONE
            if (hideSearch) {
                dataViewBinding.svFragmentData.visibility = View.GONE
            } else {
                dataViewBinding.svFragmentData.visibility = View.VISIBLE
            }
        } else {
            dataViewBinding.tvFragmentDataError.visibility = View.GONE
            dataViewBinding.svFragmentData.visibility = View.VISIBLE
            dataViewBinding.rvFragmentData.visibility = View.VISIBLE
        }
    }

    /**
     * show list view or error message based on rooms array size
     * */
    private fun populateRooms(rooms: List<Room>) {
        if (rooms.isNotEmpty()) {
            dataViewBinding.rvFragmentData.visibility = View.VISIBLE
            dataViewBinding.tvFragmentDataError.visibility = View.GONE
            val sorted = rooms.sortedBy { room -> room.occupied }
            roomsAdapter = AdapterRooms(rooms = ArrayList(sorted), context = requireContext(), searchListener = this)
            dataViewBinding.rvFragmentData.adapter = roomsAdapter

            dataViewBinding.svFragmentData.visibility = View.VISIBLE
        } else {
            showError(true, resources.getString(R.string.no_data_available), true)
        }
    }

    /**
     * calculate span count based on screen width and min card size for room item data
     * */
    private fun getSpanCount(): Int {
        val cardMinSize = resources.getDimensionPixelSize(R.dimen.rooms_card_min_width)
        return (SystemUtils.getScreenWidth(requireActivity()) / cardMinSize)
    }

    /**
     * add listeners to relevant widgets and views
     * */
    private fun applyListeners() {
        dataViewBinding.svFragmentData.setOnQueryTextListener(
            LifecycleTextChangeListener(
                this
            ) { newText ->
                newText?.let {
                    roomsAdapter.searchRooms(it)
                }
            }
        )
        dataViewBinding.setOnClick {
            if (!dataViewBinding.svFragmentData.isIconified) {
                dataViewBinding.svFragmentData.isIconified = false
            }
        }
    }


    /**
     * initialize view/s,object/s with relevant instance of class,managers¬ etc.
     * */
    private fun init() {
        getSpanCount()
        val layoutManager = GridLayoutManager(requireContext(), getSpanCount(), RecyclerView.VERTICAL, false)
        dataViewBinding.rvFragmentData.layoutManager = layoutManager

        apiVm = ApiVM(requireActivity().application)

        val searchText = dataViewBinding.svFragmentData.findViewById<EditText>(R.id.search_src_text)
        searchText.setTextColor(ContextCompat.getColor(requireContext(), R.color.textViewPrimary))
        searchText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.textViewSecondary))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        init()

        applyListeners()

        fetchRooms()

        return dataViewBinding.root
    }

    override fun onPause() {
        super.onPause()
        dataViewBinding.svFragmentData.clearFocus()
    }

    override fun searchResult(searchedData: ArrayList<*>?) {
        if (searchedData.isNullOrEmpty()) {
            showError(true, resources.getString(R.string.no_data_available), false)
        } else {
            showError(false, null, false)
        }
    }
}