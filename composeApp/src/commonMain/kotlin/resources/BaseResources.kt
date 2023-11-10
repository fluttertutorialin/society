package resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import theme.AppTheme

private val localDrawableResources = staticCompositionLocalOf { DrawableLightResources() }
private val localStringResources = staticCompositionLocalOf { StringResources() }

@Composable
fun BaseTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val drawableResources = if (useDarkTheme) DrawableDarkResources else DrawableLightResources()

    CompositionLocalProvider(
        localDrawableResources provides drawableResources,
        localStringResources provides StringResources(),
    ) {
        AppTheme {
            //SetInsetsController(useDarkTheme)
            content()
        }
    }
}

object Resources {
    val images: DrawableLightResources
        @Composable
        @ReadOnlyComposable
        get() = localDrawableResources.current

    val strings: StringResources
        @Composable
        @ReadOnlyComposable
        get() = localStringResources.current
}