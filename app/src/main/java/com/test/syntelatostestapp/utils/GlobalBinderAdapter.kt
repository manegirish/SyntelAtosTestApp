package com.test.syntelatostestapp.utils

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter

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
}