package com.example.composesampleproject.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composesampleproject.R
import com.example.composesampleproject.features.favorite.presentation.FavoriteScreen
import com.example.composesampleproject.features.home.presentation.HomeScreen
import com.example.composesampleproject.features.home.presentation.HomeViewModel
import com.example.composesampleproject.features.profile.presentation.ProfileScreen
import com.example.composesampleproject.features.splash.SplashScreen
import com.example.composesampleproject.features.item_detail.presentation.TodoDetailScreen

sealed class Destination(var route: String , var icon: Int?, var title: String?){

    object Splash: Destination("splash", null , null)
    object Home: Destination("home" , R.drawable.ic_home ,"Home")
    object Favorite: Destination("favorite" , R.drawable.ic_favorite ,"Favorite")
    object Profile: Destination("profile" , R.drawable.ic_profile ,"Profile")
    object TodoDetailScreen: Destination("todoDetailScreen/{itemId}" , null ,null){
        fun createRoute(itemId:Int) = "todoDetailScreen/$itemId"
    }


}

@Composable
fun NavigationAppHost(navController: NavHostController) {


    NavHost(navController, startDestination = Destination.Splash.route) {

        composable(Destination.Splash.route) {
            SplashScreen(navController)
        }

        composable(Destination.Home.route) {
            HomeScreen(navController)
        }

        composable(Destination.Favorite.route){
            FavoriteScreen(navController)
        }

        composable(Destination.Profile.route) {
            ProfileScreen(navController)
        }

//        composable(Destination.TodoDetailItem.route) {
//            TodoDetailItem(navController)
//        }

        composable(Destination.TodoDetailScreen.route) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")

            if(itemId != null){
                TodoDetailScreen(navController = navController , itemId.toInt())
            }
        }




    }
}
