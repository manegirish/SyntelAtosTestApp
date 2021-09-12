package com.test.syntelatostestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.syntelatostestapp.base.BaseFragment
import com.test.syntelatostestapp.databinding.FragmentDataRecyclerViewBinding

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class FragmentPeople : BaseFragment() {

    private lateinit var dataViewBinding: FragmentDataRecyclerViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataViewBinding = FragmentDataRecyclerViewBinding.inflate(inflater, container, false)

        return dataViewBinding.root
    }
}