package com.chuongdever.metaweather.scenes.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuongdever.metaweather.R
import com.chuongdever.metaweather.common.utils.BindableAdapter
import com.chuongdever.metaweather.data.model.WeatherInfo
import kotlinx.android.synthetic.main.item_weather_info.view.*

class WeatherRecyclerAdapter :
    RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherInfoViewHolder>(), BindableAdapter<WeatherInfo> {

    private val mDataList: MutableList<WeatherInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info, parent, false)
        return WeatherInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    override fun getItemCount(): Int = mDataList.size

    override fun updateData(items: List<WeatherInfo>) {
        if (items.isEmpty()) return
        mDataList.clear()
        mDataList.addAll(items)
        notifyDataSetChanged()
    }

    class WeatherInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherInfo: WeatherInfo) {
            itemView.title.text = weatherInfo.name
            itemView.latLng.text = weatherInfo.latLng
        }
    }
}