package com.example.data_networking

import com.example.common.Holiday
import com.google.gson.annotations.SerializedName
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface HolidayApi {

    @GET("breeds/image/random")
    suspend fun fetchHolidays(): Result<List<HolidayDTO>>
}

class HolidayImpl @Inject constructor(
    private val holidayRetrofitService: HolidayRetrofitService
) : HolidayApi {

    override suspend fun fetchHolidays(): Result<List<HolidayDTO>> {
        val response = holidayRetrofitService.getStuff()
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDto())
        } else Result.failure(Throwable("fetch failure"))
    }
}

class HolidayResponse : ArrayList<HolidayResponse.HolidayResponseItem>() {

    data class HolidayResponseItem(
        @SerializedName("date")
        val date: String?,
        @SerializedName("localName")
        val localName: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("countryCode")
        val countryCode: String?,
        @SerializedName("fixed")
        val fixed: Boolean?,
        @SerializedName("global")
        val global: Boolean?,
        @SerializedName("counties")
        val counties: List<String>?,
        @SerializedName("launchYear")
        val launchYear: Int?,
        @SerializedName("type")
        val type: String?
    )

    fun toDto(): List<HolidayDTO> {
        return this.map { holidayResponseItem ->
            HolidayDTO(
                date = holidayResponseItem.date,
                localName = holidayResponseItem.localName,
                name = holidayResponseItem.name
            )
        }
    }
}

data class HolidayDTO(
    val date: String?,
    val localName: String?,
    val name: String?,
) {
    fun toHoliday() = Holiday(
        date = date.orEmpty(),
        localName = localName.orEmpty(),
        name = name.orEmpty()
    )
}

interface HolidayRetrofitService {

    @GET("publicholidays/2022/US")
    suspend fun getStuff(): Response<HolidayResponse>
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindings {

    @Binds
    abstract fun bindHolidayApi(impl: HolidayImpl): HolidayApi
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl = "https://date.nager.at/api/v2/"

    @Provides
    @Singleton
    fun provideAlfiRetrofitService(): HolidayRetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(HolidayRetrofitService::class.java)
    }
}