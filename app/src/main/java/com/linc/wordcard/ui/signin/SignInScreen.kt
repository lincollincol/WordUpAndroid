package com.linc.wordcard.ui.signin

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.linc.wordcard.ui.component.AppButton
import com.linc.wordcard.ui.component.AppTextField
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.theme.AppTheme
import io.ktor.util.reflect.*

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navController: NavController
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
                value = "",
                hint = "login",
                startIcon = Icons.Outlined.Person,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                hint = "password",
                startIcon = Icons.Outlined.Lock,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign In"
            ) {
                navController.navigate(AppScreen.Collections.name)
            }
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
                        onClick = {

                        }
                    )
                    .padding(AppTheme.dimens.paddingMedium),
                text = "Sign Up",
                style = AppTheme.typographies.button.copy(fontWeight = FontWeight.Bold)
            )
        }
    }

    /*Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(AppTheme.dimens.paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1F))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "WordUp",
                style = AppTheme.typographies.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.1F))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                hint = "login",
                startIcon = Icons.Outlined.Person,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                hint = "password",
                startIcon = Icons.Outlined.Lock,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign In"
            ) {
                navController.navigate(AppScreen.Collections.name)
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
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
                        onClick = {

                        }
                    )
                    .padding(AppTheme.dimens.paddingMedium),
                text = "Sign Up",
                style = AppTheme.typographies.button.copy(fontWeight = FontWeight.Bold)
            )
        }
    }*/
}