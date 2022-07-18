package com.linc.wordcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.linc.wordcard.extension.navigate
import com.linc.wordcard.ui.collections.CollectionsScreen
import com.linc.wordcard.ui.collections.CollectionsViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.newcollection.NewCollectionScreen
import com.linc.wordcard.ui.newcollection.NewCollectionViewModel
import com.linc.wordcard.ui.word.WordScreen
import com.linc.wordcard.ui.word.WordViewModel
import com.linc.wordcard.ui.word.model.WordUiState

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    startDestination: AppScreen = AppScreen.Collections,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier
            .then(modifier),
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(route = AppScreen.Collections.route) {
            val viewModel = hiltViewModel<CollectionsViewModel>()
            viewModel.observeNavRoute(navHostController::navigate)
            CollectionsScreen(
                state = viewModel.uiState,
                onIntent = viewModel::obtainIntent
            )
        }
        composable(route = AppScreen.NewCollection.route) {
            val viewModel = hiltViewModel<NewCollectionViewModel>()
            viewModel.observeNavRoute(navHostController::navigate)
            NewCollectionScreen(
                state = viewModel.uiState,
                onIntent = viewModel::obtainIntent
            )
        }
        composable(
            route = AppScreen.Card.route
        ) {
            val wordId = requireNotNull(it.arguments?.getString(AppScreen.Card.WORD_ID_ARG))
            val viewModel = hiltViewModel<WordViewModel>()
            WordScreen(
                wordId = wordId,
                state = viewModel.uiState,
                onIntent = {}
            )
        }
        composable(AppScreen.Bookmarks.route) {

        }
    }
}