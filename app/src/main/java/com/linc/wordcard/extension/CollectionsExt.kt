package com.linc.wordcard.extension

fun <T> Collection<T>.next(node: T): T? =
    elementAtOrNull(indexOf(node) + 1) ?: firstOrNull()

fun <T> Collection<T>.previous(node: T): T? =
    elementAtOrNull(indexOf(node) - 1) ?: lastOrNull()

fun <T> Collection<T>.nextOrNull(node: T): T? =
    elementAtOrNull(indexOf(node) + 1)

fun <T> Collection<T>.previousOrNull(node: T): T? =
    elementAtOrNull(indexOf(node) - 1)
