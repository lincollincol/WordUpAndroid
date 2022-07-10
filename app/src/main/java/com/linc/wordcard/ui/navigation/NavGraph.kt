package com.linc.wordcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.linc.wordcard.ui.word.WordScreen
import com.linc.wordcard.ui.collections.CollectionsScreen
import com.linc.wordcard.ui.collections.CollectionsViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.newcollection.NewCollectionScreen
import com.linc.wordcard.ui.newcollection.NewCollectionViewModel
import com.linc.wordcard.ui.word.WordViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: AppScreen = AppScreen.Collections,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier
            .then(modifier),
        navController = navHostController,
        startDestination = startDestination.name
    ) {
        composable(route = AppScreen.Collections.name) {
            val viewModel = hiltViewModel<CollectionsViewModel>()
            CollectionsScreen(
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(route = AppScreen.NewCollection.name) {
            val viewModel = hiltViewModel<NewCollectionViewModel>()
            NewCollectionScreen(
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(
            route = AppScreen.Card.name + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) {
            val wordId = it.arguments?.getString("id")!!
            val viewModel = hiltViewModel<WordViewModel>()
            WordScreen(
                wordId = wordId,
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(AppScreen.Bookmarks.name) {}
    }
}