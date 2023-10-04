package com.appcreate.ligthon.domain.usecases

import com.appcreate.ligthon.data.PowerTrackData
import com.appcreate.ligthon.domain.repos.PowerTrackRepository
import javax.inject.Inject

class SavePowerTrackUseCase @Inject constructor() {

    @Inject
    lateinit var powerTrackRepository: PowerTrackRepository

    suspend fun createAndSaveNewNetwork(nameAndIp: Pair<String, String>) = run {
        //todo add service which check power
        val newPowerTrack =
            PowerTrackData(name = nameAndIp.first, ipAddress = nameAndIp.second, isOn = false)
        saveNewPowerTrack(newPowerTrack)
        newPowerTrack
    }

    private suspend fun saveNewPowerTrack(powerTrackData: PowerTrackData) {
        powerTrackData.id = powerTrackRepository.addNewPowerTrack(powerTrackData)
    }
}