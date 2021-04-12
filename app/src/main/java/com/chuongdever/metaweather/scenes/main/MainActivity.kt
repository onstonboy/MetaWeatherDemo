package com.chuongdever.metaweather.scenes.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chuongdever.metaweather.R
import com.chuongdever.metaweather.data.source.local.RoomService
import com.chuongdever.metaweather.data.source.remote.RestfulService
import com.chuongdever.metaweather.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        weatherRecyclerView.adapter = WeatherRecyclerAdapter()
        val roomService = RoomService.getInstance(this@MainActivity)
        mViewModel = MainViewModel(roomService.getDatabase(), RestfulService.instance.getApi())
        updateViewModel(binding)
    }

    private fun updateViewModel(binding: ActivityMainBinding) {
        binding.viewModel = mViewModel
        mViewModel?.apply {
            getListWeatherInfoLocal()
            getListWeatherInfoServer()
        }
    }

    override fun onDestroy() {
        mViewModel?.onDestroy()
        super.onDestroy()
    }
}