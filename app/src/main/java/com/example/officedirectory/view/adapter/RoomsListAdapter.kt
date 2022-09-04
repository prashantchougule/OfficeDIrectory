package com.example.officedirectory.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officedirectory.R
import com.example.officedirectory.databinding.RoomListItemBinding
import com.example.officedirectory.view.uistate.RoomItemUIState
import java.util.*

class RoomsListAdapter (): RecyclerView.Adapter<RoomsListAdapter.RoomsViewHolder>() {
    private var data: List<RoomItemUIState> = Collections.emptyList()
    inner class RoomsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(roomsItemUIState: RoomItemUIState) {
            val bind = RoomListItemBinding.bind(itemView)
            bind.apply {
                roomIdTextview.text = roomsItemUIState.id
                occupancyValueTextView.text = roomsItemUIState.maxOccupancy
                roomAvailableTextview.text = if(roomsItemUIState.isOccupied) "Not Available" else "Available"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.room_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun setData(roomsList: List<RoomItemUIState>) {
        this.data = roomsList
        notifyDataSetChanged()
    }
}