package com.example.virginmoneyapp.ui.people

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import androidx.lifecycle.Observer
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModel

import com.example.virginmoneyapp.databinding.FragmentPeopleBinding
import com.example.virginmoneyapp.ui.PeoplesDetails.PeoplesDetailsViewModel
import com.example.virginmoneyapp.ui.errrorHandling.ErrorHandling.doIfFailure
import com.example.virginmoneyapp.ui.errrorHandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<PeopleViewModel>()
    lateinit var namesList: MutableList<String>
    lateinit var colorList: MutableSet<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        /*viewModel.peoples.observe(viewLifecycleOwner) {
            it?.let { it ->
                namesList(it).also { namesList = it }
                //           colorList  =  colorList(it)
                setupUI(it, namesList)

            }

        }*/
        viewModel.peoples.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess {items ->
                setupUI(items)
            }

            result.doIfFailure {message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })




        viewModel.getPeoples()
        return binding.root
    }

    //function to display error message
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





    fun setupUI(people: ArrayList<PeopleModelItemModel>) {
        val peopleAdapter = PeopleAdapter(people)


        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = peopleAdapter

        }

        peopleAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("PeopleItem", it)
            }

            findNavController().navigate(R.id.action_navigation_people_to_peoplesDetails, bundle)
        }

        //Defining setOnQueryTextListener for the search view which will be triggered whenever
        // we type something in the search view

        binding.searchPeople.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                // to filter the data start with lastname given in a search bar
                val filteredList  = people.filter {
                    it.lastName?.startsWith(newText, ignoreCase = true)?:false
                }
                //changing from list of people item model to arraylist of people item model
                val filteredPeople = PeopleModel()
                for (p in filteredList)
                {
                    filteredPeople.add(p)
                }

                // filteredPeople.setPeople
                peopleAdapter.updateData(filteredPeople)
                return true
            }
        })







    }
}