package com.cmota.sample.ui.speakers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cmota.sample.MR
import com.cmota.theme.TextTypography
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SpeakersContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(MR.strings.placeholder_hello),
            style = TextTypography.body1
        )
    }
}