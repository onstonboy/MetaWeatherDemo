package com.chuongdever.metaweather.data.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuongdever.metaweather.data.source.local.entities.WeatherInfoEntity
import io.reactivex.Observable

@Dao
interface WeatherInfoDao {
    @Query("SELECT * FROM WEATHER_INFO")
    fun getListWeatherInfo(): Observable<List<WeatherInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListWeatherInfo(users: List<WeatherInfoEntity>)

    @Query("DELETE FROM WEATHER_INFO")
    fun deleteAll()
}
