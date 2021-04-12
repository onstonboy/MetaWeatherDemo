package com.chuongdever.metaweather.data.source.remote

import com.chuongdever.metaweather.data.model.WeatherInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestfulApi {
    @GET("/api/location/search")
    fun getListWeatherInfoByQuery(@Query("query") query: String): Observable<List<WeatherInfo>>
}
