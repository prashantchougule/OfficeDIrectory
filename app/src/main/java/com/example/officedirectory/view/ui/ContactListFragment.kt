package com.example.officedirectory.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officedirectory.R
import com.example.officedirectory.databinding.FragmentContactListBinding
import com.example.officedirectory.databinding.FragmentRoomsListBinding
import com.example.officedirectory.view.adapter.ContactListAdapter
import com.example.officedirectory.view.adapter.RoomsListAdapter
import com.example.officedirectory.view.uistate.ContactListUIState
import com.example.officedirectory.view.uistate.RoomsListUIState
import com.example.officedirectory.view.viewmodel.ContactListViewModel
import com.example.officedirectory.view.viewmodel.RoomsListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding
    private val contactsAdapter = ContactListAdapter()
    private val contactViewModel: ContactListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactlist.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.contactlist.adapter =contactsAdapter
        //observe livedata from viewModel
        lifecycleScope.launchWhenCreated{
            contactViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
                updateUI(viewState)
            }
            contactViewModel.fetchContacts()
            //We can also have option menu to refresh list at any time on fragment
        }
    }

    // Method to handle UI state updates
    private fun updateUI(viewState: ContactListUIState) {
        when (viewState) {
            is ContactListUIState.Content -> {
                binding.contactlist.isVisible = true
                binding.contactListErrorView.isVisible = false
                binding.contactLoadingView.isVisible = false
                contactsAdapter.setData(viewState.contactList)
            }
            is ContactListUIState.Error-> {
                binding.contactlist.isVisible = false
                binding.contactListErrorView.isVisible = true
                binding.contactLoadingView.isVisible = false
                Toast.makeText(activity,viewState.message, Toast.LENGTH_LONG).show()
            }
            is ContactListUIState.Loading -> {
                binding.contactlist.isVisible = false
                binding.contactListErrorView.isVisible = false
                binding.contactLoadingView.isVisible = true
            }
        }
    }

}