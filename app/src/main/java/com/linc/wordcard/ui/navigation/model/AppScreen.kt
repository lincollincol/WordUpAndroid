package com.linc.wordcard.ui.navigation.model

sealed class AppScreen(val route: String) {

    object Splash : AppScreen("splash")

    object SignIn : AppScreen("sign-in")

    object SignUp : AppScreen("sign-up")

    object Main : AppScreen("main")

    object Collections : AppScreen("collections")

    object NewCollection : AppScreen("new-collection")

    object Bookmarks : AppScreen("bookmarks")

    object CollectionOverview : AppScreen("collection-overview/{id}") {
        const val ID_ARG = "id"
        fun createRoute(id: String) = "collection-overview/$id"
    }

}