package com.test.syntelatostestapp.base

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.syntelatostestapp.R
import kotlin.system.exitProcess

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/11/21
 * Last modified on 9/11/21
 */

abstract class BaseActivity : AppCompatActivity() {

    private var doubleBackPressed = false

    override fun onBackPressed() {
        if (doubleBackPressed) {
            finish()
            exitProcess(0)
        } else {
            doubleBackPressed = true
            runOnUiThread {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.double_back_pressed_warning), Toast.LENGTH_LONG
                ).show()
            }
            Handler(Looper.getMainLooper()).postDelayed({ doubleBackPressed = false }, 3000)
        }
    }
}