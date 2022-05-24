package com.viht.weathermvvm

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.viht.data.local.WeatherDatabase
import com.viht.data.local.dao.WeatherDAO
import com.viht.data.local.entity.WeatherEntity
import com.viht.domain.model.DataModel
import com.viht.domain.model.TemperatureModel
import com.viht.domain.model.WeatherDescriptionModel
import com.viht.domain.model.WeatherModel
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

    private lateinit var weatherDatabase: com.viht.data.local.WeatherDatabase
    private lateinit var weatherDao: com.viht.data.local.dao.WeatherDAO

    private val searchKey: String = "saigon"
    private val dateSearch: String = "Mon, 23 May 2022"
    private var data: com.viht.domain.model.DataModel =
        com.viht.domain.model.DataModel(arrayListOf<com.viht.domain.model.WeatherModel>().also {
            it.add(
                com.viht.domain.model.WeatherModel(
                    dt = 1653278400,
                    humidity = 79,
                    pressure = 1006,
                    temp = com.viht.domain.model.TemperatureModel(max = 29.97F, min = 24.62F),
                    weather = arrayListOf(
                        com.viht.domain.model.WeatherDescriptionModel(
                            description = "moderate rain",
                            main = "Rain"
                        )
                    )
                )
            )
            it.add(
                com.viht.domain.model.WeatherModel(
                    dt = 1653364800,
                    humidity = 55,
                    pressure = 1009,
                    temp = com.viht.domain.model.TemperatureModel(max = 34.54F, min = 24.95F),
                    weather = arrayListOf(
                        com.viht.domain.model.WeatherDescriptionModel(
                            description = "overcast clouds",
                            main = "Clouds"
                        )
                    )
                )
            )
            it.add(
                com.viht.domain.model.WeatherModel(
                    dt = 1653451200,
                    humidity = 56,
                    pressure = 1010,
                    temp = com.viht.domain.model.TemperatureModel(max = 34.26F, min = 26.12F),
                    weather = arrayListOf(
                        com.viht.domain.model.WeatherDescriptionModel(
                            description = "light rain",
                            main = "Rain"
                        )
                    )
                )
            )
            it.add(
                com.viht.domain.model.WeatherModel(
                    dt = 1653537600,
                    humidity = 66,
                    pressure = 1008,
                    temp = com.viht.domain.model.TemperatureModel(max = 32.33F, min = 25.62F),
                    weather = arrayListOf(
                        com.viht.domain.model.WeatherDescriptionModel(
                            description = "light rain",
                            main = "Rain"
                        )
                    )
                )
            )
            it.add(
                com.viht.domain.model.WeatherModel(
                    dt = 1653624000,
                    humidity = 62,
                    pressure = 1007,
                    temp = com.viht.domain.model.TemperatureModel(max = 31.7F, min = 25.9F),
                    weather = arrayListOf(
                        com.viht.domain.model.WeatherDescriptionModel(
                            description = "light rain",
                            main = "Rain"
                        )
                    )
                )
            )
        })

    private val entity = com.viht.data.local.entity.WeatherEntity(
        searchKey,
        dateSearch,
        data
    )

    // execute before every test case
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        weatherDatabase = Room.inMemoryDatabaseBuilder(context, com.viht.data.local.WeatherDatabase::class.java)
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

        val weathers: List<com.viht.data.local.entity.WeatherEntity> = weatherDao.getAll() ?: arrayListOf()
        assertEquals(weathers.size, 1)
        val db: com.viht.data.local.entity.WeatherEntity = weathers[0]
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