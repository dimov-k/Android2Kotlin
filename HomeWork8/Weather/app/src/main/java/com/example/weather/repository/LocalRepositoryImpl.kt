package com.example.weather.repository

import com.example.weather.model.Weather
import com.example.weather.room.HistoryDao
import com.example.weather.utils.convertHistoryEntityToWeather
import com.example.weather.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
    LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}