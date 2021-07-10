package com.android.myapplication.data.model

import kotlinx.serialization.SerialName

/**
 * @author at-hien.dao
 */
data class City(
    @SerialName("title") val title: String?,
    @SerialName("location_type") val locationType: String?,
    @SerialName("woeid") val woeid: String?,
    @SerialName("latt_long") val latLong: String?,
)
