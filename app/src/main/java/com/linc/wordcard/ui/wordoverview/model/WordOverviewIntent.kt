package com.linc.wordcard.ui.wordoverview.model

sealed interface WordOverviewIntent

object NextClick : WordOverviewIntent
object PreviousClick : WordOverviewIntent
object EditClick : WordOverviewIntent
object LearnClick : WordOverviewIntent
object BookmarkClick : WordOverviewIntent
