package com.test.syntelatostestapp

import android.os.Bundle
import com.test.syntelatostestapp.base.BaseActivity
import com.test.syntelatostestapp.databinding.ActivityMainBinding

/**
 * @author Girish D. Mane gmane@birdzi.com
 * Created on 9/11/21
 * Last modified on 9/11/21
 * All rights reserved by Birdzi In
 */

class ActivityMain : BaseActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}