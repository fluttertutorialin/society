package presentation.screen.auth.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import getNavigationBarPadding
import getStatusBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.parameter.parametersOf
import presentation.base.BaseScreen
import presentation.composable.BackgroundContainerComposable
import presentation.composable.DigitsInputComposable
import presentation.composable.HeadFirstCardComposable
import presentation.composable.SnackBarComposable
import presentation.composable.TextButtonComposable
import presentation.composable.modifier.noRippleEffect
import presentation.screen.main.MainContainer
import resources.Resources
import theme.Theme

class OtpScreen(private val userId: String,
                 private val mobile: String) :
    BaseScreen<OtpScreenModel, OtpScreenUIState, OtpScreenUIEffect, OtpScreenInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel { parametersOf(userId, mobile) })
    }

    override fun onEffect(
        effect: OtpScreenUIEffect,
        navigator: Navigator,
    ) {
        when (effect) {
            is OtpScreenUIEffect.NavigateToHome -> navigator.replaceAll(MainContainer)
            OtpScreenUIEffect.NavigateBack -> navigator.pop()
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun onRender(state: OtpScreenUIState, listener: OtpScreenInteractionListener) {
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
                textHeader = "OTP Verification",
                textSubHeader = "Enter the code from the SMS we sent to $mobile"
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                DigitsInputComposable(
                    value = state.otp,
                    onDigitsChange = listener::onOtpChanged,
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't received the code ?",
                        style = Theme.typography.caption,
                        color = Theme.colors.contentTertiary
                    )
                    TextButtonComposable(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = "Resend",
                        onClick = {},
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                    )
                }
            }

            AnimatedVisibility(
                visible = state.showSnackBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                SnackBarComposable (icon = painterResource(Resources.images.icError), modifier = Modifier
                    .padding(bottom = getNavigationBarPadding().calculateBottomPadding())) {
                    Text(
                        text = state.snackBarMessage,
                        style = Theme.typography.body.copy(color = Theme.colors.contentPrimary),
                    )
                }
            }
        }
    }
}
