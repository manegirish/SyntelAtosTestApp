package com.test.syntelatostestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.callbacks.ItemClickedListener
import com.test.syntelatostestapp.databinding.PeopleItemBinding
import com.test.syntelatostestapp.models.People


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class AdapterPeople(
    private val people: List<People>,
    private val activity: FragmentActivity,
    private val itemClickedListener: ItemClickedListener
) :
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
            itemClickedListener.itemClicked(view = it, position = position, data = peopleItem)
        }
        holder.peopleItemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return people.size
    }
}