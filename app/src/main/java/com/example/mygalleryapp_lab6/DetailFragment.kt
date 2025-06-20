package com.example.mygalleryapp_lab6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val imageUri = DetailFragmentArgs.fromBundle(requireArguments()).imageUri
        val description = DetailFragmentArgs.fromBundle(requireArguments()).description

        val imageView: ImageView = view.findViewById(R.id.imageViewDetail)
        val descriptionView: TextView = view.findViewById(R.id.descriptionView)

        Glide.with(requireContext())
            .load(imageUri)
            .into(imageView)

        descriptionView.text = description

        return view
    }
}