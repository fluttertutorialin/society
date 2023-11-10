package presentation.screen.profile

sealed class ProfileUIEffect {
    data object NavigateToLoginScreen : ProfileUIEffect()
}
