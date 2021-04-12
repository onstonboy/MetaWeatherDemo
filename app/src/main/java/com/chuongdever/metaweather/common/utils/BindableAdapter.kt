package com.chuongdever.metaweather.common.utils

interface BindableAdapter<T> {
    fun updateData(items: List<T>)
}