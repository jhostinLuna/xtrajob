package com.droidper.xtrajob.feature

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidper.xtrajob.core.navigation.Screen
import com.droidper.xtrajob.feature.home.HomeScreen
import com.droidper.xtrajob.feature.newworkday.NewDayScreen
import com.droidper.xtrajob.feature.recorddays.RecordDaysScreen
import com.droidper.xtrajob.feature.workcalendar.WorkCalendarScreen
import com.droidper.xtrajob.ui.theme.AppTheme

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "HomeScreenPreviewLight",
)
@Composable
fun HomeScreenPreview(){
    AppTheme {
        XtraJobComposeApp()
    }
}

@Composable
fun XtraJobComposeApp (
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ){

        composable(route = Screen.RecordDays.route) {
            RecordDaysScreen(
                backPressed = {},
                navToNewDay = {}
            )
        }
        composable(route = Screen.Calendar.route) {
            WorkCalendarScreen(
                navToNewDay = { navHostController.navigate(Screen.NewDay.route)},
                navToBack = {}
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToCalendarScreen = { navHostController.navigate(Screen.Calendar.route) },
                navigateToLogin = { /*TODO*/ },
                navigateToRecordDayScreen = { navHostController.navigate(Screen.RecordDays.route) }
            )
        }
        composable(route = Screen.NewDay.route) {
            NewDayScreen {
                navHostController.navigateUp()
            }
        }
    }
}