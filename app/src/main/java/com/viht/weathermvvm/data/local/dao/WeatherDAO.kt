package com.viht.weathermvvm.data.local.dao

import androidx.room.*
import com.viht.weathermvvm.data.local.entity.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(training: WeatherEntity)

    @Update
    suspend fun update(training: WeatherEntity)

    @Query("DELETE from history")
    suspend fun deleteAll()

    @Query("DELETE FROM history WHERE searchKey LIKE :searchKey")
    suspend fun deleteBySearchKey(searchKey: String)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<WeatherEntity>?

    @Query("SELECT * FROM history WHERE searchKey LIKE :searchKey")
    suspend fun getWeatherBySearchKey(searchKey: String): WeatherEntity?

    /**
     * @param dateSearch - format EEEE, dd MMM yyyy
     * */
    @Query("SELECT * FROM history WHERE searchKey LIKE  '%' || :searchKey || '%' AND dateSearch = :dateSearch")
    suspend fun getBySearchKey(searchKey: String, dateSearch: String): WeatherEntity?
}

//@Transaction
//suspend fun WeatherDAO.replaceAll(vararg weathers: WeatherEntity) {
//    deleteAll()
//    insert(*weathers)
//}