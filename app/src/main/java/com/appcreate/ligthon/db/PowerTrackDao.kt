package com.appcreate.ligthon.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PowerTrackDao {

    @Query("SELECT * FROM PowerTrackDb WHERE name = (:powerTrackName)")
    suspend fun getPowerTrack(powerTrackName: String): PowerTrackDb

    @Query("SELECT * FROM PowerTrackDb WHERE ip_address = (:ipAddress)")
    suspend fun getPowerTrackByIp(ipAddress: String): PowerTrackDb

    @Query("SELECT * FROM PowerTrackDb")
    suspend fun getPowerTracks(): List<PowerTrackDb>

    @Insert
    suspend fun addNewPowerTrack(powerTrackDb: PowerTrackDb): Long

    @Delete
    suspend fun deletePowerTrack(powerTrackDb: PowerTrackDb)
}