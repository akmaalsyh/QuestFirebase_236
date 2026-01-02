package com.example.questfirebase_236.view.controlNavigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questfirebase_236.view.HalamanDetail
import com.example.questfirebase_236.view.HalamanEdit

import com.example.questfirebase_236.view.HalamanHome
import com.example.questfirebase_236.view.route.DestinasiDetail
import com.example.questfirebase_236.view.route.DestinasiEdit
import com.example.questfirebase_236.view.route.DestinasiEntry
import com.example.questfirebase_236.view.route.DestinasiHome

// Tambahkan fungsi ini karena MainActivity memanggil fungsi ini
// Pastikan ini ada di PetaNavigasi.kt agar MainActivity bisa memanggilnya
@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Halaman Home
        composable(DestinasiHome.route) {
            HalamanHome(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }

        // Halaman Entry
        composable(DestinasiEntry.route) {
            HalamanEntry(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Halaman Detail
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            HalamanDetail(
                navigateBack = {
                    navController.navigateUp()
                },
                onEditClick = { nim ->
                    navController.navigate("${DestinasiEdit.route}/$nim")
                }
            )
        }

        // Halaman Edit
        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEdit.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            HalamanEdit(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun HalamanEntry(navigateBack: () -> Boolean) {
    TODO("Not yet implemented")
}