package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
    ) :
    ViewModel() {

        fun getLiveData() = liveDataToObserve

        fun getWeatherFromLocalSource() = getDataFromLocalSource()

        fun getWeatherFromRemoteSource() = getDataFromLocalSource()

        private fun getDataFromLocalSource() {
            liveDataToObserve.value = AppState.Loading
            Thread {
                Thread.sleep(1000)
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
            }.start()
        }
}