package com.cmota.sample.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MainBottomBar(
    onIndexChanged: (Int, String) -> Unit,
    items: List<BottomNavigationScreen>
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        ) {}

        AppBottomNavigation(
            onIndexChanged = onIndexChanged,
            items = items
        )
    }
}

@Composable
private fun AppBottomNavigation(
    onIndexChanged: (Int, String) -> Unit,
    items: List<BottomNavigationScreen>
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
        cutoutShape = CircleShape
    ) {

        val selectedIndex = remember { mutableStateOf(0) }

        items.forEachIndexed { index, screen ->

            val isSelected = selectedIndex.value == index

            val color = if (isSelected) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.secondary
            }

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.painter,
                        contentDescription = stringResource(screen.stringRes)
                    )
                },
                label = {
                    Text(
                        stringResource(screen.stringRes),
                        color = color,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                selected = isSelected,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true,
                onClick = {
                    if (!isSelected) {
                        selectedIndex.value = index
                        onIndexChanged(index, screen.route)
                    }
                }
            )
        }
    }
}