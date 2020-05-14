package alshariqa.althaqafiya.magazine.utils.view

import alshariqa.althaqafiya.magazine.R
import android.widget.ImageView
import io.pixel.android.Pixel
import io.pixel.android.config.PixelOptions

object ImageLoader {
    fun load(url: String?, imageView: ImageView, placeHolder: Int = R.drawable.placeholder) {
        Pixel.load(url, imageView, PixelOptions.Builder().run {
            setPlaceholderResource(placeHolder)
            build()
        })
    }

    fun load(
        url: String?,
        imageView: ImageView,
        placeHolder: Int = R.drawable.placeholder,
        width: Int,
        height: Int
    ) {
        Pixel.load(url, imageView, PixelOptions.Builder().run {
            setPlaceholderResource(placeHolder)
            setImageSize(width, height)
            build()
        })
    }
}