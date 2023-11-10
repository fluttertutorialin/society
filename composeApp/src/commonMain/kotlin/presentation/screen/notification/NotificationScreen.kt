package presentation.screen.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.Navigator
import getStatusBarPadding
import presentation.base.BaseScreen
import presentation.composable.ContentVisibilityComposable
import presentation.composable.PagingListComposable
import presentation.screen.notification.combosable.NotificationCard
import theme.Theme

class NotificationScreen : BaseScreen<NotificationScreenModel, NotificationsUiState, NotificationUiEffect, NotificationInteractionListener>() {
    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    override fun onEffect(effect: NotificationUiEffect, navigator: Navigator) {
        when (effect) {
            is NotificationUiEffect.NavigateToLoginScreen -> {}

            is NotificationUiEffect.NavigateToTrackFoodOrder -> {}

            is NotificationUiEffect.NavigateToTrackDelivery -> {}

            is NotificationUiEffect.NavigateToTaxiRide -> {}
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun onRender(state: NotificationsUiState, listener: NotificationInteractionListener) {
        val notifications = state.notifications.collectAsLazyPagingItems()

        /*LoginRequiredPlaceholder(
            placeHolder = painterResource(Resources.images.requireLoginToShowNotificationPlaceholder),
            message = Resources.strings.notificationLoginMessage,
            onClickLogin = listener::onClickLogin
        )*/

        ContentVisibilityComposable(true) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Theme.colors.background)
                    .padding(getStatusBarPadding()),
            ) {
                    PagingListComposable(
                        data = notifications,
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        optionalHeaderTitle = "Today",
                        optionalTopLList = state.todayNotifications,
                        hasOptionalList = state.todayNotifications.isNotEmpty(),
                        optionalContent = { notification ->
                            NotificationCard(
                                title = notification.title,
                                content = notification.body,
                                time = notification.time,
                                cardShape = if (state.todayNotifications.indexOf(notification) == 0) {
                                    RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                                } else {
                                    RoundedCornerShape(8.dp)
                                }
                            )
                        },
                        content = { notificationUiState ->
                            notificationUiState?.let { notification ->
                                NotificationCard(
                                    title = notification.title,
                                    content = notification.body,
                                    time = notification.time,
                                    date = notification.date,
                                    showDate = true
                                )
                            }
                        }
                    )
                }
            }
        }
}
