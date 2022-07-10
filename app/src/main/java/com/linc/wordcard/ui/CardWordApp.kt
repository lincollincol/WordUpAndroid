package com.linc.wordcard.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.linc.wordcard.ui.component.DrawerContent
import com.linc.wordcard.ui.component.DrawerToolbar
import com.linc.wordcard.ui.menu.model.MenuItem
import com.linc.wordcard.ui.navigation.AppNavGraph
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordCardTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardWordApp() {
    WordCardTheme {

        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()
        val menuItems = remember {
            mutableStateListOf(MenuItem.Bookmarks, MenuItem.Collections)
        }
        var selectedMenuItem by remember { mutableStateOf<MenuItem>(MenuItem.Bookmarks) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                DrawerToolbar(title = "Words", elevation = AppTheme.dimens.paddingMedium) {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }
            },
            drawerContent = {
                DrawerContent(
                    selectedItem = selectedMenuItem,
                    items = menuItems,
                    onItemClick = { selectedMenuItem = it }
                )
            }
        ) {
            AppNavGraph(navHostController = navController)
        }
    }
}