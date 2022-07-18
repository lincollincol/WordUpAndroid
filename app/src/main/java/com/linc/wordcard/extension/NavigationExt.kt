package com.linc.wordcard.extension

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.linc.wordcard.ui.navigation.model.*

fun NavHostController.navigate(command: NavCommand) {
    when(command) {
        is Forward -> navigate(command.route) { launchSingleTop = true }
        is NewRoot -> navigate(command.route) {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
            }
        }
        is Replace -> currentDestination?.route?.let { previous ->
            navigate(command.route) {
                launchSingleTop = true
                popUpTo(previous) {
                    inclusive = true
                }
            }
        } ?: return navigate(command.route)
        is Back -> popBackStack()
        is BackTo -> popBackStack(command.route, inclusive = false)
    }
}