package com.linc.wordcard.ui.signin

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.linc.wordcard.ui.component.AppButton
import com.linc.wordcard.ui.component.AppTextField
import com.linc.wordcard.ui.signin.model.LoginChange
import com.linc.wordcard.ui.signin.model.PasswordChange
import com.linc.wordcard.ui.signin.model.*
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun SignInScreen(
    state: SignInUiState,
    onIntent: (SignInIntent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (headline, fields, register) = createRefs()
        Text(
            modifier = Modifier
                .padding(AppTheme.dimens.paddingLarge)
                .constrainAs(headline) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = fields.top,
                        horizontalBias = 0F
                    )
                },
            text = "WordUp",
            style = AppTheme.typographies.h4,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .padding(AppTheme.dimens.paddingLarge)
                .constrainAs(fields) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        start = parent.start,
                        end = parent.end
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.login,
                hint = "login",
                startIcon = Icons.Outlined.Mail,
                onValueChange = { onIntent(LoginChange(it)) }
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                hint = "password",
                startIcon = Icons.Outlined.Lock,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { onIntent(PasswordChange(it)) }
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign In",
                onClick = { onIntent(SignInClick) }
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(register) {
                    bottom.linkTo(parent.bottom)
                    linkTo(start = parent.start, end = parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Don't have an account?")
            Text(
                modifier = Modifier
                    .clip(AppTheme.shapes.medium)
                    .clickable(
                        indication = rememberRipple(
                            bounded = true,
                            color = AppTheme.colors.primarySurfaceColor
                        ),
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onIntent(SignUpClick) }
                    )
                    .padding(AppTheme.dimens.paddingMedium),
                text = "Sign Up",
                style = AppTheme.typographies.button.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
fun SignInScreenPreview() {
    WordUpTheme {
        SignInScreen(
            state = SignInUiState(),
            onIntent = {},
        )
    }
}