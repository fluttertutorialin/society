package presentation.screen.auth.signup

data class RegistrationUIState(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isUsernameError: Boolean = false,
    val isPhoneError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val snackBarMessage: String = "",
    val showSnackBar: Boolean = false,
)
