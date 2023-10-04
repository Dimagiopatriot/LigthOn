package com.appcreate.ligthon.views

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appcreate.ligthon.domain.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getPowerTracks()
        setContent {
            StartUI()
        }
    }

    @Composable
    @Preview
    fun StartUI() {

        val networkDataState = mainActivityViewModel.powerTrackData
        val dialogStateOpen = remember { mutableStateOf(false) }

        Scaffold(bottomBar = {
            BottomAddCardView(onClick = { dialogStateOpen.value = true })
        }) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
            ) {

                items(items = networkDataState, itemContent = { item ->
                    NetworkCardView(cardName = item.name, isLightOn = item.isOn) {
                        mainActivityViewModel.deletePowerTrack(item)
                    }
                })
            }
        }

        if (dialogStateOpen.value) {
            AddNewNetworkDialog(onDialogDismiss = {
                dialogStateOpen.value = it
            }, onAddButtonClicked = {
                mainActivityViewModel.createPowerTrack(it)
            })
        }
    }
}