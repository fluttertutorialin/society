package domain.getway

import domain.entity.NotificationHistory
import domain.entity.PaginationItems

interface IUserGateway {
    //createUser
    //loginUser
    //getDeviceToken
    //refreshAccessToken
    //getUserProfile
    //getUserAddress
    //updateProfile
    //getFavouriteRestaurants
    //addRestaurantToFavorites
    //removeRestaurantFromFavorites

    suspend fun getNotificationHistory(page: Int, limit: Int): PaginationItems<NotificationHistory>
    suspend fun getNotificationHistoryInLast24Hours(): List<NotificationHistory>
}
