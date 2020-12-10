package com.example.cryptocurrencytracker.api.repositories

import com.example.cryptocurrencytracker.api.data.FirebaseSource

class FirebaseUserRepository(private val firebase: FirebaseSource) {
    fun login(email: String, password: String) = firebase.login(email, password)
    fun register(email: String, password: String) = firebase.register(email, password)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logOut()
}