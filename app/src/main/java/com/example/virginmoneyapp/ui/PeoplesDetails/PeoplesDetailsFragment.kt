package com.example.virginmoneyapp.ui.PeoplesDetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.virginmoneyapp.R

import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.databinding.FragmentPeoplesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeoplesDetailsFragment : Fragment() {
private var _binding: FragmentPeoplesDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val  sharedmodel: PeoplesDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeoplesDetailsBinding.inflate(inflater,container,false)

        setupUI()
        return binding.root
    }



    @SuppressLint("SuspiciousIndentation")
    private fun setupUI(){
        val people = arguments?.getSerializable("PeopleItem") as PeopleModelItemModel


/*
        var backgroundColor = Color.parseColor(people.favouriteColor.toString())

        binding.cardPeople.setCardBackgroundColor(backgroundColor)*/


        val imgView: ImageView = binding.avtarImg
            context?.let{ctx ->
                Glide.with(ctx).load(people.avatar).into(imgView)

            }


        binding.peopleFname.text = people.firstName
        binding.peopleLname.text = people.lastName
        binding.peopleJobtitle.text = people.jobtitle
        binding.peopleEmail.text = people.email

        binding.peopleFavColor.text = people.favouriteColor

    }

}