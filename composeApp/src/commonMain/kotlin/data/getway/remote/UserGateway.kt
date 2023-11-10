package data.getway.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import data.remote.mapper.toEntity
import data.remote.model.NotificationHistoryDto
import data.remote.model.PaginationResponse
import data.remote.model.ServerResponse
import domain.entity.NotificationHistory
import domain.entity.PaginationItems
import domain.getway.IUserGateway
import util.GeneralException

class UserGateway(
    client: HttpClient,
) : BaseGateway(client), IUserGateway {

    override suspend fun getNotificationHistory(
        page: Int,
        limit: Int,
    ): PaginationItems<NotificationHistory> {
        val result = tryToExecute<ServerResponse<PaginationResponse<NotificationHistoryDto>>> {
            get("/notifications/history") {
                parameter("page", page)
                parameter("limit", limit)
            }
        }.value

        //println("History before mapping = ${result?.items}")
        //println("History After mapping = ${result?.items?.map { it.toEntity() }}")

        return paginateData(
            result = result?.items?.map { it.toEntity() } ?: emptyList(),
            page = page,
            total = limit.toLong(),
        )
    }

    override suspend fun getNotificationHistoryInLast24Hours(): List<NotificationHistory> {
        return tryToExecute<ServerResponse<List<NotificationHistoryDto>>> {
            get("/notifications/history-24hours")
        }.value?.map { it.toEntity() } ?: throw GeneralException.NotFoundException
    }
}