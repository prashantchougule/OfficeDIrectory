package com.example.officedirectory.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.officedirectory.view.ui.ContactListFragment
import com.example.officedirectory.view.ui.RoomsListFragment

class ViewPageAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactListFragment()
            1 -> RoomsListFragment()
            else -> ContactListFragment()
        }

    }
}