package com.appcreate.ligthon.domain.repos

import com.appcreate.ligthon.data.PowerTrackData

interface PowerTrackRepository {

    suspend fun getPowerTrack(powerTrackName: String): PowerTrackData

    suspend fun getPowerTrackByIp(ipAddress: String): PowerTrackData

    suspend fun getPowerTracks(): List<PowerTrackData>

    suspend fun addNewPowerTrack(powerTrackData: PowerTrackData): Long

    suspend fun deletePowerTrack(powerTrackData: PowerTrackData)

}