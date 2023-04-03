package com.example.virginmoneyapp.ui.people

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel

import com.example.virginmoneyapp.databinding.FragmentPeopleBinding
import com.example.virginmoneyapp.ui.PeoplesDetails.PeoplesDetailsViewModel
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
        viewModel.peoples.observe(viewLifecycleOwner) {
            it?.let { it ->
                namesList(it).also { namesList = it }
                //           colorList  =  colorList(it)
                setupUI(it, namesList)

            }

        }
        viewModel.getPeoples()
        return binding.root
    }

    private fun colorList(people: ArrayList<PeopleModelItemModel>): MutableSet<String> {

        people?.forEach {
            colorList.add(it.favouriteColor.toString())

        }
        return colorList

    }

    private fun namesList(people: ArrayList<PeopleModelItemModel>?): MutableList<String> {
        val namesList = ArrayList<String>()
        people?.forEach {
            namesList.add(it.name.toString())

        }
        return namesList
    }

    fun setupUI(people: ArrayList<PeopleModelItemModel>, namesList: MutableList<String>) {
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


    }
}