package com.linc.wordcard.ui.signin

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navController: NavController
) {
    Button(onClick = { viewModel.loadUsers() }) {
        Text(text = "load")
    }
}