package presentation.screen.notification

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import domain.entity.NotificationHistory
import domain.usercase.IGetTransactionHistoryUseCase
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class NotificationScreenModel(
    private val transaction: IGetTransactionHistoryUseCase,
) : BaseScreenModel<NotificationsUiState, NotificationUiEffect>(NotificationsUiState()),
    NotificationInteractionListener {
    override val viewModelScope: CoroutineScope = screenModelScope

    init {
        viewModelScope.launch {
            getNotificationHistory()
        }
    }

    private fun checkIfLoggedIn() {

    }

    private fun onCheckIfLoggedInSuccess(accessToken: Flow<String>) {
        screenModelScope.launch {
            accessToken.collect { token ->
                if (token.isNotEmpty()) {
                    updateState { it.copy(isLoggedIn = true) }
                } else {
                    updateState { it.copy(isLoggedIn = false) }
                }
            }
        }
    }

    private fun onCheckIfLoggedInError(errorState: ErrorState) {
        updateState { it.copy(isLoggedIn = false) }
    }

    private suspend fun getNotificationHistory() {

        tryToExecute(
            function = transaction::getNotificationHistory,
            onSuccess = ::onGetNotificationHistorySuccess,
            onError = ::onGetNotificationHistoryError
        ).join()


    }

    private fun onGetNotificationHistoryInLast24HoursSuccess(notifications: List<NotificationHistory>) {
        updateState { it.copy(todayNotifications = notifications.toUiState()) }
    }

    private fun onGetNotificationHistorySuccess(notification: Flow<PagingData<NotificationHistory>>) {
        updateState {
            it.copy(notifications = notification.toUiState())
        }
    }

    private fun onGetNotificationHistoryError(errorState: ErrorState) {

    }

    override fun onClickNotification(topicId: String, sender: Int) {
        when (NotificationHistory.getNotificationSender(sender)) {
            NotificationHistory.NotificationSender.RESTAURANT -> {
                sendNewEffect(NotificationUiEffect.NavigateToTrackFoodOrder(topicId))
            }

            NotificationHistory.NotificationSender.DELIVERY -> {
                sendNewEffect(NotificationUiEffect.NavigateToTrackDelivery(topicId))

            }

            NotificationHistory.NotificationSender.TAXI -> {
                sendNewEffect(NotificationUiEffect.NavigateToTaxiRide(topicId))
            }

            NotificationHistory.NotificationSender.UNDEFINED -> {}
        }
    }

    override fun onClickLogin() {
        sendNewEffect(NotificationUiEffect.NavigateToLoginScreen)
    }

}
