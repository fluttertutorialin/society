package data.getway.fake

import data.remote.model.NotificationHistoryDto
import domain.entity.NotificationHistory
import domain.entity.PaginationItems
import domain.getway.IUserGateway
import data.remote.mapper.toEntity

class FakeUserGateway : IUserGateway {
    override suspend fun getNotificationHistory(
        page: Int,
        limit: Int,
    ): PaginationItems<NotificationHistory> {
        return PaginationItems(
            items = notifications.map { it.toEntity() },
            page = page,
            total = 3
        )
    }

    override suspend fun getNotificationHistoryInLast24Hours(): List<NotificationHistory> {
        TODO("Not yet implemented")
    }

    private val notifications = listOf(
        NotificationHistoryDto(
            id = "64f372095fecc11e6d917656",
            title = "Order is Cancelled",
            sender = 1,
            body = "Sorry! Yummies Restaurant have so much load they cancelled your order.",
            date = 1695047432000,
            topic = "Order",
            userId = "64f3663e5ddbc15bfd1efcfa"
        ),
        NotificationHistoryDto(
            id = "64f372095fecc11e6d917657",
            title = "Order is Cancelled",
            sender = 2,
            body = "Sorry! Yummies Restaurant have so much load they cancelled your order.",
            date = 1695070832000,
            topic = "Order",
            userId = "64f3663e5ddbc15bfd1efcfa"
        ),
        NotificationHistoryDto(
            id = "64f372095fecc11e6d917658",
            title = "Order is Ready",
            sender = 1,
            body = "Your order is on its way!",
            date = 1694996492000,
            topic = "Order",
            userId = "64f3663e5ddbc15bfd1efcfa"
        ),
        NotificationHistoryDto(
            id = "64f372095fecc11e6d917659",
            title = "Cashback 50%",
            sender = 1,
            body = "Get 50% cashback for the next order from Restaurant Yummies.",
            date = 1695032732000,
            topic = "Order",
            userId = "64f3663e5ddbc15bfd1efcfa"
        ),
        NotificationHistoryDto(
            id = "64f372095fecc11e6d917660",
            title = "Cashback 50%",
            sender = 3,
            body = "Get 50% cashback for the next order from Restaurant Yummies.",
            date = 1695032732000,
            topic = "Order",
            userId = "64f3663e5ddbc15bfd1efcfa"
        ),
    )
}
