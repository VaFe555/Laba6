package com.example.mygalleryapp_lab6

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val imageList: List<Uri>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUri = imageList[position]
        Glide.with(holder.itemView.context)
            .load(imageUri)
            .into(holder.imageView)

        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            val input = EditText(holder.itemView.context)
            builder.setView(input)
            builder.setTitle("Add Description")
            builder.setPositiveButton("Save") { dialog, which ->
                val description = input.text.toString()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
            true
        }

        holder.itemView.setOnClickListener {
            val description = "Описание изображения"
            val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(imageUri, description)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}