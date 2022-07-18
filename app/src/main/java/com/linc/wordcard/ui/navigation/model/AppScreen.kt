package com.linc.wordcard.ui.navigation.model

sealed class AppScreen(val route: String) {

    object SignIn : AppScreen("sign_in")

    object SignUp : AppScreen("sign_up")

    object Main : AppScreen("main")

    object Collections : AppScreen("collections")

    object NewCollection : AppScreen("new_collection")

    object Bookmarks : AppScreen("bookmarks")

    object Card : AppScreen("card/{wordId}") {
        const val WORD_ID_ARG = "wordId"
        fun createRoute(id: String) = "card/$id"
    }

}