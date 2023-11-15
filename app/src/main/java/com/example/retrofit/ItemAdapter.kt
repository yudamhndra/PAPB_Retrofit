package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(val dataItem: List<ResultsItem>): RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem = view.findViewById<ImageView>(R.id.item_image)
        val nameItem = view.findViewById<TextView>(R.id.item_name)
        val statusItem = view.findViewById<TextView>(R.id.item_status)
        val speciesItem = view.findViewById<TextView>(R.id.item_species)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataItem != null){
            return dataItem.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataItem?.get(position)

        holder.nameItem.text = currentData?.name
        holder.statusItem.text = currentData?.status

        currentData?.species?.let {
            holder.speciesItem.text = it
        } ?: run {
            holder.speciesItem.text = "unknown"
        }

        Glide.with(holder.imgItem)
            .load(currentData?.image)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgItem)

        holder.itemView.setOnClickListener {
            val name = currentData?.name
            val type = currentData?.type ?: "unknown"
            val gender = currentData?.gender ?: "unknown"

            Toast.makeText(holder.itemView.context, "${name}, ${type}, ${gender}", Toast.LENGTH_SHORT).show()
        }
    }



}