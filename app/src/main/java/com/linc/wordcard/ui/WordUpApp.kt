package com.linc.wordcard.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.linc.wordcard.ui.component.DrawerContent
import com.linc.wordcard.ui.component.DrawerToolbar
import com.linc.wordcard.ui.menu.model.MenuItem
import com.linc.wordcard.ui.navigation.AppNavGraph
import com.linc.wordcard.ui.navigation.MainNavGraph
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WordUpApp() {
    WordUpTheme {
        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(color = AppTheme.colors.primarySurfaceColor)
        Surface {
            AppNavGraph(navHostController = rememberNavController())
        }
    }
}