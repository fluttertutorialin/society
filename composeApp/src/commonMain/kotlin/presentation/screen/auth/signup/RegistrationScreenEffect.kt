package presentation.screen.auth.signup

sealed interface RegistrationScreenEffect {
    data class NavigateToSubmitRegistrationScreen(
        val userId: String,
        val mobile: String,
    ) : RegistrationScreenEffect

    data object NavigateBack : RegistrationScreenEffect
}
