package com.healthtech.misalud.uils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.healthtech.misalud.core.models.NavBarItems

object Constants {
    val BottomNavItems = listOf(
        NavBarItems(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        NavBarItems(
            label = "Search",
            icon = Icons.Filled.Search,
            route = "search"
        ),
        NavBarItems(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile"
        )
    )
}