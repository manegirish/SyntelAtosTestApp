package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.adapters.AdapterRooms
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding
import com.test.syntelatostestapp.tasks.ApiVM
import com.test.syntelatostestapp.utils.LogPrint


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 */
class FragmentRooms : BaseFragment() {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding
    private lateinit var roomsAdapter: AdapterRooms
    private lateinit var apiVm: ApiVM

    private fun fetchRooms() {
        dataViewBinding.loaderOn = true
        apiVm.fetchRooms()
        apiVm.getRoomsObserver().observe(viewLifecycleOwner, {
            dataViewBinding.loaderOn = false
            val rooms = it ?: ArrayList()
            if (rooms.size > 0) {
                dataViewBinding.rvFragmentData.visibility = View.VISIBLE
                dataViewBinding.tvFragmentDataError.visibility = View.GONE
                val sorted = rooms.sortedBy { room -> room.occupied }
                roomsAdapter = AdapterRooms(rooms = sorted, context = requireContext())
                dataViewBinding.rvFragmentData.adapter = roomsAdapter
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
        val layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
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