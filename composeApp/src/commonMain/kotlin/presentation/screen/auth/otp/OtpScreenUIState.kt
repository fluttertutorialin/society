package presentation.screen.auth.otp

data class OtpScreenUIState(
    val otp: String = "",
    val isOtpError: Boolean = false,
    val snackBarMessage: String = "",
    val showSnackBar: Boolean = false,
    val isLoading:Boolean = false,
)
