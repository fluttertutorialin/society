package theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fontResources

@Composable
fun headlineLarge(): TextStyle {
    return TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.4.sp,
        fontFamily = FontFamily(fontResources("mulish_medium")),
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun headline(): TextStyle {
    return TextStyle(
        fontSize = 20.sp,
       fontFamily = FontFamily(fontResources("mulish_medium")),
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleLarge(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(fontResources("mulish_medium")),
        fontWeight = FontWeight.W600,
    )
}

@Composable
fun title(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(fontResources("mulish_medium")),
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleMedium(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(fontResources("mulish_medium")),
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun body(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = FontFamily(fontResources("mulish_regular")),
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun caption(): TextStyle {
    return TextStyle(
        fontFamily = FontFamily(fontResources("mulish_regular")),
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    )
}
@Composable
fun button(): TextStyle {
    return TextStyle(
        fontFamily = FontFamily(fontResources("mulish_bold")),
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
    )
}