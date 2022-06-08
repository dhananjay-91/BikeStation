package com.dhananjay.digikraft.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhananjay.digikraft.data.repository.StationRepo
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repo: StationRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("MainViewModel not found")
    }
}