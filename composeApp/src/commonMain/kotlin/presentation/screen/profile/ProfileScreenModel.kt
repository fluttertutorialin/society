package presentation.screen.profile

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState
import util.AuthorizationException

class ProfileScreenModel: BaseScreenModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()), ProfileInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope

    init {
        checkIfLoggedIn()
    }

    private fun checkIfLoggedIn() {

    }

    private fun getUserProfile() {

    }

    private fun onCheckIfLoggedInSuccess(accessToken: Flow<String>) {
        screenModelScope.launch {
            accessToken.distinctUntilChanged().collectLatest { token ->
                if (token.isNotEmpty()) {
                    updateState { it.copy(isLoggedIn = true) }
                    getUserProfile()
                } else {
                    updateState { it.copy(isLoggedIn = false) }
                }
            }
        }
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoggedIn = false) }
    }

    override fun onFullNameChanged(fullName: String) {
        updateState { it.copy(fullName = fullName) }
        checkValidate {
            clearErrors()
        }
    }

    override fun onPhoneNumberChanged(phone: String) {
        updateState { it.copy(phoneNumber = phone) }
        checkValidate {
            clearErrors()
        }
    }

    override fun onSaveProfileInfo() {
        updateState { it.copy(isLoading = true) }
    }

    private fun onUpdateProfileError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false) }
    }

    override fun onLogout() {
        updateState { it.copy(isLoggedIn = false) }
        viewModelScope.launch {

        }
    }

    override fun onClickLogin() {
        sendNewEffect(ProfileUIEffect.NavigateToLoginScreen)
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isFullNameError = false,
                isPhoneNumberError = false,
                isButtonEnabled = it.isNameOrPhoneChange()
            )
        }
    }

    private fun checkValidate(block: () -> Unit) {
        try {
            block()
        } catch (e: AuthorizationException.InvalidFullNameException) {
            updateState {
                it.copy(isFullNameError = true, isButtonEnabled = false)
            }
        } catch (e: AuthorizationException.InvalidPhoneException) {
            updateState {
                it.copy(isPhoneNumberError = true, isButtonEnabled = false)
            }
        }
    }
}
