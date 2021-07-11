package com.android.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

/**
 * @author at-hien.dao
 */
@Entity(tableName = "cities", indices = [Index(value = ["title"], unique = true)])
data class City(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") @SerialName("title") var title: String = "",
    @ColumnInfo(name = "location_type") @SerialName("location_type") val location_type: String?,
    @ColumnInfo(name = "woeid") @SerialName("woeid") val woeid: String?,
    @ColumnInfo(name = "latt_long") @SerialName("latt_long") val latt_long: String?,
)
