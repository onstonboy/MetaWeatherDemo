package com.chuongdever.metaweather.data.source.local

import android.content.Context
import androidx.room.Room

class RoomService(context: Context) {

    private val mDatabase = Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather.db")
            .build()

    fun getDatabase() = mDatabase

    private object Internal {
        fun instance(context: Context) = RoomService(context)
    }

    companion object {
        fun getInstance(context: Context) = Internal.instance(context)
    }
}