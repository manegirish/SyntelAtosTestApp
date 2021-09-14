package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.adapters.AdapterPeople
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.callbacks.ItemClickedListener
import com.test.syntelatostestapp.callbacks.SearchListener
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.dialogs.DialogFragmentPeopleDetails
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.tasks.ApiVM
import com.test.syntelatostestapp.utils.LifecycleTextChangeListener


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class FragmentPeople : BaseFragment(), ItemClickedListener, SearchListener {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding
    private lateinit var adapterPeople: AdapterPeople
    private lateinit var apiVm: ApiVM

    /**
     * make API call with Vm to fetch people list
     * */
    private fun fetchPeople() {
        if (checkInternet(dataViewBinding.root)) {
            dataViewBinding.loaderOn = true
            apiVm.fetchPeople()
            apiVm.getPeopleObserver().observe(viewLifecycleOwner, {
                dataViewBinding.loaderOn = false
                val people = it ?: ArrayList()
                populatePeople(people = people)
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
     * show list of people or error message based on people array size
     * */
    private fun populatePeople(people: List<People>) {
        if (people.isNotEmpty()) {
            dataViewBinding.rvFragmentData.visibility = View.VISIBLE
            dataViewBinding.tvFragmentDataError.visibility = View.GONE
            adapterPeople = AdapterPeople(
                people = ArrayList(people.sortedBy { item -> item.name() }),
                activity = requireActivity(),
                itemClickedListener = this,
                searchListener = this
            )
            dataViewBinding.rvFragmentData.adapter = adapterPeople
            dataViewBinding.svFragmentData.visibility = View.VISIBLE
        } else {
            showError(true, resources.getString(R.string.no_data_available), true)
        }
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
                    adapterPeople.searchPeople(it.trim())
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
     * initialize view/s,object/s with relevant managers, etc.
     * */
    private fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
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

        fetchPeople()

        return dataViewBinding.root
    }

    override fun itemClicked(view: View, position: Int, data: Any?) {
        if (data != null && data is People) {
            dataViewBinding.svFragmentData.clearFocus()
            val peopleDetailsDialog = DialogFragmentPeopleDetails.newInstance(data)
            peopleDetailsDialog.showNow(childFragmentManager, null)
        }
    }

    override fun searchResult(searchedData: ArrayList<*>?) {
        if (searchedData.isNullOrEmpty()) {
            showError(true, resources.getString(R.string.no_data_available), false)
        } else {
            showError(false, null, false)
        }
    }
}