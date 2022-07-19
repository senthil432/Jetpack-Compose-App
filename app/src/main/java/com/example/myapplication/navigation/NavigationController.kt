package com.example.myapplication.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myapplication.view.SplashScreen
import com.example.myapplication.view.UserDetailsView
import com.example.myapplication.view.UserListScreen
import com.example.myapplication.viewmodel.UserViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ComposeNavigation(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable(
            route = "splash",
            exitTransition = {
                when (targetState.destination.route) {
                    "list" ->
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300))
                    else -> null
                }
            },
        ) {
            SplashScreen(
                navigateToListScreen = {
                    navController.navigate(route = "list") {
                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(
            route = "list"
        ) {
            UserListScreen(
                navigateToViewScreen = { userId ->
                    navController.navigate(route = "view/$userId")
                },
                userViewModel = userViewModel
            )
        }


        composable(
            route = "view/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300))
            }
        ) { entry ->

            val userId = entry.arguments!!.getInt("userId")

            val selectedUser = userViewModel.findUser(userId)

            if (selectedUser != null) {
                UserDetailsView(
                    userViewModel = userViewModel,
                    selectedUser = selectedUser
                )
            }
        }
    }
}