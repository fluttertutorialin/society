package presentation.screen.auth.signup

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import domain.validation.IValidationUseCase
import presentation.base.BaseScreenModel
import util.AuthorizationException

class RegistrationScreenModel(private val validation: IValidationUseCase) :
    BaseScreenModel<RegistrationUIState, RegistrationScreenEffect>(RegistrationUIState()),
    RegistrationInteractionListener {
    override val viewModelScope: CoroutineScope = screenModelScope

    // region interactions
    override fun onUsernameChanged(username: String) {
        updateState { it.copy(username = username) }
        tryCatch { validation.validateUsername(username) }
    }

    override fun onPhoneChanged(phone: String) {
        updateState { it.copy(phone = phone) }
        //tryCatch { validation.validatePhone(phone,_language.value.value) }
    }

    override fun onEmailChanged(email: String) {
        updateState { it.copy(email = email) }
        tryCatch { validation.validateEmail(email) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { it.copy(password = password) }
        tryCatch { validation.validatePassword(password) }
    }

    override fun onNextButtonClicked() {
        with(state.value) {
            tryCatch {
                sendNewEffect(
                    RegistrationScreenEffect.NavigateToSubmitRegistrationScreen(
                        "",
                        phone,
                    )
                )
            }
        }
    }

    override fun onBackButtonClicked() {
        sendNewEffect(RegistrationScreenEffect.NavigateBack)
    }

    // region private methods
    private fun tryCatch(block: () -> Unit) {
        try {
            block()
            clearErrors()
        } catch (e: AuthorizationException.InvalidUsernameException) {
            updateState { it.copy(isUsernameError = true) }
        } catch (e: AuthorizationException.InvalidEmailException) {
            updateState {
                it.copy(isEmailError = true)
            }
        } catch (e: AuthorizationException.InvalidPasswordException) {
            updateState { it.copy(isPasswordError = true) }
        }
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isUsernameError = false,
                isPasswordError = false,
                isEmailError = false,
            )
        }
    }
    // endregion
}