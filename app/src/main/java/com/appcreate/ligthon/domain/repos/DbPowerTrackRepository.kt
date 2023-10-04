package com.appcreate.ligthon.domain.repos

import com.appcreate.ligthon.data.PowerTrackData
import com.appcreate.ligthon.db.PowerTrackDao
import com.appcreate.ligthon.domain.mapers.DbPowerTrackToDataPowerTrackMapper
import javax.inject.Inject

class DbPowerTrackRepository @Inject constructor(): PowerTrackRepository {

    @Inject
    lateinit var powerTrackDao: PowerTrackDao

    @Inject
    lateinit var dbPowerTrackToDataPowerTrackMapper: DbPowerTrackToDataPowerTrackMapper

    override suspend fun getPowerTrack(powerTrackName: String): PowerTrackData {
        return dbPowerTrackToDataPowerTrackMapper.fromDbPowerTrackToPowerTrack(powerTrackDao.getPowerTrack(powerTrackName))
    }

    override suspend fun getPowerTrackByIp(ipAddress: String): PowerTrackData {
        return dbPowerTrackToDataPowerTrackMapper.fromDbPowerTrackToPowerTrack(powerTrackDao.getPowerTrackByIp(ipAddress))
    }

    override suspend fun getPowerTracks(): List<PowerTrackData> {
        return dbPowerTrackToDataPowerTrackMapper.fromDbPowerTracksToPowerTracks(powerTrackDao.getPowerTracks())
    }

    override suspend fun addNewPowerTrack(powerTrackData: PowerTrackData): Long {
        return powerTrackDao.addNewPowerTrack(dbPowerTrackToDataPowerTrackMapper.fromPowerTrackToDbPowerTrack(powerTrackData))
    }

    override suspend fun deletePowerTrack(powerTrackData: PowerTrackData) {
        powerTrackDao.deletePowerTrack(dbPowerTrackToDataPowerTrackMapper.fromPowerTrackToDbPowerTrack(powerTrackData))
    }
}