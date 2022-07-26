package com.linc.wordcard.ui.collectionoverview.model

sealed interface WordIntent

object NextClick : WordIntent
object PreviousClick : WordIntent
object EditClick : WordIntent
object LearnClick : WordIntent
object BookmarkClick : WordIntent
