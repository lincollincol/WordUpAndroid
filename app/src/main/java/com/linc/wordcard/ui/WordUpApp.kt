package com.linc.wordcard.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.linc.wordcard.ui.component.DrawerContent
import com.linc.wordcard.ui.component.DrawerToolbar
import com.linc.wordcard.ui.menu.model.MenuItem
import com.linc.wordcard.ui.navigation.AppNavGraph
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WordUpApp() {
    WordUpTheme {
        val menuItems = listOf(MenuItem.Bookmarks, MenuItem.Collections)
        val systemUiController = rememberSystemUiController()
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var selectedMenuItemState by remember { mutableStateOf<MenuItem>(MenuItem.Bookmarks) }
        var topBarVisibleState by rememberSaveable { mutableStateOf(false) }

        topBarVisibleState = when (navBackStackEntry?.destination?.route) {
            AppScreen.SignUp.route,
            AppScreen.SignIn.route -> false
            else -> true
        }

        systemUiController.setSystemBarsColor(color = AppTheme.colors.primarySurfaceColor)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                if(topBarVisibleState) {
                    DrawerToolbar(title = "Words", elevation = AppTheme.dimens.paddingMedium) {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }
                }
            },
            drawerContent = {
                DrawerContent(
                    selectedItem = selectedMenuItemState,
                    items = menuItems,
                    onItemClick = { selectedMenuItemState = it }
                )
            },
            drawerGesturesEnabled = topBarVisibleState
        ) {
            AppNavGraph(navHostController = navController)
        }
    }
}