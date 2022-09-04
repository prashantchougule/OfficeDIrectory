package com.example.officedirectory.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.officedirectory.R
import com.example.officedirectory.databinding.ContactListItemBinding
import com.example.officedirectory.view.uistate.ContactItemUIState
import java.util.Collections.emptyList

class ContactListAdapter (): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private var data: List<ContactItemUIState> = emptyList()
    inner class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(contactItemUIState: ContactItemUIState) {
            val bind = ContactListItemBinding.bind(itemView)
            bind.apply {
                firstNameTextview.text = contactItemUIState.firstname
                lastNameTextview.text = contactItemUIState.lastname
                jobtitleTextview.text = contactItemUIState.jobTitle
                favColorValueTextview.text = contactItemUIState.favouriteColor
                emailValueTextview.text = contactItemUIState.emailId
                Glide.with(avtarImageview)
                    .asBitmap()
                    .load(contactItemUIState.avtarUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(BitmapImageViewTarget(avtarImageview))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun setData(contactList: List<ContactItemUIState>) {
        this.data = contactList
        notifyDataSetChanged()
    }
}