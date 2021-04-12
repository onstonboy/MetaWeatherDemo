package com.chuongdever.metaweather.repositories

import android.util.Log
import com.chuongdever.metaweather.data.model.WeatherInfo
import com.chuongdever.metaweather.data.source.local.WeatherDatabase
import com.chuongdever.metaweather.data.source.local.entities.WeatherInfoEntity
import com.chuongdever.metaweather.data.source.remote.RestfulApi
import io.reactivex.Completable
import io.reactivex.Observable

interface WeatherRepository {
    fun getListWeatherInfoServer(query: String): Observable<List<WeatherInfo>>

    fun getListWeatherInfoLocal(query: String): Observable<List<WeatherInfoEntity>>

    fun saveListWeatherInfo(infos: List<WeatherInfo>): Completable
}

class WeatherRepositoryImpl(
        private val api: RestfulApi,
        private val database: WeatherDatabase,
) : WeatherRepository {
    override fun getListWeatherInfoServer(query: String): Observable<List<WeatherInfo>> {
        return api.getListWeatherInfoByQuery(query)
    }

    override fun getListWeatherInfoLocal(query: String): Observable<List<WeatherInfoEntity>> {
        return database.weatherInfoDao().getListWeatherInfo()
    }

    override fun saveListWeatherInfo(infos: List<WeatherInfo>): Completable {
        return Completable.defer {
            Completable.fromAction {
                database.weatherInfoDao().insertListWeatherInfo(infos.map { WeatherInfoEntity.fromWeatherInfo(it) })
            }
        }
    }
}