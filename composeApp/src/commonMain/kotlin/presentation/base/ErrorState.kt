package presentation.base

import util.AuthorizationException
import util.AuthorizationException.*
import util.InternetException

sealed interface ErrorState {
    // region Authorization
    data object UnAuthorized : ErrorState
    data object InvalidUsername : ErrorState
    data object InvalidPassword : ErrorState
    data object InvalidFullName : ErrorState
    data object InvalidEmail : ErrorState
    data object InvalidPhone : ErrorState
    data object InvalidAddress : ErrorState
    data class UserAlreadyExists(val message: String) : ErrorState
    data class UserNotFound(val message: String) : ErrorState
    data class WrongPassword(val message: String) : ErrorState
    data object LocationPermissionDenied : ErrorState
    // endregion

    // region Internet
    data object WifiDisabled : ErrorState
    data object NoInternet : ErrorState
    data object NetworkNotSupported : ErrorState
    data object RequestFailed : ErrorState
    // endregion
}


fun handelInternetException(
    exception: InternetException,
    onError: (t: ErrorState) -> Unit,
) {
    when (exception) {
        is InternetException.NetworkNotSupportedException -> onError(ErrorState.NetworkNotSupported)
        is InternetException.NoInternetException -> onError(ErrorState.NoInternet)
        is InternetException.WifiDisabledException -> onError(ErrorState.WifiDisabled)
    }
}

fun handelAuthorizationException(
    exception: AuthorizationException,
    onError: (t: ErrorState) -> Unit,
) {
    when (exception) {
        is UnAuthorizedException -> onError(ErrorState.RequestFailed)
        is InvalidUsernameException -> onError(ErrorState.InvalidUsername)
        is InvalidPasswordException -> onError(ErrorState.InvalidPassword)
        is InvalidCredentialsException -> onError(
            ErrorState.WrongPassword(
                exception.errorMessage
            )
        )

        is UserAlreadyExistException -> onError(
            ErrorState.UserAlreadyExists(
                exception.errorMessage
            )
        )

        is UserNotFoundException -> onError(ErrorState.UserNotFound(exception.errorMessage))
        InvalidEmailException -> onError(ErrorState.InvalidEmail)
        InvalidFullNameException -> onError(ErrorState.InvalidFullName)
        InvalidPhoneException -> onError(ErrorState.InvalidPhone)
        InvalidAddressException -> onError(ErrorState.InvalidAddress)
        is LocationAccessDeniedException -> onError(ErrorState.LocationPermissionDenied)
    }
}