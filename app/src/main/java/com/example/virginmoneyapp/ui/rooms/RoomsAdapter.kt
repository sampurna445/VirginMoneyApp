package com.example.virginmoneyapp.ui.rooms

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.databinding.ItemRoomBinding


class RoomsAdapter(var rooms: ArrayList<RoomsModelItemModel>):
RecyclerView.Adapter<RoomsAdapter.ViewHolder>(){

    var onItemClick: ((RoomsModelItemModel) -> Unit)? = null

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val binding = ItemRoomBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun handleData(item: RoomsModelItemModel?) {
            binding.roomId.text = item?.id
            if (item != null) {
                if(item.isOccupied == true)
                    binding.roomStatus.text = "Occupied"
                else binding.roomStatus.text = "Vacant"
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RoomsAdapter. ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room,parent,false)

        return RoomsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = rooms?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.handleData(rooms?.get(position))
        holder.itemView.setOnClickListener{
            rooms?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    fun updateData(filteredRooms: ArrayList<RoomsModelItemModel>) {
        rooms = filteredRooms
        notifyDataSetChanged()

    }

}
