package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(val dataRick: List<ResultsItem>): RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
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
        if(dataRick != null){
            return dataRick.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameItem.text = dataRick?.get(position)?.name
        holder.statusItem.text = dataRick?.get(position)?.status
        holder.speciesItem.text = dataRick?.get(position)?.species

        Glide.with(holder.imgItem)
            .load(dataRick?.get(position)?.image)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgItem)

        holder.itemView.setOnClickListener{
            val name = dataRick?.get(position)?.name
            Toast.makeText(holder.itemView.context, "${name}", Toast.LENGTH_SHORT).show()
        }

    }
}