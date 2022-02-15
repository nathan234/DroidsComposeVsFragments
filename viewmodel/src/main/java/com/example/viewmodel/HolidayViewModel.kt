package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Holiday
import com.example.domain.HolidayRepositoryApi
import com.example.domain.HolidayResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val holidayRepositoryApi: HolidayRepositoryApi
) : ViewModel() {

    init {
        viewModelScope.launch {
            getHolidays()
        }
    }

    private val _holidaysLiveData = MutableLiveData<List<Holiday>>()
    val holidaysLiveData: LiveData<List<Holiday>> = _holidaysLiveData

    private suspend fun getHolidays() {
        when (val result = holidayRepositoryApi.getHolidays()) {
            HolidayResult.Failure -> {
                _holidaysLiveData.value = listOf(failure())
            }
            is HolidayResult.Success -> {
                _holidaysLiveData.value = result.holidays
            }
        }
    }

    private fun failure() = Holiday(
        date = "failure!",
        localName = "failure!",
        name = "failure!"
    )
}
