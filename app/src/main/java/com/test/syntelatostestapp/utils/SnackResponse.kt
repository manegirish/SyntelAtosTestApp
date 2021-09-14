package com.test.syntelatostestapp.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.test.syntelatostestapp.R


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */
/**
 * global class to show customized response message/s
 * */
internal object SnackResponse {

    fun success(message: String, view: View) {
        val successSnack = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        successSnack.setTextColor(ContextCompat.getColor(view.context, R.color.success_text))
        successSnack.show()
    }

    fun failure(message: String, view: View) {
        val failureSnack = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        failureSnack.setTextColor(ContextCompat.getColor(view.context, R.color.error_text))
        failureSnack.show()
    }

    fun somethingWentWrong(view: View) {
        val failureSnack = Snackbar.make(view, view.context.getString(R.string.something_went_Wrong), Snackbar.LENGTH_SHORT)
        failureSnack.setTextColor(ContextCompat.getColor(view.context, R.color.error_text))
        failureSnack.show()
    }

    fun internetUnavailable(view: View) {
        val failureSnack = Snackbar.make(view, view.context.getString(R.string.internet_unavailable), Snackbar.LENGTH_SHORT)
        failureSnack.setTextColor(ContextCompat.getColor(view.context, R.color.error_text))
        failureSnack.show()
    }

}