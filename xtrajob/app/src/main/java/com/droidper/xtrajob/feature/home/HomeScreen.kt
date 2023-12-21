package com.droidper.xtrajob.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.CardDayHoursMoney
import com.droidper.xtrajob.core.desingn.CardMonth
import com.droidper.xtrajob.core.desingn.CardWorkingDay
import com.droidper.xtrajob.core.desingn.HeaderHome
import com.droidper.xtrajob.ui.theme.AppTheme
import java.time.LocalDate

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "HomeScreenPreviewLight",
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "HomeScreenPreviewDark",
)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            navigateToCalendarScreen = {},
            navigateToLogin = {},
            navigateToRecordDayScreen = {}
        )
    }


}
@Composable
fun HomeScreen(
    navHostController: NavHostController = rememberNavController(),
    navigateToCalendarScreen: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToRecordDayScreen: () -> Unit
) {

    Scaffold(
        topBar = {
            HeaderHome(
                name = "Alejandro",
                onclickMore = { /*TODO*/ },
                onclickMinus = { /*TODO*/ }) {
                //Registrar
            }
        },
        modifier = Modifier
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RowTitleWithContent(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp),
                title = stringResource(id = R.string.title_home_1),
                contentRight = {
                    TextButton(onClick = navigateToCalendarScreen) {
                        Text(
                            text = stringResource(id = R.string.ver_mas),
                            textDecoration = TextDecoration.Underline,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            )
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardWorkingDay(hoursDay = listOf("08:00","16:00"), hoursBrakingWork = listOf("13:00","14:00"), day = "Miercoles") {

                    }
                }
            }
            RowTitleWithContent(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 25.dp, start = 40.dp, end = 40.dp),
                title = stringResource(id = R.string.title_home_2)) {

            }
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardDayHoursMoney(date = LocalDate.now(), hoursDay = "9h", money = "70€")
                }
            }
            RowTitleWithContent(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 25.dp, start = 40.dp, end = 40.dp),
                title = stringResource(id = R.string.title_home_3)) {

            }
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardMonth(age = "2023", hours = "200", month = "Diciembre", money = "1500€") {
                        navigateToRecordDayScreen()
                    }
                }
            }

        }
    }
    /***********/

}
@Composable
fun RowTitleWithContent(
    modifier: Modifier = Modifier,
    title: String,
    contentRight: @Composable () -> Unit
){

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.titleMedium,
            text = title)
        Surface(
            modifier = Modifier,
            content = contentRight,
        )
    }

}
