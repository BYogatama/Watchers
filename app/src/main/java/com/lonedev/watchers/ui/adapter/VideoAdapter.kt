package com.lonedev.watchers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lonedev.watchers.R
import com.lonedev.watchers.data.model.VideoResult
import com.lonedev.watchers.databinding.ItemVideoBinding

class VideoAdapter
constructor(private val context: Context) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {


    var videos: MutableList<VideoResult> = mutableListOf()
    lateinit var onItemClickListener: (VideoResult) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_video, parent, false
        ) as ItemVideoBinding
        return VideoViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
    }

    class VideoViewHolder(
        private val binding: ItemVideoBinding,
        private val onItemClickListener: (VideoResult) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: VideoResult) {
            binding.video = video

            binding.videoThumbnail.setOnClickListener {
                onItemClickListener(video)
            }

        }
    }
}