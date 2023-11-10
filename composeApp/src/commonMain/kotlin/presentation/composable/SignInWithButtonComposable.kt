package presentation.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import theme.Theme

@Composable
fun SignInWithButtonComposable(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    textStyle: TextStyle = Theme.typography.button,
    enabled: Boolean = true,
    containerColor: Color,
    contentColor: Color,
    disabledContainerColor: Color = Color.LightGray,
    disabledContentColor: Color = Color.Gray,
    shape: Shape = MaterialTheme.shapes.medium,
    iconTint: Color = Color.Unspecified,
    textPadding: PaddingValues = PaddingValues(Theme.dimens.space12),
    onClick: () -> Unit,
) {
	Button(
		modifier = modifier,
		enabled = enabled,
		colors = ButtonDefaults.buttonColors(
			containerColor = containerColor,
			contentColor = contentColor,
			disabledContainerColor = disabledContainerColor,
			disabledContentColor = disabledContentColor,
		),
		onClick = onClick,
		shape = shape,
		contentPadding = textPadding,
	) {
		Icon(
			modifier = Modifier
				.padding(end = 20.dp)
				.size(24.dp),
			painter = icon,
			contentDescription = null,
			tint = iconTint,
		)
		Text(
			text = text,
			style = textStyle,
		)
	}
}
