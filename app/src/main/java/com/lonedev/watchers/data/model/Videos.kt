package com.lonedev.watchers.data.model


import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.lonedev.watchers.R
import com.lonedev.watchers.utils.Constant
import com.squareup.picasso.Picasso

data class Videos(
    val id: Int = 0,
    val results: List<VideoResult> = listOf()
)

data class VideoResult(
    val id: String = "",
    @SerializedName("iso_639_1")
    val iso6391: String = "",
    @SerializedName("iso_3166_1")
    val iso31661: String = "",
    val key: String = "",
    val name: String = "",
    val site: String = "",
    val size: Int = 0,
    val type: String = ""
) {
    companion object {
        @BindingAdapter("thumbnail")
        @JvmStatic
        fun setThumbnail(view: ImageView, key: String) {
            Picasso.get()
                .load("${Constant.VIDEO_THUMBNAIL}$key/0.jpg")
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(view)

            Log.d("VideoResult", "setThumbnail: ${Constant.VIDEO_THUMBNAIL}$key/0.jpg");
        }
    }
}
