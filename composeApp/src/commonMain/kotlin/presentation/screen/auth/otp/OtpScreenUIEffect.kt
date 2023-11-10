package presentation.screen.auth.otp

sealed class OtpScreenUIEffect {
    data object NavigateToHome : OtpScreenUIEffect()
    data object NavigateBack : OtpScreenUIEffect()
}
