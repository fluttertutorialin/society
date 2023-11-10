package presentation.screen.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import resources.Resources
import theme.Theme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Maintenance(
    title: String,
    modifier: Modifier = Modifier,
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize().overlayBottomToTop(),
                painter = painterResource(Resources.images.backgroundCard),
                contentDescription = Resources.strings.backgroundDescription,
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(
                    title,
                    style = Theme.typography.headline,
                    color = Theme.colors.contentSecondary
                )
                Text(
                    "Rs. 1000",
                    style = Theme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}


