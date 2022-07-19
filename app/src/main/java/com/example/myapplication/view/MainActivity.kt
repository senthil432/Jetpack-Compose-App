package com.example.myapplication.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import com.example.myapplication.navigation.ComposeNavigation
import com.example.myapplication.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.UserViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalMaterialApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                navController = rememberAnimatedNavController()
                ComposeNavigation(
                    navController = navController,
                    userViewModel = userViewModel
                )
            }
        }
    }
}

