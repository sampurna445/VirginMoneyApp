package com.example.virginmoneyapp.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModel

import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel

import com.example.virginmoneyapp.databinding.FragmentRoomsBinding
import com.example.virginmoneyapp.ui.errrorHandling.ErrorHandling.doIfFailure
import com.example.virginmoneyapp.ui.errrorHandling.ErrorHandling.doIfSuccess
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

        viewModel.rooms.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess {items ->
                setupUI(items)
            }

            result.doIfFailure {message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })
        viewModel.getRooms()

        //availablity dropdown
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.rooms_availability,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerRoomsAvailability.adapter = adapter
        }


        return binding.root
    }

    private fun showErrorPopup(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun setupUI(rooms: ArrayList<RoomsModelItemModel>) {
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
        binding.spinnerRoomsAvailability.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                val selectedItem= parent.getItemAtPosition(position)
                if(selectedItem=="Available Rooms")
                {
                    val filteredList  = rooms.filter {
                        !(it.isOccupied)!!

                    }
                    val filteredRooms = ArrayList<RoomsModelItemModel>()
                    for (p in filteredList)
                    {
                        filteredRooms.add(p)
                    }
                    roomsAdapter.updateData(filteredRooms)
                }
                else if(selectedItem=="Occupied Rooms"){
                    val filteredList= rooms.filter {
                        (it.isOccupied == true)
                    }
                    val filteredRooms = RoomsModel()
                    for (p in filteredList)
                    {
                        filteredRooms.add(p)
                    }
                    roomsAdapter.updateData(filteredRooms)

                }
                else
                {
                    roomsAdapter.updateData(rooms)
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }





    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}