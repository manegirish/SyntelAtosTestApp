package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.tasks.ApiVM


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 */
class FragmentRooms : BaseFragment() {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding
    private lateinit var apiVm: ApiVM

    private fun fetchRooms() {
        apiVm.fetchRooms()
        apiVm.getRoomsObserver().observe(viewLifecycleOwner, {
            val rooms = it ?: ArrayList()
            if (rooms.size > 0) {
                dataViewBinding.rvFragmentData.visibility = View.VISIBLE
                dataViewBinding.tvFragmentDataError.visibility = View.GONE

            } else {
                dataViewBinding.tvFragmentDataError.visibility = View.VISIBLE
                dataViewBinding.rvFragmentData.visibility = View.GONE
            }
        })
    }

    /**
     * initialize view/s,object/s with relevant instance of class,managers¬ etc.
     * */
    private fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        dataViewBinding.rvFragmentData.layoutManager = layoutManager

        apiVm = ApiVM(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        init()

        return dataViewBinding.root
    }

}