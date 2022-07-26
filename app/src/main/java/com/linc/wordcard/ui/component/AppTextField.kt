package com.linc.wordcard.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    elevation: Dp = AppTheme.dimens.elevationSmall,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    val fieldShape = AppTheme.shapes.large
    TextField(
        modifier = Modifier
            .shadow(elevation, fieldShape)
            .then(modifier),
        value = value,
        shape = fieldShape,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            textColor = AppTheme.colors.secondaryContentColor,
            placeholderColor = AppTheme.colors.secondaryContentColor.copy(alpha = 0.5F),
            disabledPlaceholderColor = AppTheme.colors.secondaryContentColor.copy(alpha = 0.5F),
            cursorColor = AppTheme.colors.primarySurfaceColor,
            backgroundColor = AppTheme.colors.secondarySurfaceColor,
            trailingIconColor = AppTheme.colors.primarySurfaceColor,
            disabledTrailingIconColor = AppTheme.colors.primarySurfaceColor,
            leadingIconColor = AppTheme.colors.primarySurfaceColor,
            disabledLeadingIconColor = AppTheme.colors.primarySurfaceColor,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        readOnly = readOnly,
        singleLine = singleLine,
        enabled = enabled,
        leadingIcon = startIcon?.let { { Icon(imageVector = it, contentDescription = null) } },
        trailingIcon = endIcon?.let { { Icon(imageVector = it, contentDescription = null) } },
        placeholder = { Text(text = hint) },
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun AppTextFieldPreview() {
    WordUpTheme {
        AppTextField(value = "", hint = "hint", onValueChange = {})
    }
}