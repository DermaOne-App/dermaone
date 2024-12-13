package com.example.dermaone.ui.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dermaone.R
import com.example.dermaone.ui.ui.response.OrganicResultsItem

class HealthInformationAdapter(
    private val healthList: List<OrganicResultsItem>
) : RecyclerView.Adapter<HealthInformationAdapter.HealthViewHolder>() {

    class HealthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val informationName: TextView = itemView.findViewById(R.id.informationname)
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)
        return HealthViewHolder(view)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val item = healthList[position]


        holder.informationName.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.thumbnail)
            .into(holder.thumbnail)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(item.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = healthList.size
}

