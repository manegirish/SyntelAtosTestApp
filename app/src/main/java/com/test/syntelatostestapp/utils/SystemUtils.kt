package com.test.syntelatostestapp.utils

import android.app.Activity
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */

internal object SystemUtils {

    /**
     * @return width of user device screen
     * */
    fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }
}