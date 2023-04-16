package com.cmota.sample.ui.schedule

import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.cmota.sample.MR
import com.cmota.theme.NavigationTypography
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ScheduleTopBar(
    onBackAction: () -> Unit
) {

    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackAction) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /*Icon(
                        painter = icBack(),
                        modifier = Modifier.size(25.dp),
                        contentDescription = stringResource(MR.strings.description_back),
                        tint = BLUE
                    )

                    Spacer(modifier = Modifier.width(8.dp))*/

                    Text(
                        text = stringResource(MR.strings.navigation_back),
                        style = NavigationTypography.subtitle1
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}