package com.test.syntelatostestapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.syntelatostestapp.R
import com.test.syntelatostestapp.callbacks.SearchListener
import com.test.syntelatostestapp.databinding.RoomItemBinding
import com.test.syntelatostestapp.models.Room
import com.test.syntelatostestapp.utils.LogPrint

/**
 * @author Girish D. Mane gmane@birdzi.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 * All rights reserved by Birdzi In
 */
internal class AdapterRooms(private val rooms: ArrayList<Room>,
                            private val context: Context,
                            private val searchListener: SearchListener
) :
    RecyclerView.Adapter<AdapterRooms.RoomItemView>() {

    private val roomList = ArrayList(rooms)

    class RoomItemView(val roomItemBinding: RoomItemBinding) : RecyclerView.ViewHolder(roomItemBinding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun searchRooms(searchQuery: String?) {
        rooms.clear()
        if (searchQuery.isNullOrEmpty()) {
            rooms.addAll(roomList)
        } else {
            for (room in roomList) {
                if (room.name.toString().contains(searchQuery, true)) {
                    rooms.add(room)
                }
            }
        }
        searchListener.searchResult(rooms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemView {
        val roomItemBinding = RoomItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return RoomItemView(roomItemBinding = roomItemBinding)
    }

    override fun onBindViewHolder(holder: RoomItemView, position: Int) {
        val room = rooms[position]
        holder.roomItemBinding.room = room
        if (room.occupied) {
            holder.roomItemBinding.tvRoomItemCount.setTextColor(ContextCompat.getColor(context, R.color.textViewSecondary))
            holder.roomItemBinding.tvRoomItemName.setTextColor(ContextCompat.getColor(context, R.color.textViewPrimary))
            holder.roomItemBinding.tvRoomItemName.setBackgroundColor(ContextCompat.getColor(context, R.color.textViewSecondary))
        } else {
            holder.roomItemBinding.tvRoomItemCount.setTextColor(ContextCompat.getColor(context, R.color.room_available))
            holder.roomItemBinding.tvRoomItemName.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.roomItemBinding.tvRoomItemName.setBackgroundColor(ContextCompat.getColor(context, R.color.room_available))
        }
    }

    override fun getItemCount(): Int {
        return rooms.size
    }
}