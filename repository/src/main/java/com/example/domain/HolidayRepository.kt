package com.example.domain

import com.example.common.Holiday
import com.example.data_networking.HolidayApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HolidayRepositoryImpl @Inject constructor(
    private val holidayApi: HolidayApi
) : HolidayRepositoryApi {
    override suspend fun getHolidays(): HolidayResult {
        return holidayApi.fetchHolidays().fold(
            onSuccess = { holidayDataTransferObjects ->
                HolidayResult.Success(holidays = holidayDataTransferObjects.map { it.toHoliday() })
            }, onFailure = {
                HolidayResult.Failure
            }
        )
    }
}

interface HolidayRepositoryApi {
    suspend fun getHolidays(): HolidayResult
}

sealed class HolidayResult {
    object Failure : HolidayResult()
    data class Success(
        val holidays: List<Holiday>
    ) : HolidayResult()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainBindings {

    @Binds
    abstract fun bindHolidayRepository(impl: HolidayRepositoryImpl): HolidayRepositoryApi

}