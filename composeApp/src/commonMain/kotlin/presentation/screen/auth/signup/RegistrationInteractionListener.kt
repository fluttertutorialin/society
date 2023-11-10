package presentation.screen.auth.signup

import presentation.base.BaseInteractionListener

interface RegistrationInteractionListener : BaseInteractionListener {
    fun onUsernameChanged(username: String)
    fun onEmailChanged(email: String)
    fun onPhoneChanged(phone: String)
    fun onPasswordChanged(password: String)
    fun onNextButtonClicked()
    fun onBackButtonClicked()
}