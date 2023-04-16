package com.cmota.sample.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cmota.sample.ui.schedule.ScheduleScreen
import com.cmota.sample.ui.speakers.SpeakersContent
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

private val DEFAULT_SCREEN = BottomNavigationScreen.Schedule

@Composable
fun MainScreen(
    onBackAction: () -> Unit
) {

    val navController = rememberNavigator()

    val onIndexChanged: (Int, String) -> Unit = { _, route ->
        navController.navigate(route)
    }

    Scaffold(
        bottomBar = {
            MainBottomBar(
                onIndexChanged = { index, route ->
                    onIndexChanged(index, route)
                },
                items = listOf(
                    BottomNavigationScreen.Schedule,
                    BottomNavigationScreen.Speakers
                )
            )
        },
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            NavHost(navigator = navController, initialRoute = DEFAULT_SCREEN.route) {
                scene(BottomNavigationScreen.Schedule.route) {
                    ScheduleScreen(onBackAction = onBackAction)
                }

                scene(BottomNavigationScreen.Speakers.route) {
                    SpeakersContent()
                }
            }
        }
    }
}