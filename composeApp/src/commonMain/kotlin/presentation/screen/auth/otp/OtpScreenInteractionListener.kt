package presentation.screen.auth.otp

import presentation.base.BaseInteractionListener

interface OtpScreenInteractionListener : BaseInteractionListener {
    fun onOtpChanged(otp: String, isFilled: Boolean)
    fun onBackButtonClicked()
}