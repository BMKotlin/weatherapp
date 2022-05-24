package com.viht.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.viht.domain.model.DataModel

@Entity(
    tableName = "history",
    indices = [
        Index(
            value = ["searchKey", "dateSearch"],
            unique = true
        )
    ]
)
data class WeatherEntity(
    val searchKey: String,
    val dateSearch: String,// EEEE, dd MMM yyyy format
    val weather: DataModel
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}