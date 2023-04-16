package com.cmota.sample.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector
import com.cmota.sample.MR
import dev.icerock.moko.resources.StringResource

sealed class BottomNavigationScreen(
    val route: String,
    val stringRes: StringResource,
    val painter: ImageVector
) {

    object Schedule: BottomNavigationScreen(
        "Schedule",
        MR.strings.navigation_schedule,
        Icons.Filled.Schedule //icSchedule()
    )

    object Speakers: BottomNavigationScreen(
        "Speakers",
        MR.strings.navigation_speakers,
        Icons.Filled.Person //icSpeakers()
    )
}