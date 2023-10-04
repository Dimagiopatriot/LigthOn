package com.appcreate.ligthon.domain.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcreate.ligthon.data.PowerTrackData
import com.appcreate.ligthon.domain.repos.PowerTrackRepository
import com.appcreate.ligthon.domain.usecases.SavePowerTrackUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var powerTrackRepository: PowerTrackRepository

    @Inject
    lateinit var savePowerTrackUseCase: SavePowerTrackUseCase

    val powerTrackData = mutableStateListOf<PowerTrackData>()

    fun createPowerTrack(nameAndIp: Pair<String, String>) {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                val newPowerTrack = savePowerTrackUseCase.createAndSaveNewNetwork(nameAndIp)
                powerTrackData.add(newPowerTrack)
            }
        }
    }

    fun getPowerTracks() {
        viewModelScope.launch {
            val res = async (Dispatchers.IO) { powerTrackRepository.getPowerTracks().toMutableList() }
            powerTrackData.addAll(res.await())
        }
    }


    fun deletePowerTrack(powerTrack: PowerTrackData) {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                powerTrackRepository.deletePowerTrack(powerTrack)
            }
        }
        powerTrackData.remove(powerTrack)
    }
}