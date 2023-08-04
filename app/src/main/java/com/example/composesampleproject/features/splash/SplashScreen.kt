package com.example.composesampleproject.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composesampleproject.core.Destination
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Blue) , contentAlignment = Alignment.Center) {

        Column(modifier = Modifier.fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Splash" , color = Color.White , fontSize = 25.sp)

            CircularProgressIndicator(color = Color.White)
        }

    }


    LaunchedEffect(true){
        delay(3000L)

        navController.navigate(Destination.Home.route)

//        navController.navigate(Destination.Home.route){
//
//           // The above code will navigate from the Splash screen to Login and will pop everything up,
//            // including the Splash screen.
//            popUpTo(Destination.Splash.route) {
//                inclusive = true
//            }
//        }
    }
}

