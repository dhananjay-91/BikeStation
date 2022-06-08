package com.dhananjay.digikraft.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {


    protected abstract fun initializeViewModel()
    protected abstract fun initializeViewBinding()
    protected abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()

        initializeViewModel()
        observeViewModel()
    }

}