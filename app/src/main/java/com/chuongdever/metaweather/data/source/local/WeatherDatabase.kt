package com.chuongdever.metaweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chuongdever.metaweather.data.source.local.daos.WeatherInfoDao
import com.chuongdever.metaweather.data.source.local.entities.WeatherInfoEntity

@Database(
        entities = [WeatherInfoEntity::class],
        version = 1,
        exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}