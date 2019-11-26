package com.ayan.appy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory

class GuestProfile : AppCompatActivity() {

    companion object {
        private const val SLIDE_NUMBER = 10
    }

    private lateinit var imageSlider: ImageSlider
    private val imageUrls = arrayListOf(
        "http://i.imgur.com/CqmBjo5.jpg",
        "http://i.imgur.com/zkaAooq.jpg",
        "http://i.imgur.com/0gqnEaY.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_profile)

        imageSlider = findViewById(R.id.image_slider)
        imageSlider.adapter = SliderAdapter(
            this,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls,
            descriptions = Data.generateDescriptions(imageUrls.size)
        )
    }

}

