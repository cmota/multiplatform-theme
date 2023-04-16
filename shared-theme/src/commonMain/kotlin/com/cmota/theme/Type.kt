
package com.cmota.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource

/**
 * According to Apple Guidelines:
 * - https://developer.apple.com/design/human-interface-guidelines/foundations/typography/
 */
private val LARGE_TITLE = 34.sp
private val TITLE_1 = 28.sp
private val TITLE_2 = 22.sp
private val TITLE_3 = 20.sp
private val HEADLINE = 17.sp
private val BODY = 17.sp
private val CALLOUT = 16.sp
private val SUBHEAD = 15.sp
private val FOOTNOTE = 13.sp
private val CAPTION_1 = 12.sp
private val CAPTION_2 = 11.sp

@Composable
fun font1() = fontFamilyResource(MR.fonts.sinoreta.sinoreta)

val TextTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = TITLE_2,
        color = BLUE,
        letterSpacing = 0.sp
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = TITLE_3,
        color = BLACK,
        letterSpacing = 0.sp
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = BODY,
        color = BLACK,
        letterSpacing = 0.sp
    )
)

val NavigationTypography = Typography(
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = BLUE,
        letterSpacing = 0.sp
    )
)