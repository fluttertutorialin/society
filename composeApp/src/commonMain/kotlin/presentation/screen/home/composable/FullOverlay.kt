package presentation.screen.home.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color

fun Modifier.fullOverlay(): Modifier {
    return then(drawWithContent {
        drawContent()
        drawRect(
            color = Color.Black.copy(0.90F),
            size = size,
        )
    })
}