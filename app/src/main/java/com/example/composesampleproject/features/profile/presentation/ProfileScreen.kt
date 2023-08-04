package com.example.composesampleproject.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
 Box(modifier = Modifier
     .fillMaxSize()
     .background(color = Color.Yellow) , contentAlignment = Alignment.Center) {
     
     Text(text = "Profile" , color = Color.Black , fontSize = 25.sp)
 }
}