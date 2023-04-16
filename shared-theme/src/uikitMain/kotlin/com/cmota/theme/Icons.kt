package com.cmota.theme

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
public actual fun icBack() = painterResource("ic_chevron_backward.xml")

@OptIn(ExperimentalResourceApi::class)
@Composable
public actual fun icSchedule() = painterResource("ic_calendar.xml")

@OptIn(ExperimentalResourceApi::class)
@Composable
public actual fun icSpeakers() = painterResource("ic_person_fill.xml")