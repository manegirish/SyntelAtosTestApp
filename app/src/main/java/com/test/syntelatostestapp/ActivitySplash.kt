package com.test.syntelatostestapp

import android.content.Intent
import android.os.Bundle
import com.test.syntelatostestapp.base.BaseActivity
import com.test.syntelatostestapp.databinding.ActivitySplashBinding

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/11/21
 * Last modified on 9/11/21
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