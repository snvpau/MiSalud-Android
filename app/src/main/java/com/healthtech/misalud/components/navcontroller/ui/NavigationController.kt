package com.healthtech.misalud.components.navcontroller.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.healthtech.misalud.components.home.ui.HomeScreen
import com.healthtech.misalud.components.home.ui.HomeViewModel
import com.healthtech.misalud.components.home.ui.Search
import com.healthtech.misalud.components.profile.ui.ProfileScreen
import com.healthtech.misalud.components.profile.ui.ProfileViewModel
import com.healthtech.misalud.uils.Constants

@Composable
fun NavigationController(globalNavController : NavHostController, homeViewModel: HomeViewModel, profileViewModel: ProfileViewModel){
    val navController = rememberNavController()

    Surface(color = Color.White){
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = navController) },
            content = { padding -> NavHostContainer(
                localNavController = navController,
                globalNavController = globalNavController,
                padding = padding,
                homeViewModel = homeViewModel,
                profileViewModel = profileViewModel
            )}
        )
    }
}

@Composable
fun NavHostContainer(
    localNavController: NavHostController,
    globalNavController : NavHostController,
    padding: PaddingValues,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel
) {
    NavHost(
        navController = localNavController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                HomeScreen(viewModel = homeViewModel)
            }
            composable("search") {
                Search()
            }
            composable("profile") {
                ProfileScreen(profileViewModel, localNavController, globalNavController)
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color(44, 90, 168)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                label = { Text(text = navItem.label) },
                alwaysShowLabel = false
            )
        }
    }
}