package com.manhnguyen.codebase.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.manhnguyen.codebase.R
import java.util.*

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val newsId: UUID = UUID.randomUUID(),
    @Embedded val article: Article
) {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            try {
                url?.let {
                    Glide.with(imageView.context).load(url).centerCrop()
                        .placeholder(R.drawable.image_placeholder_black_36)
                        .into(imageView)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}