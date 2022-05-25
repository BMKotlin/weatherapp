package com.viht.weathermvvm.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.viht.weathermvvm.data.remote.response.DataResponse
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
    // EEEE, dd MMM yyyy format
    val dateSearch: String,
    val weather: DataResponse
): Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}