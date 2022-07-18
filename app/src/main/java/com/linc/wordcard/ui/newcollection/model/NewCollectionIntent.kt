package com.linc.wordcard.ui.newcollection.model

import android.net.Uri

sealed interface NewCollectionIntent

data class NameChange(val value: String) : NewCollectionIntent
data class DocumentChange(val value: Uri?) : NewCollectionIntent