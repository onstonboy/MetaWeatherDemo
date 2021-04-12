package com.chuongdever.metaweather.data.model

import com.chuongdever.metaweather.data.source.local.entities.WeatherInfoEntity
import com.google.gson.annotations.SerializedName

data class WeatherInfo(
        @SerializedName("title")
        val name: String,
        @SerializedName("latt_long")
        val latLng: String,
) {
    companion object {
        fun fromWeatherInfoEntity(info: WeatherInfoEntity): WeatherInfo {
            return WeatherInfo(
                    name = info.title,
                    latLng = info.latLng
            )
        }
    }
}