package com.linc.wordcard.ui.collectionoverview.model

sealed interface WordIntent

data class LoadCollection(val collectionId: String) : WordIntent
object NextWordClick : WordIntent
object PreviousWordClick : WordIntent
object EditWordClick : WordIntent
object LearnClick : WordIntent
object BookmarkClick : WordIntent
