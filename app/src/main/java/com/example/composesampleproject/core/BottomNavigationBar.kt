package com.example.composesampleproject.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composesampleproject.R
import com.example.composesampleproject.ui.theme.Purple500

/**************************************** bottom bar *************************************/
//
private val bottomNavItems = listOf(
    Destination.Home,
    Destination.Favorite,
    Destination.Profile
)


@Composable
fun BottomNavigationBar(navController: NavController) {

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.yellow),
        contentColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavItems.forEach { item ->

            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon!!), contentDescription = item.title) },
                label = { Text(text = item.title!!) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Red.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}












/************************************* animated example*******************************************/


val screens = listOf(
    Destination.Home,
    Destination.Favorite,
    Destination.Profile,
)

@Composable
fun BottomBarAnimation(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        screens.forEach { screen ->

            AddItem(
                screen = screen,
                currentDestinationBottomBarAnimation = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: Destination,   //
    currentDestinationBottomBarAnimation: NavDestination?,
    navController: NavHostController,
) {
    val selected =
        currentDestinationBottomBarAnimation?.hierarchy?.any { it.route == screen.route } == true

    val background = if (selected) Purple500.copy(alpha = 0.6f) else Color.Transparent
    val contentColor = if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {

        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painter =
                painterResource(id = screen.icon!!),
//                painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )

            AnimatedVisibility(visible = selected) {
                screen.title?.let {
                    Text(
                        text = it,
                        color = contentColor
                    )
                }
            }
        }
    }
}




