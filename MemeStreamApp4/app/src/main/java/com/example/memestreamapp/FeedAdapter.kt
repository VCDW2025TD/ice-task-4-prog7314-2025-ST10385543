package com.example.memestreamapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memestreamapp.data.model.Gif
import com.example.memestreamapp.databinding.ItemGifBinding

class FeedAdapter : ListAdapter<Gif, FeedAdapter.GifViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GifViewHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: Gif) {
            Glide.with(binding.root.context)
                .load(gif.images.original.url)
                .into(binding.gifImage)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Gif, newItem: Gif) = oldItem == newItem
    }
}
