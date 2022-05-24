package com.viht.weathermvvm.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.viht.weathermvvm.domain.model.DataModel
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "history",
    indices = [
        Index(
            value = ["searchKey", "dateSearch"],
            unique = true
        )
    ]
)
@Parcelize
data class WeatherEntity(
    val searchKey: String,
    val dateSearch: String,// EEEE, dd MMM yyyy format
    val weather: DataModel
): Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}