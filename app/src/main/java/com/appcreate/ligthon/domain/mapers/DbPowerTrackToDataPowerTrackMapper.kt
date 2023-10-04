package com.appcreate.ligthon.domain.mapers

import com.appcreate.ligthon.data.PowerTrackData
import com.appcreate.ligthon.db.PowerTrackDb
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbPowerTrackToDataPowerTrackMapper @Inject constructor()  {

    fun fromDbPowerTrackToPowerTrack(powerTrackDb: PowerTrackDb): PowerTrackData {
        return PowerTrackData(
            id = powerTrackDb.uid,
            name = powerTrackDb.name,
            ipAddress = powerTrackDb.ipAddress,
            isOn = powerTrackDb.isOn
        )
    }

    fun fromDbPowerTracksToPowerTracks(dbPowerTracks: List<PowerTrackDb>): List<PowerTrackData> {
        return dbPowerTracks.map { powerTrackDb ->
            PowerTrackData(
                id = powerTrackDb.uid,
                name = powerTrackDb.name,
                ipAddress = powerTrackDb.ipAddress,
                isOn = powerTrackDb.isOn
            )
        }
    }

    fun fromPowerTrackToDbPowerTrack(powerTrackData: PowerTrackData): PowerTrackDb {
        return PowerTrackDb(
            uid = powerTrackData.id ?: 0,
            name = powerTrackData.name,
            ipAddress = powerTrackData.ipAddress,
            isOn = powerTrackData.isOn
        )
    }
}