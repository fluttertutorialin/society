package presentation.screen.auth.otp

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel

class OtpScreenModel(private val userId: String,
                     private val phone: String) : BaseScreenModel<OtpScreenUIState, OtpScreenUIEffect>(
    OtpScreenUIState()
),
    OtpScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope

    override fun onOtpChanged(otp: String, isFilled: Boolean) {
        updateState { it.copy(otp = otp) }

        if (isFilled) {
            onOtpSuccess()
        }
    }

    override fun onBackButtonClicked() {
        sendNewEffect(OtpScreenUIEffect.NavigateBack)
    }

    private fun onOtpSuccess() {
        //Get the userId and phone number from sign-up screen
        clearErrors()
        sendNewEffect(OtpScreenUIEffect.NavigateToHome)
    }

    private fun showSnackBar(message: String) {
        viewModelScope.launch {
            updateState { it.copy(snackBarMessage = message, showSnackBar = true) }
            delay(2000) // wait for sank-bar to show
            updateState { it.copy(showSnackBar = false) }
            delay(300) // wait for sank-bar to hide
            updateState { it.copy(snackBarMessage = "") }
        }
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isOtpError = false,
                isLoading = false
            )
        }
    }
}
