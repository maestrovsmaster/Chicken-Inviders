package com.example.chickeninviders.utils

import android.content.Context
import android.content.pm.PackageManager


fun isFireOS(context: Context): Boolean {
    val pm: PackageManager = context.packageManager
    return try {
        pm.getPackageInfo("com.amazon.venezia", PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}