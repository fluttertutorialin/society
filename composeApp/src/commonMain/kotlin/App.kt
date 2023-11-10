import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screen.auth.login.LoginScreen
import resources.BaseTheme

@Composable
fun App() {
    MainApp.Content()
}

object MainApp : Screen {
    @Composable
    override fun Content() {
        BaseTheme() { //BaseTheme //MaterialTheme
            Navigator(LoginScreen()) { SlideTransition(it) }
        }
    }
}