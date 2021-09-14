package com.test.syntelatostestapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.callbacks.ItemClickedListener
import com.test.syntelatostestapp.callbacks.SearchListener
import com.test.syntelatostestapp.databinding.PeopleItemBinding
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.utils.LogPrint


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class AdapterPeople(
    private val people: ArrayList<People>,
    private val activity: FragmentActivity,
    private val itemClickedListener: ItemClickedListener,
    private val searchListener: SearchListener
) :
    RecyclerView.Adapter<AdapterPeople.PeopleViewItem>() {

    private val peopleList = ArrayList(people)

    class PeopleViewItem(val peopleItemBinding: PeopleItemBinding) : RecyclerView.ViewHolder(peopleItemBinding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun searchPeople(searchQuery: String?) {
        people.clear()
        if (searchQuery.isNullOrEmpty()) {
            people.addAll(peopleList)
        } else {
            for (peopleItem in peopleList) {
                if (peopleItem.name().contains(searchQuery, true)) {
                    people.add(peopleItem)
                }
            }
        }
        searchListener.searchResult(people)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewItem {
        val peopleItemBinding = PeopleItemBinding.inflate(LayoutInflater.from(activity.applicationContext), parent, false)
        return PeopleViewItem(peopleItemBinding)
    }

    override fun onBindViewHolder(holder: PeopleViewItem, position: Int) {
        val peopleItem = people[position]
        holder.peopleItemBinding.peopleItem = peopleItem
        holder.peopleItemBinding.setOnClick {
            itemClickedListener.itemClicked(view = it, position = position, data = peopleItem)
        }
        holder.peopleItemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return people.size
    }
}