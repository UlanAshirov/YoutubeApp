package com.joma.youtubeapp.ui.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joma.youtubeapp.databinding.VideosItemBinding

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding: VideosItemBinding =
            VideosItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class VideoViewHolder(val binding: VideosItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}