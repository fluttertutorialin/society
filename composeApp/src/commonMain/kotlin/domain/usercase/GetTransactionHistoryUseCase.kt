package domain.usercase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import data.getway.remote.pagesource.NotificationPagingSource
import domain.entity.NotificationHistory

interface IGetTransactionHistoryUseCase {
    suspend fun getNotificationHistory(): Flow<PagingData<NotificationHistory>>
}

class GetTransactionHistoryUseCase(
    private val notificationPagingSource: NotificationPagingSource,
) : IGetTransactionHistoryUseCase {
    override suspend fun getNotificationHistory(): Flow<PagingData<NotificationHistory>> {
        return Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory = { notificationPagingSource }
        ).flow
    }
}
