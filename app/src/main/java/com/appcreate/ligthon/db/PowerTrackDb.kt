package com.appcreate.ligthon.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PowerTrackDb(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ip_address") val ipAddress: String,
    @ColumnInfo(name = "is_on") val isOn: Boolean
)