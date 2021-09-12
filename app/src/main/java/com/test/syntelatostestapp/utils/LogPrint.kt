package com.test.syntelatostestapp.utils

import android.util.Log
import com.test.syntelatostestapp.BuildConfig

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */

internal object LogPrint {

    fun e(tag: String, stackTrace: String) {
        if (BuildConfig.DEBUG) {
            Log.e("SATP_$tag", stackTrace)
        }
    }

    fun d(tag: String, stackTrace: String) {
        if (BuildConfig.DEBUG) {
            Log.d("SATP_$tag", stackTrace)
        }
    }

    fun i(tag: String, stackTrace: String) {
        if (BuildConfig.DEBUG) {
            Log.i("SATP_$tag", stackTrace)
        }
    }
}