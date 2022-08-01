package com.ariefzuhri.suitmediainterntest.common.util

import android.widget.EditText
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

fun EditText?.getInput(): String {
    return this?.text?.trim().toString()
}

fun ImageView?.loadAvatar(source: Any?) {
    this?.load(source) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}

fun String?.getHostName(): String {
    return this?.split("/")?.get(2).orEmpty()
}

fun String?.isPalindrome(): Boolean {
    val text = removePunctuations().lowercase()
    val reverse = text.reversed()
    return text == reverse
}

fun String?.removePunctuations(): String {
    return this?.filter { char -> char.isLetterOrDigit() }.orEmpty()
}