package com.linc.wordcard.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//fun <T> debounce(
//    waitMs: Long = 300L,
//    coroutineScope: CoroutineScope,
//    destinationFunction: (T) -> Unit
//): (T) -> Unit {
//    var debounceJob: Job? = null
//    return { param: T ->
//        debounceJob?.cancel()
//        debounceJob = coroutineScope.launch {
//            delay(waitMs)
//            destinationFunction(param)
//        }
//    }
//}

fun throttleFirst(
    skipMs: Long = 500L,
    coroutineScope: CoroutineScope,
    destinationFunction: () -> Unit
): () -> Unit {
    var throttleJob: Job? = null
    return {
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                destinationFunction()
                delay(skipMs)
            }
        }
    }
}

fun debounce(
    waitMs: Long = 2000L,
    coroutineScope: CoroutineScope,
    destinationFunction: () -> Unit
): () -> Unit {
    var debounceJob: Job? = null
    return {
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            destinationFunction()
        }
    }
}