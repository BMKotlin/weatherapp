package com.viht.weathermvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.viht.weathermvvm.data.local.entity.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert
    suspend fun insert(training: WeatherEntity)

    @Update
    suspend fun update(training: WeatherEntity)

    @Query("DELETE from weather")
    suspend fun deleteAll()

    @Query("DELETE FROM weather WHERE searchKey LIKE :searchKey")
    suspend fun deleteBySearchKey(searchKey: String)

    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<WeatherEntity>?

    @Query("SELECT * FROM weather WHERE searchKey LIKE :searchKey")
    suspend fun getWeatherBySearchKey(searchKey: String): WeatherEntity?

    /**
     * @param dateSearch - format EEEE, dd MMM yyyy
     * */
    @Query("SELECT * FROM weather WHERE searchKey LIKE :searchKey AND dateSearch = :dateSearch")
    suspend fun getBySearchKey(searchKey: String, dateSearch: String): WeatherEntity?
}

//@Transaction
//suspend fun WeatherDAO.replaceAll(vararg weathers: WeatherEntity) {
//    deleteAll()
//    insert(*weathers)
//}