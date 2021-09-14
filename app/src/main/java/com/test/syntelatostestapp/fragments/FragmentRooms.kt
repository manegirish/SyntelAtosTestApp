package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.adapters.AdapterRooms
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.models.Room
import com.test.syntelatostestapp.tasks.ApiVM
import com.test.syntelatostestapp.utils.SystemUtils


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 */
internal class FragmentRooms : BaseFragment() {

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
            showError(resources.getString(R.string.internet_unavailable))
        }
    }

    /**
     * show error message on view and hide list view
     * */
    private fun showError(errorMessage: String) {
        dataViewBinding.tvFragmentDataError.text = errorMessage
        dataViewBinding.tvFragmentDataError.contentDescription =
            StringBuilder().append(resources.getString(R.string.error)).append(" ").append(errorMessage)
        dataViewBinding.tvFragmentDataError.visibility = View.VISIBLE
        dataViewBinding.rvFragmentData.visibility = View.GONE
    }

    /**
     * show list view or error message based on rooms array size
     * */
    private fun populateRooms(rooms: List<Room>) {
        if (rooms.isNotEmpty()) {
            dataViewBinding.rvFragmentData.visibility = View.VISIBLE
            dataViewBinding.tvFragmentDataError.visibility = View.GONE
            val sorted = rooms.sortedBy { room -> room.occupied }
            roomsAdapter = AdapterRooms(rooms = sorted, context = requireContext())
            dataViewBinding.rvFragmentData.adapter = roomsAdapter
        } else {
            showError(resources.getString(R.string.no_data_available))
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
     * initialize view/s,object/s with relevant instance of class,managers¬ etc.
     * */
    private fun init() {
        getSpanCount()
        val layoutManager = GridLayoutManager(requireContext(), getSpanCount(), RecyclerView.VERTICAL, false)
        dataViewBinding.rvFragmentData.layoutManager = layoutManager

        apiVm = ApiVM(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        init()

        fetchRooms()

        return dataViewBinding.root
    }
}