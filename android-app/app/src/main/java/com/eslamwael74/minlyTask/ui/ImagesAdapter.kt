package com.eslamwael74.minlyTask.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.eslamwael74.minlyTask.BuildConfig
import com.eslamwael74.minlyTask.R
import com.eslamwael74.minlyTask.databinding.ImageItemBinding
import java.util.ArrayList

class ImagesAdapter :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val images = ArrayList<String>()

    fun setItems(items: ArrayList<String>) {
        this.images.clear()
        this.images.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: ImageItemBinding =
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(image = images[position])
    }

    override fun getItemCount(): Int = images.size
}


class ImageViewHolder(
    private val itemBinding: ImageItemBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(image: String) {
        Glide.with(itemBinding.root)
            .load("${BuildConfig.BASE_URL}images/$image")
            .placeholder(R.drawable.placeholder)
            .into(itemBinding.image)
    }


}