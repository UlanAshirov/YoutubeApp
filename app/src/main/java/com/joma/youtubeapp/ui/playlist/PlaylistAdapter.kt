package com.joma.youtubeapp.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joma.youtubeapp.data.model.playlist.Item
import com.joma.youtubeapp.databinding.PlaylistItemBinding

class PlaylistAdapter(private val sendKey: SendKeyPlayList<String>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var playlist: List<Item> = ArrayList()
    fun setPlaylist(playlist: List<Item>) {
        this.playlist = playlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding: PlaylistItemBinding = PlaylistItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(playlist[position])
        holder.itemView.setOnClickListener {
            sendKey.onItemClick(playlist[position].id)
        }
    }

    override fun getItemCount(): Int = playlist.size

    class PlaylistViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Item?) {
            if (item != null) {
                Glide.with(binding.root).load(item.snippet.thumbnails.medium.url)
                    .centerCrop().into(binding.imgItemPlaylist)
                binding.tvItemDescription.text = item.snippet.title
            }
        }
    }

    interface SendKeyPlayList<T> {
        fun onItemClick(data: T)
    }
}