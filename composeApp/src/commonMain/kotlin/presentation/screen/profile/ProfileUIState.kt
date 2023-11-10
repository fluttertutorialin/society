package presentation.screen.profile

data class ProfileUIState(
    val user: UserUIState = UserUIState(),
    val fullName: String = "",
    val phoneNumber: String = "",
    val snackBarMessage: String = "",
    val showSnackBar: Boolean = false,
    val isFullNameError: Boolean = false,
    val isPhoneNumberError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoggedIn: Boolean = true,
    val isLoading: Boolean = false
)

data class UserUIState(
    val address: String = "",
    val email: String = "",
    val id: String = "",
    val username: String = "",
    val walletBalance: Double = 0.0,
    val fullName: String = "",
    val phoneNumber: String = "",
    val currency: String = ""
)

fun ProfileUIState.isNameOrPhoneChange(): Boolean {
    return (fullName != user.fullName || phoneNumber != user.phoneNumber)
}

data class AddressUIState(
    val address: String = "",
    val id: String = "",
    val location: LocationUIState? = LocationUIState()
)

data class LocationUIState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

