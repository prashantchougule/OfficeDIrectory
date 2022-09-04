package com.example.officedirectory.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officedirectory.R
import com.example.officedirectory.databinding.FragmentRoomsListBinding
import com.example.officedirectory.view.adapter.RoomsListAdapter
import com.example.officedirectory.view.uistate.RoomsListUIState
import com.example.officedirectory.view.viewmodel.RoomsListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class RoomsListFragment : Fragment() {
    private lateinit var binding:FragmentRoomsListBinding
    private val roomsAdapter = RoomsListAdapter()
    private val viewModel: RoomsListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoomsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.roomslist.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.roomslist.adapter =roomsAdapter
        //observe livedata from viewModel
        lifecycleScope.launchWhenCreated{
            viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
                updateUI(viewState)
            }
            viewModel.fetchRooms()
            //We can also have option menu to refresh list at any time on fragment
        }
    }

    // Method to handle UI state updates
    private fun updateUI(viewState: RoomsListUIState) {
        when (viewState) {
            is RoomsListUIState.Content -> {
                binding.roomslist.isVisible = true
                binding.roomsListErrorView.isVisible = false
                binding.roomsLoadingView.isVisible = false
                roomsAdapter.setData(viewState.roomsList)
            }
            is RoomsListUIState.Error-> {
                binding.roomslist.isVisible = false
                binding.roomsListErrorView.isVisible = true
                binding.roomsLoadingView.isVisible = false
                Toast.makeText(activity,viewState.message, Toast.LENGTH_LONG).show()
            }
            is RoomsListUIState.Loading -> {
                binding.roomslist.isVisible = false
                binding.roomsListErrorView.isVisible = false
                binding.roomsLoadingView.isVisible = true
            }
        }
    }


}