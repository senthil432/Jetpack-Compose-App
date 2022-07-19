package com.example.myapplication.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.User
import com.example.myapplication.network.ApiService
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class UserViewModel : ViewModel() {

    var userLiveData: ArrayList<User> by mutableStateOf(arrayListOf())
    var errorMessage: String by mutableStateOf("")

    fun getUser() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getUserList()
                userLiveData = movieList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }


    fun findUser(userId: Int): User? {
        var userDetails: User? = null

        if (!userLiveData.isNullOrEmpty()) {
            userLiveData.forEachIndexed { _, user ->
                if (user.id == userId) {
                    with(user) {
                        userDetails = User(
                            id = id,
                            name = name,
                            email = email,
                            gender = gender,
                            status = status
                        )
                    }
                }
            }
        }
        return userDetails
    }
}
