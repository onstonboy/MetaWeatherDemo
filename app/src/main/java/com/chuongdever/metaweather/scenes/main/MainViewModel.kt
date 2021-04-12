package com.chuongdever.metaweather.scenes.main

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.chuongdever.metaweather.BR
import com.chuongdever.metaweather.data.model.WeatherInfo
import com.chuongdever.metaweather.data.source.local.WeatherDatabase
import com.chuongdever.metaweather.data.source.remote.RestfulApi
import com.chuongdever.metaweather.repositories.WeatherRepository
import com.chuongdever.metaweather.repositories.WeatherRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val weatherDatabase: WeatherDatabase, api: RestfulApi) : BaseObservable() {
    private var mRepository: WeatherRepository = WeatherRepositoryImpl(api, weatherDatabase)
    private var mDisposable: Disposable? = null

    @get:Bindable
    var weatherInfos: List<WeatherInfo> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.weatherInfos)
        }

    fun getListWeatherInfoLocal() {
        mDisposable = weatherDatabase.weatherInfoDao().getListWeatherInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { localWeatherInfos ->
                            weatherInfos = localWeatherInfos.map {
                                WeatherInfo.fromWeatherInfoEntity(it)
                            }
                        }, { Log.e("error", "${it.message}") }
                )
    }

    fun getListWeatherInfoServer() {
        mDisposable = mRepository.getListWeatherInfoServer("a") // We can change search query here or make a input view to get from user input.
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { newWeatherInfos ->
                            weatherInfos = newWeatherInfos
                            saveListWeatherInfo(newWeatherInfos)
                        }, { Log.e("error", "${it.message}") }
                )
    }

    fun onDestroy() {
        mDisposable?.dispose()
    }

    private fun saveListWeatherInfo(infos: List<WeatherInfo>) {
        mDisposable = mRepository.saveListWeatherInfo(infos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({/*No-op*/ }, {
                    Log.e("error", "${it.message}")
                })
    }
}

