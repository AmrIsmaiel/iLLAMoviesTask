package com.som3a.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * This class is a base for activities.
 * here we handle all common cases with the activities created in the code
 * */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    abstract val createLayout: (LayoutInflater) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = createLayout.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        createView(savedInstanceState)
    }

    abstract fun createView(savedInstanceState: Bundle?)

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}