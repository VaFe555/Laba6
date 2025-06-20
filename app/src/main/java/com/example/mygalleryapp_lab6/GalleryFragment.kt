package com.example.mygalleryapp_lab6

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GalleryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private val imageList = mutableListOf<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewGallery)
        recyclerView.layoutManager = LinearLayoutManager(context)
        imageAdapter = ImageAdapter(imageList)
        recyclerView.adapter = imageAdapter

        loadImages()

        return view
    }

    private fun loadImages() {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val imageUri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
                imageList.add(imageUri)
            }
        }

        imageAdapter.notifyDataSetChanged()
    }

}