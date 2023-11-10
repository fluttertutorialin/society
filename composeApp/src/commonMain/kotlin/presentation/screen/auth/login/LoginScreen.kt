package presentation.screen.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import getNavigationBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.base.BaseScreen
import presentation.composable.BackgroundContainerComposable
import presentation.composable.ButtonFilledComposable
import presentation.composable.CheckBoxComposable
import presentation.composable.HeadFirstCardComposable
import presentation.composable.SignInWithButtonComposable
import presentation.composable.SnackBarComposable
import presentation.composable.TextButtonComposable
import presentation.composable.TextFieldComposable
import presentation.screen.auth.signup.RegistrationScreen
import presentation.screen.home.HomeScreen
import presentation.screen.main.MainContainer
import resources.Resources
import theme.Theme

class LoginScreen :
    BaseScreen<LoginScreenModel, LoginScreenUIState, LoginScreenUIEffect, LoginScreenInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    override fun onEffect(
        effect: LoginScreenUIEffect,
        navigator: Navigator,
    ) {
        when (effect) {
            is LoginScreenUIEffect.NavigateToHome -> navigator.replaceAll(MainContainer)
            LoginScreenUIEffect.NavigateToSignup -> navigator.push(MainContainer)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    override fun onRender(state: LoginScreenUIState, listener: LoginScreenInteractionListener) {
        BackgroundContainerComposable {
            HeadFirstCardComposable(
                textHeader = Resources.strings.loginWelcomeMessage,
                textSubHeader = Resources.strings.loginSubWelcomeMessage
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
                    text = state.password,
                    onValueChange = listener::onPasswordChanged,
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    errorMessage = if (state.isPasswordError) Resources.strings.invalidPassword else "",
                    isError = state.isPasswordError,
                )
                CheckBoxComposable(
                    modifier = Modifier.padding(top = 16.dp),
                    label = Resources.strings.keepMeLoggedIn,
                    onCheck = listener::onKeepLoggedInChecked,
                    isChecked = state.keepLoggedIn,
                    size = 24,
                    textStyle = Theme.typography.caption,
                    textColor = Theme.colors.contentSecondary
                )
                ButtonFilledComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    title = Resources.strings.login,
                    onClick = {
                        listener.onClickLogin(
                            username = state.username,
                            password = state.password,
                            keepLoggedIn = state.keepLoggedIn
                        )
                    },
                    isLoading = state.isLoading,
                )

                SignInWithButtonComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    text = "Sign in with Apple",
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal),
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    icon = painterResource(Resources.images.signWithApple),
                    onClick = {  },
                )

                SignInWithButtonComposable(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    text = "Sign in with Google",
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal),
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {},
                    icon = painterResource(Resources.images.signWithGoogle),
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account ?",
                        style = Theme.typography.caption,
                        color = Theme.colors.contentTertiary
                    )
                    TextButtonComposable(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = "Sign Up",
                        onClick = listener::onClickSignUp,
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                    )
                }
            }

            AnimatedVisibility(
                visible = state.showSnackbar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                SnackBarComposable (icon = painterResource(Resources.images.icError), modifier = Modifier
                    .padding(bottom = getNavigationBarPadding().calculateBottomPadding())) {
                    Text(
                        text = state.snackbarMessage,
                        style = Theme.typography.body.copy(color = Theme.colors.contentPrimary),
                    )
                }
            }
        }
    }
}
