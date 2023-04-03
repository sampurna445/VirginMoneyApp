package com.example.virginmoneyapp.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneyapp.R

import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel

import com.example.virginmoneyapp.databinding.FragmentRoomsBinding
import com.example.virginmoneyapp.ui.roomsDetails.RoomsDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val sharedmodel: RoomsDetailsViewModel by activityViewModels()
    private val viewModel by viewModels<RoomsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomsBinding.inflate(inflater, container, false)

        viewModel.rooms.observe(viewLifecycleOwner){
            it?.let {
                setupUI(it,viewModel,sharedmodel)
            }

        }
        viewModel.getRooms()
        return binding.root
    }

    private fun setupUI(rooms: ArrayList<RoomsModelItemModel>, viewModel: RoomsViewModel, sharedmodel: RoomsDetailsViewModel) {
            val roomsAdapter = RoomsAdapter(rooms)
        binding.rvRooms.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomsAdapter
        }
        roomsAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("RoomsItem", it)
            }

            findNavController().navigate(R.id.action_navigation_rooms_to_roomsDetails,bundle)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}