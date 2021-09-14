package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.adapters.AdapterPeople
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.callbacks.ItemClickedListener
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.dialogs.DialogFragmentPeopleDetails
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.tasks.ApiVM

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class FragmentPeople : BaseFragment(), ItemClickedListener {

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
            showError(resources.getString(R.string.internet_unavailable))
        }
    }

    /**
     * show error message on view and hide list view¬
     * */
    private fun showError(errorMessage: String) {
        dataViewBinding.tvFragmentDataError.text = errorMessage
        dataViewBinding.tvFragmentDataError.contentDescription =
            StringBuilder().append(resources.getString(R.string.error)).append(" ").append(errorMessage)
        dataViewBinding.tvFragmentDataError.visibility = View.VISIBLE
        dataViewBinding.rvFragmentData.visibility = View.GONE
    }

    /**
     * show list of people or error message based on people array size
     * */
    private fun populatePeople(people: List<People>) {
        if (people.isNotEmpty()) {
            dataViewBinding.rvFragmentData.visibility = View.VISIBLE
            dataViewBinding.tvFragmentDataError.visibility = View.GONE
            adapterPeople = AdapterPeople(
                people = people.sortedBy { item -> item.name() },
                activity = requireActivity(),
                itemClickedListener = this
            )
            dataViewBinding.rvFragmentData.adapter = adapterPeople
        } else {
            showError(resources.getString(R.string.no_data_available))
        }
    }

    /**
     * initialize view/s,object/s with relevant managers, etc.
     * */
    private fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        dataViewBinding.rvFragmentData.layoutManager = layoutManager

        apiVm = ApiVM(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        init()

        fetchPeople()

        return dataViewBinding.root
    }

    override fun itemClicked(view: View, position: Int, data: Any?) {
        if (data != null && data is People) {
            val peopleDetailsDialog = DialogFragmentPeopleDetails.newInstance(data)
            peopleDetailsDialog.showNow(childFragmentManager, null)
        }
    }
}