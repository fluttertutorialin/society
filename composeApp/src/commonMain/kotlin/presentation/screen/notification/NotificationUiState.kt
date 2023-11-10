package presentation.screen.notification

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.datetime.Month
import domain.entity.Date
import domain.entity.NotificationHistory
import domain.entity.Time

data class NotificationsUiState(
    val notifications: Flow<PagingData<NotificationUiState>> = emptyFlow(),
    val todayNotifications: List<NotificationUiState> = emptyList(),
    val isLoggedIn: Boolean = false,
)

data class NotificationUiState(
    val title: String = "",
    val body: String = "",
    val topicId: String = "",
    val sender: Int = NotificationHistory.NotificationSender.UNDEFINED.code,
    val isClickable: Boolean = false,
    val clickableText: String = "",
    val date: Date = Date(24, Month.AUGUST, 2023),
    val time: Time = Time(0, 0),
) {
    val notificationClickableText: String
        @Composable
        get() {
            return when (sender) {
                NotificationHistory.NotificationSender.RESTAURANT.code -> "Track Your Order"
                NotificationHistory.NotificationSender.DELIVERY.code -> "Track Your Order"
                NotificationHistory.NotificationSender.TAXI.code -> "Follow your ride"
                else -> ""
            }
        }
}