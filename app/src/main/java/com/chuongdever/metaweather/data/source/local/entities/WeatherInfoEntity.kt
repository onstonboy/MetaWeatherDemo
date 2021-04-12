package com.chuongdever.metaweather.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chuongdever.metaweather.data.model.WeatherInfo

@Entity(tableName = "weather_info")
data class WeatherInfoEntity(
        @PrimaryKey
        var title: String = "",
        var latLng: String = "",
) {
    companion object {
        fun fromWeatherInfo(info: WeatherInfo): WeatherInfoEntity {
            return WeatherInfoEntity().apply {
                this.title = info.name
                this.latLng = info.latLng
            }
        }
    }
}
