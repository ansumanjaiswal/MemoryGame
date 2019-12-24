package com.jaiswal.memorygame.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jaiswal.memorygame.R
import com.jaiswal.memorygame.databinding.ActivityMainBinding
import com.jaiswal.memorygame.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel : MainActivityViewModel = obtainViewModel(MainActivityViewModel::class.java)


        val adapter = CustomGridAdapter(viewModel.getCells())
        binding.gVGameView.adapter = adapter
    }
}
