package data.getway.remote.pagesource

import org.koin.core.component.KoinComponent
import domain.entity.NotificationHistory
import domain.entity.PaginationItems
import domain.getway.IUserGateway

class NotificationPagingSource(
    private val userGateway: IUserGateway,
) : BasePagingSource<NotificationHistory>(), KoinComponent {
    override suspend fun fetchData(page: Int, limit: Int): PaginationItems<NotificationHistory> {
        return userGateway.getNotificationHistory(page, limit)
    }
}