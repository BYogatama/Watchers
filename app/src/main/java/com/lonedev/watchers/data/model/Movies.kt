package com.lonedev.watchers.data.model


import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.lonedev.watchers.R
import com.lonedev.watchers.utils.Constant
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Movies(
    val page: Int = 0,
    val results: List<MovieResult> = listOf(),
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0
)

@Entity(tableName = "movies")
data class MovieResult(
    @PrimaryKey
    val id: Int = 0,
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    val title: String = "",
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    var genres: String = "",
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    val adult: Boolean = false,
    val video: Boolean = false,
    var isFavourite: Boolean = false
) : Parcelable {
    fun isEmpty(): Boolean {
        return this == MovieResult()
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        ArrayList<Int>().apply { source.readList(this as List<*>, Int::class.java.classLoader) },
        source.readString()!!,
        source.readInt(),
        source.readDouble(),
        source.readString()!!,
        source.readString()!!,
        1 == source.readInt(),
        1 == source.readInt(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(originalTitle)
        writeString(originalLanguage)
        writeString(title)
        writeString(overview)
        writeString(releaseDate)
        writeList(genreIds)
        writeString(genres)
        writeInt(voteCount)
        writeDouble(voteAverage)
        writeString(posterPath)
        writeString(backdropPath)
        writeInt((if (adult) 1 else 0))
        writeInt((if (video) 1 else 0))
        writeInt((if (isFavourite) 1 else 0))
    }

    companion object {
        @BindingAdapter("posterPath")
        @JvmStatic
        fun setImage(view: ImageView, posterPath: String) {
            Picasso.get()
                .load("${Constant.IMAGE_URL}${Constant.W300}$posterPath")
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(view)
        }

        @BindingAdapter("isFavourite")
        @JvmStatic
        fun setFavourite(view: ImageView, isFavourite: Boolean) {
            if (isFavourite) {
                view.setImageResource(R.drawable.ic_heart_red)
            }
        }

        @BindingAdapter("releaseDate")
        @JvmStatic
        fun setReleaseDate(view: TextView, releaseDate: String) {
            val realFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val readableFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = realFormat.parse(releaseDate)
            view.text = readableFormat.format(date)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<MovieResult> = object : Parcelable.Creator<MovieResult> {
            override fun createFromParcel(source: Parcel): MovieResult = MovieResult(source)
            override fun newArray(size: Int): Array<MovieResult?> = arrayOfNulls(size)
        }
    }
}