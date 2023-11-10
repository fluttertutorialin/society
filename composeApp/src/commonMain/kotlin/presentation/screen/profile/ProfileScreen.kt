package presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import getStatusBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.base.BaseScreen
import presentation.composable.ButtonFilledComposable
import presentation.composable.ContentVisibilityComposable
import presentation.composable.LoginRequiredPlaceholder
import presentation.composable.TextFieldComposable
import presentation.composable.modifier.noRippleEffect
import presentation.screen.auth.login.LoginScreen
import resources.Resources
import theme.Theme
import util.root

class ProfileScreen :
    BaseScreen<ProfileScreenModel, ProfileUIState, ProfileUIEffect, ProfileInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    override fun onEffect(effect: ProfileUIEffect, navigator: Navigator) {
        when (effect) {
            ProfileUIEffect.NavigateToLoginScreen -> navigator.root?.push(LoginScreen())
        }
    }

    @OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun onRender(state: ProfileUIState, listener: ProfileInteractionListener) {
        LoginRequiredPlaceholder(
            placeHolder = painterResource(Resources.images.logout),
            message = "",
            onClickLogin = listener::onClickLogin
        )
        ContentVisibilityComposable(true) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(getStatusBarPadding())
                    .background(Theme.colors.background)
                    .verticalScroll(rememberScrollState()),
            ) {
                WhiteCard {
                    Title("Pending payment")
                    SubTitle(
                        "${state.user.currency} ${state.user.walletBalance}",
                        Theme.colors.primary
                    )
                }
                WhiteCard {
                    TextFieldComposable(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        text = state.fullName,
                        onValueChange = listener::onFullNameChanged,
                        label = "Email",
                        keyboardType = KeyboardType.Text,
                        errorMessage = if (state.isFullNameError) "" else "",
                        isError = state.isFullNameError,
                    )
                    TextFieldComposable(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        text = state.phoneNumber,
                        onValueChange = listener::onPhoneNumberChanged,
                        label = "Mobile number",
                        keyboardType = KeyboardType.Text,
                        errorMessage = if (state.isPhoneNumberError) Resources.strings.invalidPhoneNumber else "",
                        isError = state.isPhoneNumberError,
                    )
                    TextFieldComposable(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        text = state.fullName,
                        onValueChange = listener::onFullNameChanged,
                        label = "Address",
                        keyboardType = KeyboardType.Text,
                        errorMessage = if (state.isFullNameError) "" else "",
                        isError = state.isFullNameError,
                    )
                    TextFieldComposable(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        text = state.fullName,
                        onValueChange = listener::onFullNameChanged,
                        label = "Password",
                        keyboardType = KeyboardType.Text,
                        errorMessage = if (state.isFullNameError) "" else "",
                        isError = state.isFullNameError,
                    )
                    ButtonFilledComposable(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        title = "Save",
                        enabled = state.isButtonEnabled,
                        isLoading = state.isLoading,
                        onClick = listener::onSaveProfileInfo,
                    )
                }
                WhiteCard {
                    Row(
                        modifier = Modifier.noRippleEffect(listener::onLogout),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Icon(
                            painter = painterResource(Resources.images.logout),
                            contentDescription = "Logout",
                            tint = Theme.colors.primary,
                        )
                        Text(
                            text = "Logout",
                            style = Theme.typography.title,
                            color = Theme.colors.primary,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun WhiteCard(content: @Composable () -> Unit) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .clip(shape = RoundedCornerShape(Theme.radius.medium))
                .background(Theme.colors.surface)
                .padding(16.dp)
        ) {
            content()
        }
    }

    @Composable
    private fun Title(text: String) {
        Text(
            text = text,
            style = Theme.typography.caption,
            color = Theme.colors.contentTertiary,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
    }

    @Composable
    private fun SubTitle(text: String, color: Color = Theme.colors.contentPrimary) {
        Text(
            text = text,
            color = color,
            style = Theme.typography.body,
        )
    }
}
