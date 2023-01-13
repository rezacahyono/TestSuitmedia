package com.rchyn.testsuitmedia.utils

import android.view.Window
import androidx.core.view.WindowCompat


fun Window.setWindowFullBackground(fitSystem: Boolean = false) {
    WindowCompat.setDecorFitsSystemWindows(this, fitSystem)
}

fun mergeName(firstName: String,lastName: String): String{
    return firstName.plus(" ").plus(lastName)
}