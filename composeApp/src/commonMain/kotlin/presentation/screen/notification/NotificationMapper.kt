package presentation.screen.notification

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import domain.entity.NotificationHistory

fun NotificationHistory.toUiState(): NotificationUiState {
    return NotificationUiState(
        title = title,
        body = body,
        time = time,
        date = date,
        topicId = topicId,
        sender = sender.code
    )
}

fun List<NotificationHistory>.toUiState(): List<NotificationUiState> {
    return this.map { it.toUiState() }
}


fun Flow<PagingData<NotificationHistory>>.toUiState(): Flow<PagingData<NotificationUiState>> {
    return this.map { pagingData -> pagingData.map { it.toUiState() } }
}