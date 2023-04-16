package com.cmota.sample.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScheduleScreen(
    onBackAction: () -> Unit
) {
    Scaffold(
        topBar = {
            ScheduleTopBar(onBackAction = onBackAction)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            ScheduleContent()
        }
    }
}