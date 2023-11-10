package presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.composable.AppBarComposable
import presentation.composable.ButtonFilledComposable
import presentation.composable.ContentVisibilityComposable
import presentation.composable.ImageSlider
import presentation.screen.home.composable.ChatSupportCard
import presentation.screen.home.composable.EventCard
import presentation.screen.home.composable.Maintenance
import resources.Resources
import theme.Theme

class HomeScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        ContentVisibilityComposable(true) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(Theme.colors.background),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                stickyHeader {
                    AppBarComposable(
                        title = "Lakhani Kamlesh",
                        actions = {
                            if (true) {
                                Wallet(value = "")
                            } else {
                                ButtonFilledComposable(
                                    modifier = Modifier.heightIn(max = 32.dp).padding(end = 16.dp),
                                    textPadding = PaddingValues(horizontal = 16.dp),
                                    title = Resources.strings.login,
                                    onClick = {}
                                )
                            }
                        }
                    )
                }

                item {
                    ImageSlider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onItemClickListener = { },
                        images = listOf("1", "2", "3", "4", "5")
                    )
                }

                item {
                    AnimatedVisibility(true) {
                        EventCard(onClick = {  })
                    }
                }

                item {
                    LastPayment()
                }

                item {
                    AnimatedVisibility(true) {
                        ChatSupportCard(
                            onClick = {},
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                item {
                    Row(modifier = Modifier.height(IntrinsicSize.Min)){
                        Box(
                            modifier = Modifier.fillMaxSize().weight(1f),
                        ) {
                            Maintenance("Maintenance")
                        }
                        Box(
                            modifier = Modifier.fillMaxSize().weight(1f),
                            ) {
                            Maintenance("Pending")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Wallet(value: String, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(end = 16.dp)) {
            Text(
                "Advance payment",
                style = Theme.typography.body,
                color = Theme.colors.contentSecondary
            )
            Text(
                "12-12-2025",
                style = Theme.typography.title,
                color = Theme.colors.primary
            )
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun LastPayment() {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Theme.colors.divider,
                    shape = RoundedCornerShape(Theme.radius.medium)
                )
                .clip(shape = RoundedCornerShape(Theme.radius.medium))
                .background(Theme.colors.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Top) {
                Text(
                    "Last payment",
                    style = Theme.typography.titleLarge.copy(color = Theme.colors.contentPrimary)
                )
                Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Image(
                        painterResource(Resources.images.lastPayment),
                        contentDescription = "connect with support text icon",
                        modifier = Modifier.size(56.dp),
                    )

                    Row(modifier = Modifier
                        .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f).padding(start = 12.dp),
                        ) {
                            Text(
                                "10121920",
                                style = Theme.typography.caption.copy(color = Theme.colors.contentSecondary)
                            )

                            Text(
                                "Holiday event",
                                modifier = Modifier.padding(top = 8.dp),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = Theme.typography.body.copy(color = MaterialTheme.colorScheme.onSecondary),
                            )
                            Text(
                                "12-12-2022",
                                modifier = Modifier.padding(top = 3.dp),
                                style = Theme.typography.caption.copy(color = MaterialTheme.colorScheme.onSecondary)
                            )
                        }
                        Text(
                            "Rs. 500",
                            modifier = Modifier.padding(start = 12.dp),
                            style = Theme.typography.titleLarge.copy(color = Theme.colors.primary))
                    }
                }
            }
        }
    }
}