package presentation.screen.notification.combosable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.Theme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PaymentStatusCard(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(88.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() } ,
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource("img_background.png"),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.clip(MaterialTheme.shapes.large)
        )
        Box(
            modifier = Modifier.fillMaxSize().background(
                Color.Black.copy(alpha = 0.4f),
                shape = MaterialTheme.shapes.large
            )
        )
        Text(
            text = "Success",
            style = Theme.typography.titleLarge,
            color = Theme.colors.onPrimary
        )
    }
}