package com.jaiswal.memorygame.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.jaiswal.memorygame.R
import com.jaiswal.memorygame.databinding.ActivityMainBinding
import com.jaiswal.memorygame.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel : MainActivityViewModel = obtainViewModel(MainActivityViewModel::class.java)


        val adapter = CustomGridAdapter(viewModel.getGridCells())
        binding.gVGameView.adapter = adapter
        binding.viewModel = viewModel
        viewModel.getImageRemoteRepository().observe(this) {
            Toast.makeText(this, "On Response", Toast.LENGTH_SHORT).show()
        }
    }
}
