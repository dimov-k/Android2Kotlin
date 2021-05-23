package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryImpl
import java.lang.Thread.sleep

const val timeoutSleep :Long  = 1000

class MainViewModel(

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
    ) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(timeoutSleep)
            liveDataToObserve.postValue(
                AppState.Success(
                if (isRussian)
                    repositoryImpl.getWeatherFromLocalStorageRus() else
                    repositoryImpl.getWeatherFromLocalStorageWorld()
                 )
            )
            //Проверить showSnackBar:
            liveDataToObserve.postValue(AppState.Error(Throwable("Test error")))

        }.start()
    }
}