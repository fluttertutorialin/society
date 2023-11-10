package presentation.screen.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import getNavigationBarPadding
import getStatusBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.base.BaseScreen
import presentation.composable.BackgroundContainerComposable
import presentation.composable.ButtonFilledComposable
import presentation.composable.HeadFirstCardComposable
import presentation.composable.SnackBarComposable
import presentation.composable.TextFieldComposable
import presentation.composable.modifier.noRippleEffect
import presentation.screen.auth.otp.OtpScreen
import resources.Resources
import theme.Theme

class RegistrationScreen : BaseScreen<RegistrationScreenModel, RegistrationUIState, RegistrationScreenEffect, RegistrationInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    override fun onRender(
        state: RegistrationUIState,
        listener: RegistrationInteractionListener
    ) {
        BackgroundContainerComposable {
            Row(
                Modifier.padding(getStatusBarPadding()).height(56.dp).fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier.size(40.dp).clip(RoundedCornerShape(Theme.radius.medium))
                        .background(color = Theme.colors.surface)
                        .noRippleEffect { listener.onBackButtonClicked() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Resources.images.iconBack),
                        contentDescription = null,
                        tint = Theme.colors.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            HeadFirstCardComposable(
                textHeader = Resources.strings.joinBpToday,
                textSubHeader = Resources.strings.createYourAccount
            ) {
                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    text = state.username,
                    onValueChange = listener::onUsernameChanged,
                    label = "Username",
                    keyboardType = KeyboardType.Text,
                    errorMessage = if (state.isUsernameError) Resources.strings.invalidUsername else "",
                    isError = state.isUsernameError,
                )

                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    text = state.email,
                    onValueChange = listener::onEmailChanged,
                    label = "Email",
                    errorMessage = if (state.isEmailError) Resources.strings.invalidEmail else "",
                    isError = state.isEmailError,
                )
                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    text = state.phone,
                    onValueChange = listener::onPhoneChanged,
                    label = "Mobile",
                    keyboardType = KeyboardType.Phone,

                    errorMessage = if (state.isPhoneError) Resources.strings.invalidPhoneNumber else "",
                    isError = state.isPhoneError,
                )
                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    text = state.password,
                    onValueChange = listener::onPasswordChanged,
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    errorMessage = if (state.isPasswordError) Resources.strings.invalidPassword else "",
                    isError = state.isPasswordError,
                )
                ButtonFilledComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    title = Resources.strings.submit,
                    onClick = listener::onNextButtonClicked,
                    isLoading = state.isLoading,
                )
            }

            AnimatedVisibility(
                visible = state.showSnackBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                SnackBarComposable(icon = painterResource(Resources.images.icError),modifier = Modifier
                    .padding(bottom = getNavigationBarPadding().calculateBottomPadding())) {
                    Text(
                        text = state.snackBarMessage,
                        style = Theme.typography.body.copy(color = Theme.colors.contentPrimary),
                    )
                }
            }
        }
    }

    override fun onEffect(effect: RegistrationScreenEffect, navigator: Navigator) {
        when (effect) {
            is RegistrationScreenEffect.NavigateBack -> navigator.pop()
            is RegistrationScreenEffect.NavigateToSubmitRegistrationScreen -> navigator.push(
                OtpScreen(
                    userId = effect.userId,
                    mobile = effect.mobile
                )
            )
        }
    }
}





