package com.bcit.assignmenttracker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(val icon: ImageVector, val route: String)

@Composable
fun MyBottomNav(navController: NavController){

    val navItems = listOf(
        NavItem(Icons.Default.Home, "home"),
        NavItem(Icons.Default.Notifications, "upcoming"),
        NavItem(Icons.Default.MailOutline, "motivation")
    )

    NavigationBar(
        containerColor = Color(0xff8EACCD)
    ) {
        // get reference to back stack entry
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        // get reference to current route as string
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach {
            NavigationBarItem(
                // the selected state ( true of false)
                selected = currentRoute == it.route,
                // when happens when we selected it
                onClick = {
                    navController.navigate(it.route)
                },
                // the icon it uses
                icon = {
                    Icon(it.icon, contentDescription = null)
                }
            )
        }
    }
}