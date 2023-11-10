package presentation.screen.auth.login

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import domain.entity.Block
import domain.usercase.IExploreBlockUseCaseGetBlockUseCase
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class LoginScreenModel(private val manageApartment: IExploreBlockUseCaseGetBlockUseCase) : BaseScreenModel<LoginScreenUIState, LoginScreenUIEffect>(
    LoginScreenUIState()
),
    LoginScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope

    /*init {
        getData()
    }*/

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            manageApartment::getBlocks,
            ::onGetApartmentSuccess,
            ::onLoginError
        )
    }

    private fun onGetApartmentSuccess(blocksList: List<Block>) {
        println(blocksList.size)
    }

    override fun onUsernameChanged(username: String) {
        updateState { it.copy(username = username) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { it.copy(password = password) }
    }

    override fun onKeepLoggedInChecked() {
        updateState { it.copy(keepLoggedIn = !it.keepLoggedIn) }
    }

    override fun onClickLogin(username: String, password: String, keepLoggedIn: Boolean) {
        updateState { it.copy(isLoading = true) }
        //onLoginSuccess(true)

        getData()
    }

    private fun onLoginSuccess(isLoggedIn: Boolean) {
        clearErrors()
        if (isLoggedIn) {
            sendNewEffect(LoginScreenUIEffect.NavigateToHome)
        }
    }

    private fun onLoginError(errorState: ErrorState) {
        clearErrors()
        when (errorState) {
            ErrorState.InvalidPassword -> updateState { it.copy(isPasswordError = true) }

            ErrorState.InvalidUsername -> updateState { it.copy(isUsernameError = true) }

            is ErrorState.WrongPassword -> {
                showSnackBar(errorState.message)
            }

            is ErrorState.UserNotFound -> {
                showSnackBar(errorState.message)
            }

            else -> { showSnackBar("$errorState") }
        }
    }

    private fun showSnackBar(message: String) {
        viewModelScope.launch {
            updateState { it.copy(snackbarMessage = message, showSnackbar = true) }
            delay(2000) // wait for sank-bar to show
            updateState { it.copy(showSnackbar = false) }
            delay(300) // wait for sank-bar to hide
            updateState { it.copy(snackbarMessage = "") }
        }
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isUsernameError = false,
                isPasswordError = false,
                isLoading = false
            )
        }
    }

    override fun onClickSignUp() {
        sendNewEffect(LoginScreenUIEffect.NavigateToSignup)
    }

}
