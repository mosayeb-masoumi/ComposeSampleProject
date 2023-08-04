package com.example.composesampleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composesampleproject.core.BottomBarAnimation
import com.example.composesampleproject.core.BottomNavigationBar

import com.example.composesampleproject.core.NavigationAppHost
import com.example.composesampleproject.features.home.presentation.HomeViewModel
import com.example.composesampleproject.ui.theme.ComposeSampleProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "home" -> true // on this screen bottom bar should be hidden
        "favorite" -> true // on this screen bottom bar should be hidden
        "profile" -> true // on this screen bottom bar should be hidden
//        "RouteOfScreenB" -> false // here too
        else -> false // in all other cases show bottom bar
    }


//    val scaffoldState = rememberScaffoldState()




    Scaffold(

//        scaffoldState = scaffoldState,

//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Drawer Sample")
//                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = {
//                            scope.launch {
//                                scaffoldState.drawerState.open()
//                            }
//                        },
//                    ) {
//                        Icon(
//                            Icons.Rounded.Menu,
//                            contentDescription = ""
//                        )
//                    }
//                })
//        },
//        drawerGesturesEnabled = true,
//        drawerContent = {
//            DrawerView(onEventCallback = { route ->
//
//                navController.navigate(route)
//                scope.launch {
//                    scaffoldState.drawerState.close()
//                }
//
//            })
//        },

        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(navController)
//                BottomBarAnimation(navController)
        },


        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                /* Add code later */
                NavigationAppHost(navController = navController  )
            }
        },
        backgroundColor = colorResource(R.color.purple_700) // Set background color to avoid the white flashing when you switch between screens
    )

}





