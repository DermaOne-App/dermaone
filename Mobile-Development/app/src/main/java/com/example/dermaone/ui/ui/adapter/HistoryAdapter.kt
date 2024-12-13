package com.example.dermaone.ui.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dermaone.databinding.ItemHistoryBinding

class HistoryAdapter(private val historyList: List<Pair<String, String>>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewHistory: TextView = binding.textViewHistory
        val imageViewHistory = binding.ivHistoryImage

        fun bind(result: String, imageUrl: String) {
            textViewHistory.text = result
            Glide.with(imageViewHistory.context)
                .load(imageUrl)
                .into(imageViewHistory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (result, imageUrl) = historyList[position]
        holder.bind(result, imageUrl)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}
