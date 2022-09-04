package com.example.officedirectory.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.officedirectory.R
import com.example.officedirectory.databinding.ActivityMainBinding
import com.example.officedirectory.view.adapter.ViewPageAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ViewPageAdapter(this, 2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabs,binding.pager,){tab, position ->
            if(position == 0) {
                tab.text = resources.getString(R.string.people)
            }else {
                tab.text = resources.getString(R.string.rooms)
            }
        }.attach()
    }

}