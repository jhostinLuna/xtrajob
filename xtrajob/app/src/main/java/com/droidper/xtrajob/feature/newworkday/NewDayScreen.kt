package com.droidper.xtrajob.feature.newworkday

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.BoxHourMedium
import com.droidper.xtrajob.core.desingn.DialogTimeInitAndFinish
import com.droidper.xtrajob.core.desingn.DialogTimePicker
import com.droidper.xtrajob.core.desingn.RowHourMinute
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.core.desingn.WorkBreak
import com.droidper.xtrajob.core.extensions.setMinAndHour
import com.droidper.xtrajob.feature.home.RowTitleWithContent
import com.droidper.xtrajob.model.RecordDay
import com.droidper.xtrajob.ui.theme.AppTheme
import java.time.LocalDateTime
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "NewDayScreenLight",
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "NewDayScreenDark",
)
@Composable
fun NewDayScreenPreview(){
    val startBreakTimepickerState = rememberTimePickerState()
    val endBreakTimepickerState = rememberTimePickerState()
    val newDayState by remember {
        mutableStateOf(RecordDay())
    }
    AppTheme {
        NewDayScreen(
            onPressBack = {}
        )
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDayScreen(
    onPressBack: () -> Unit
){

    val startTimepickerState = rememberTimePickerState()
    val endTimePickerState = rememberTimePickerState()
    val recordDay = RecordDay()
    val startTimeWork by remember {
        mutableStateOf(0L)
    }
    val endTimeWork by remember {
        mutableStateOf(0L)
    }
    var startTimeBreakWork by remember {
        mutableStateOf(0L)
    }
    val endTimeBreakWork by remember {
        mutableStateOf(0L)
    }
    Scaffold(
        topBar = {
            TopAppBarBasic(
                title = stringResource(id = R.string.new_day_screen),
                onclickArrowBack = onPressBack
            )
        },
        floatingActionButton = {
            IconButton(onClick = { /*TODO*/ }) {
                Surface(
                    modifier = Modifier,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Guardar nueva jornada"
                    )
                }

            }
        }
    ) {innerPaddingValue->

        Column(
            modifier = Modifier
                .padding(innerPaddingValue)
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RowTitleWithContent(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                title = stringResource(id = R.string.newday_title1)) {

            }
            // Hora Inicio

            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.inversePrimary,
                shadowElevation = 4.dp
            ) {
                var showTimePicker by remember {
                    mutableStateOf(false)
                }
                var hourInit by remember {
                    mutableStateOf("00")
                }
                var minInit by remember {
                    mutableStateOf("00")
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showTimePicker = true },
                    hour = hourInit,
                    minute =minInit
                )
                DialogTimePicker(
                    timepickerState = startTimepickerState,
                    show = showTimePicker,
                    onClickAccept = {
                        hourInit = String.format("%02d",startTimepickerState.hour)
                        minInit = String.format("%02d",startTimepickerState.minute)
                        showTimePicker = false},
                    onDismiss = {showTimePicker = false}
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            //Hora Fin
            RowTitleWithContent(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                title = stringResource(id = R.string.newday_title2)) {

            }
            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shadowElevation = 4.dp
            ) {
                var showTimePicker by remember {
                    mutableStateOf(false)
                }
                var hourFin by remember {
                    mutableStateOf("00")
                }
                var minFin by remember {
                    mutableStateOf("00")
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showTimePicker = true },
                    hour = hourFin,
                    minute = minFin
                )
                DialogTimePicker(
                    timepickerState = endTimePickerState,
                    show = showTimePicker,
                    onClickAccept = {
                        hourFin = String.format("%02d",endTimePickerState.hour)
                        minFin = String.format("%02d",endTimePickerState.minute)
                        showTimePicker = false},
                    onDismiss = {showTimePicker = false}
                )

            }
            Spacer(modifier = Modifier.height(50.dp))
            var switchBreakWorkState by remember {
                mutableStateOf(false)
            }
            var showDialogTime by remember {
                mutableStateOf(false)
            }
            RowTitleWithContent(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                title = stringResource(id = R.string.newday_title3)) {
                //Switch(checked = switchBreakWorkState, onCheckedChange = {switchBreakWorkState = !switchBreakWorkState})
            }
            var observationState by remember {
                mutableStateOf("")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                WorkBreak(
                    modifier = Modifier
                        .clickable { showDialogTime = true },
                    hours = emptyList())
                BoxHourMedium(number = "0h")
            }
            DialogTimeInitAndFinish(
                title = stringResource(id = R.string.time_init),
                showState = showDialogTime,
                onclickCancel = { showDialogTime = false },
                onclickAccept = {minute, hour ->
                    //startTimeBreakWork = LocalDateTime.now().setMinAndHour(minute,hour).toEpochSecond(ZoneOffset.UTC) * 1000
                })

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(136.dp),
                value = observationState,
                onValueChange = { if (it.length < 256) observationState = it},
                maxLines = 5,
                decorationBox = {
                    Surface(
                        modifier = Modifier
                            .padding(8 .dp),
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(4.dp),
                        shadowElevation = 4.dp
                    ) {
                        if (observationState.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.observations),
                                color = Color.Gray
                            )
                        }else {
                            Text(text = observationState)
                        }

                    }
                }
            )
        }
    }

}
