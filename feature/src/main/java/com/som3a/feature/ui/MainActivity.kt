package com.som3a.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.som3a.base.BaseActivity
import com.som3a.feature.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This activity is handle all our fragments
 * we added in the app
 * We only have one activity container has all fragments
 * on it.
 * It's an entry point to the android UI components and screens
 * This is required to be implemented at least once
 * in the app
 * */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {


    override val createLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun createView(savedInstanceState: Bundle?) = Unit
}