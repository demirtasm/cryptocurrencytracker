package com.example.cryptocurrencytracker.utils

import android.content.Context
import android.content.Intent
import com.example.cryptocurrencytracker.ui.LoginActivity
import com.example.cryptocurrencytracker.ui.MainActivity

fun Context.startMainActivity() = Intent(this, MainActivity::class.java).also {
    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(it)
}

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }