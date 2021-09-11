package com.test.syntelatostestapp

import android.content.Intent
import android.os.Bundle
import com.test.syntelatostestapp.base.BaseActivity
import com.test.syntelatostestapp.databinding.ActivitySplashBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ActivitySplash : BaseActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
    }

    override fun onResume() {
        super.onResume()
        startActivity(Intent(applicationContext, ActivityMain::class.java))
        finish()
    }
}