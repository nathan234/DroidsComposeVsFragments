package com.example.droidscomposesample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data_networking.HolidayApi
import com.example.data_networking.HolidayDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val holidayApi: HolidayApi
) : ViewModel() {

    init {
        viewModelScope.launch {
            getHolidays()
        }
    }

    private val _holidaysLiveData = MutableLiveData<List<HolidayDTO>>()
    val holidaysLiveData: LiveData<List<HolidayDTO>> = _holidaysLiveData

    private suspend fun getHolidays() {
        holidayApi.fetchHolidays().fold(
            onSuccess = {
                _holidaysLiveData.value = it
            }, onFailure = {
                _holidaysLiveData.value =
                    listOf(
                        HolidayDTO(
                            date = "failure!",
                            localName = "failure!",
                            name = "failure!"
                        )
                    )
            }
        )
    }
}