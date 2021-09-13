package com.test.syntelatostestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.databinding.PeopleItemBinding
import com.test.syntelatostestapp.models.People
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.core.content.ContextCompat


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class AdapterPeople(private val people: ArrayList<People>, private val activity: FragmentActivity) :
    RecyclerView.Adapter<AdapterPeople.PeopleViewItem>() {

    class PeopleViewItem(val peopleItemBinding: PeopleItemBinding) : RecyclerView.ViewHolder(peopleItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewItem {
        val peopleItemBinding = PeopleItemBinding.inflate(LayoutInflater.from(activity.applicationContext), parent, false)
        return PeopleViewItem(peopleItemBinding)
    }

    override fun onBindViewHolder(holder: PeopleViewItem, position: Int) {
        val peopleItem = people[position]
        holder.peopleItemBinding.peopleItem = peopleItem
        holder.peopleItemBinding.setOnClick {
            val tagData = it.tag as String?
            if (!tagData.isNullOrEmpty()) {

            }
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }
}