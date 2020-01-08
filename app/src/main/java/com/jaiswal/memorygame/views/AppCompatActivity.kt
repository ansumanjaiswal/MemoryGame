package com.jaiswal.memorygame.views

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.jaiswal.memorygame.viewModels.ViewModelFactory


fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)