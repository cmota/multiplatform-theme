package com.cmota.sample

import android.os.Bundle
import com.cmota.sample.ui.main.MainScreen
import com.cmota.theme.MultiplatformTheme
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplatformTheme {
                MainScreen(
                    onBackAction = { finish() }
                )
            }
        }
    }
}