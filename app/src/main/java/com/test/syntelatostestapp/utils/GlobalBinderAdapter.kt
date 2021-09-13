package com.test.syntelatostestapp.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.test.syntelatostestapp.R

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */

object GlobalBinderAdapter {

    @BindingAdapter("favoriteColor")
    @JvmStatic
    fun setFavoriteColor(imageFrame: FrameLayout, colorString: String?) {
        imageFrame.background.colorFilter = PorterDuffColorFilter(Color.parseColor(colorString), PorterDuff.Mode.SRC)
    }

    @BindingAdapter("profileImage")
    @JvmStatic
    fun setProfileImage(imageView: AppCompatImageView, imageUrl: String?) {
        GlideApp.with(imageView.context)
            .asBitmap()
            .load(imageUrl)
            .override(imageView.resources.getDimensionPixelSize(R.dimen.profile_image)+100)
            .sizeMultiplier(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(ContextCompat.getDrawable(imageView.context, R.drawable.ic_avatar))
            .placeholder(ContextCompat.getDrawable(imageView.context, R.drawable.ic_avatar))
            .apply(RequestOptions.circleCropTransform())
            .into(object :
                BitmapImageViewTarget(imageView) {
                override fun setResource(resource: Bitmap?) {
                    imageView.setImageBitmap(
                        resource
                    )
                }
            })
    }
}