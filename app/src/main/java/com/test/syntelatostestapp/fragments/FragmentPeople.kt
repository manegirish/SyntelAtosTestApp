package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.adapters.AdapterPeople
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.tasks.ApiVM

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class FragmentPeople : BaseFragment() {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding
    private lateinit var adapterPeople: AdapterPeople
    private lateinit var apiVm: ApiVM

    private fun fetchPeople() {
        dataViewBinding.loaderOn = true
        apiVm.fetchPeople()
        apiVm.getPeopleObserver().observe(viewLifecycleOwner, {
            dataViewBinding.loaderOn = false
            val people = it ?: ArrayList()
            if (people.size > 0) {
                dataViewBinding.rvFragmentData.visibility = View.VISIBLE
                dataViewBinding.tvFragmentDataError.visibility = View.GONE

                adapterPeople = AdapterPeople(people = people, activity = requireActivity())
                dataViewBinding.rvFragmentData.adapter = adapterPeople

            } else {
                dataViewBinding.tvFragmentDataError.visibility = View.VISIBLE
                dataViewBinding.rvFragmentData.visibility = View.GONE
            }
        })
    }

    /**
     * initialize view/s,object/s with relevant managers, etc.
     * */
    private fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        dataViewBinding.rvFragmentData.layoutManager = layoutManager

        apiVm = ApiVM(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        init()

        fetchPeople()

        return dataViewBinding.root
    }
}