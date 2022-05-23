package com.viht.weathermvvm

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.viht.weathermvvm.data.local.WeatherDatabase
import com.viht.weathermvvm.data.local.dao.WeatherDAO
import com.viht.weathermvvm.data.local.entity.WeatherEntity
import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.data.remote.response.TemperatureResponse
import com.viht.weathermvvm.data.remote.response.WeatherDescriptionResponse
import com.viht.weathermvvm.data.remote.response.WeatherResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var weatherDao: WeatherDAO

    private val searchKey: String = "saigon"
    private val dateSearch: String = "Mon, 23 May 2022"
    private var data: DataResponse = DataResponse(arrayListOf<WeatherResponse>().also {
        it.add(
            WeatherResponse(
                dt = 1653278400,
                humidity = 79,
                pressure = 1006,
                temp = TemperatureResponse(max = 29.97F, min = 24.62F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "moderate rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653364800,
                humidity = 55,
                pressure = 1009,
                temp = TemperatureResponse(max = 34.54F, min = 24.95F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "overcast clouds",
                        main = "Clouds"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653451200,
                humidity = 56,
                pressure = 1010,
                temp = TemperatureResponse(max = 34.26F, min = 26.12F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653537600,
                humidity = 66,
                pressure = 1008,
                temp = TemperatureResponse(max = 32.33F, min = 25.62F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653624000,
                humidity = 62,
                pressure = 1007,
                temp = TemperatureResponse(max = 31.7F, min = 25.9F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
    })

    private val entity = WeatherEntity(
        searchKey,
        dateSearch,
        data
    )

    // execute before every test case
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        weatherDatabase = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        weatherDao = weatherDatabase.getWeatherDao()
    }

    // execute after every test case
    @After
    fun teardown() {
        weatherDatabase.close()
    }

    /*
    test case to insert weather in room database
    */
    @Test
    fun insertWeather() = runBlocking {
        weatherDao.insert(entity)
        val weathers = weatherDao.getAll()
        assertEquals(weathers?.contains(entity), true)
    }

    /*
    test case to insert weather and get in room database
    */
    @Test
    fun insertAndGetWeather() = runBlocking {
        weatherDao.insert(entity)

        val weathers: List<WeatherEntity> = weatherDao.getAll() ?: arrayListOf()
        assertEquals(weathers.size, 1)
        val db: WeatherEntity = weathers[0]
        assertEquals(db.searchKey, entity.searchKey)
    }

    /*
    test case to delete weather in room database
    */
    @Test
    fun deleteWeather() = runBlocking {
        weatherDao.insert(entity)
        weatherDao.deleteBySearchKey(entity.searchKey)
        val weathers = weatherDao.getAll()//Livedata getOrAwaitValue()
        assertEquals(weathers?.contains(entity), false)
    }

    /*
    test case to insert and update user in room database
    */
    @Test
    fun updateWeather() = runBlocking {
        weatherDao.insert(entity)
        val newWeather = entity.copy(searchKey = "bentre")
        weatherDao.update(newWeather)
        val weathers = weatherDao.getAll()
        assertEquals(weathers?.contains(entity), true)
    }
}