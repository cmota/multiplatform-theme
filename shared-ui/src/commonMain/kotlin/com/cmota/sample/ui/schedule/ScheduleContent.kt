package com.cmota.sample.ui.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cmota.sample.MR
import com.cmota.theme.TextTypography
import com.seiko.imageloader.rememberAsyncImagePainter
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ScheduleContent() {

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {

        item {
            Text(
                text = stringResource(MR.strings.placeholder_title),
                style = TextTypography.h1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(MR.strings.placeholder_text),
                style = TextTypography.body1
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {

                val url = stringResource(MR.strings.placeholder_speaker_avatar)
                val painter = rememberAsyncImagePainter(url)

                Image(
                    painter = painter,
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape),
                    contentDescription = stringResource(MR.strings.description_speaker_image)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {

                    Text(
                        text = stringResource(MR.strings.placeholder_speaker_title),
                        style = TextTypography.h2
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(MR.strings.placeholder_speaker_description),
                        style = TextTypography.body1,
                        //fontFamily = font1()
                    )
                }
            }
        }
    }
}