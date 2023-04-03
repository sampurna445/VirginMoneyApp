package com.example.virginmoneyapp.ui.roomsDetails

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.databinding.FragmentPeoplesDetailsBinding
import com.example.virginmoneyapp.databinding.FragmentRoomsDetailsBinding
import com.example.virginmoneyapp.ui.PeoplesDetails.PeoplesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsDetailsFragment : Fragment() {
    private var _binding: FragmentRoomsDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

  //  private val  sharedmodel: RoomsDetailsViewModel by activityViewModels()

private val viewModel by viewModels<RoomsDetailsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomsDetailsBinding.inflate(inflater,container,false)
        setupUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        val rooms = arguments?.getSerializable("RoomsItem") as RoomsModelItemModel
        binding.roomId.text = rooms?.id
        binding.roomMaxOccupancy.text = rooms?.maxOccupancy.toString()
        binding.roomCreatedAt.text = rooms?.createdAt

        if (rooms.isOccupied != null) {
            if(rooms.isOccupied == true)
                binding.roomStatus.text = "Occupied"
            else binding.roomStatus.text = "Vacant"
        }

    }


}