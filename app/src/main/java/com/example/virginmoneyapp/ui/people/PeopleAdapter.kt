package com.example.virginmoneyapp.ui.people


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModel
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.databinding.ItemPeopleBinding

class PeopleAdapter(var people: ArrayList<PeopleModelItemModel>) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    var onItemClick: ((PeopleModelItemModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPeopleBinding.bind(view)

        fun handleData(item: PeopleModelItemModel?) {
            binding.peopleFname.text = item?.firstName
            binding.peopleLname.text = item?.lastName
            binding.peopleJobtitle.text = item?.jobtitle

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
        return PeopleAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = people?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.handleData(people?.get(position))
        holder.itemView.setOnClickListener {
            people?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }

    }


    // update adapter with the filter data
    fun updateData(filteredPeople: PeopleModel) {
        people = filteredPeople
        notifyDataSetChanged()
    }
}
