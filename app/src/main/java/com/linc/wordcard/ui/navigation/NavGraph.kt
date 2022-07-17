package com.linc.wordcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.linc.wordcard.ui.word.WordScreen
import com.linc.wordcard.ui.collections.CollectionsScreen
import com.linc.wordcard.ui.collections.CollectionsViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.newcollection.NewCollectionScreen
import com.linc.wordcard.ui.newcollection.NewCollectionViewModel
import com.linc.wordcard.ui.signin.SignInScreen
import com.linc.wordcard.ui.signin.SignInViewModel
import com.linc.wordcard.ui.signup.SignUpScreen
import com.linc.wordcard.ui.signup.SignUpViewModel
import com.linc.wordcard.ui.word.WordViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: AppScreen = AppScreen.SignIn,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier
            .then(modifier),
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        addAuthGraph(navHostController)
        composable(route = AppScreen.Collections.route) {
            val viewModel = hiltViewModel<CollectionsViewModel>()
            CollectionsScreen(
                state = viewModel.uiState,
                onLoadCollections = { viewModel.loadCollections() },
                onCollectionClick = { navHostController.navigate(AppScreen.Card.createRoute(it)) },
                onNewCollectionClick = { navHostController.navigate(AppScreen.NewCollection.route) }
            )
        }
        composable(route = AppScreen.NewCollection.route) {
            val viewModel = hiltViewModel<NewCollectionViewModel>()
            NewCollectionScreen(
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(
            route = AppScreen.Card.route
        ) {
            val wordId = requireNotNull(it.arguments?.getString(AppScreen.Card.WORD_ID_ARG))
            val viewModel = hiltViewModel<WordViewModel>()
            WordScreen(
                wordId = wordId,
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(AppScreen.Bookmarks.route) {

        }
    }
}

fun NavGraphBuilder.addAuthGraph(
    navHostController: NavHostController
) {
    composable(route = AppScreen.SignIn.route) {
        val viewModel = hiltViewModel<SignInViewModel>()
        SignInScreen(
            state = viewModel.uiState,
            onIntent = viewModel::obtainIntent,
            navigate = navHostController::navigate
        )
    }
    composable(route = AppScreen.SignUp.route) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        SignUpScreen(
            state = viewModel.uiState,
            onIntent = viewModel::obtainIntent,
            navigate = { navHostController.navigate(it) },
        )
    }
}