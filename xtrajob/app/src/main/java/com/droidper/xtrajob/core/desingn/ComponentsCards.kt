package com.droidper.xtrajob.core.desingn

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.core.extensions.bottomBorder
import com.droidper.xtrajob.ui.theme.AppTheme
import java.time.LocalDate

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "CardsPreviewLight",
)
@Preview(
    device = Devices.PIXEL_3A,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "CardsPreviewNight",
)
@Composable
fun CardsPreview() {
    AppTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardWorkingDay(
                hoursDay = listOf("6:00","16:00"),
                hoursBrakingWork = listOf("12:00","13:00"),
                day = "LUNES",
            ){

            }
            Spacer(modifier = Modifier.height(4.dp))
            CardDayHoursMoney(
                date = LocalDate.now(),
                hoursDay = "8h",
                money = "55€"
            )
            Spacer(modifier = Modifier.height(4.dp))
            CardMonth(
                age = "2023",
                hours = "8h",
                month = "Diciembre",
                money = "35€",
                onclick = {}
            )
            Spacer(modifier = Modifier.height(4.dp))
            HeaderListDaysRecorded()
            CardDayRecorded(date = LocalDate.now(), hoursDay = listOf("6:00","16:00"), hoursBrakingWork = listOf("12:00","13:00"))
            CardDayRecorded(date = LocalDate.now(), hoursDay = listOf("6:00","16:00"), hoursBrakingWork = listOf("12:00","13:00"))
        }

    }

}
@Composable
fun CardWorkingDay(
    hoursDay: List<String>,
    hoursBrakingWork: List<String>,
    day: String,
    important: Boolean = false,
    onclick: () -> Unit
){
    Card(
        modifier = Modifier
            .width(236.dp)
            .height(124.dp)
            .clickable { onclick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (important) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                ) {
            Row(
                modifier = Modifier
                    .padding(top = 15.dp, end = 15.dp, start = 15.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ColumnHourWithIcon(hours = listOf(hoursDay[0])){
                    Icon(imageVector = Icons.Filled.PlayCircle, contentDescription = "Icon of play or start")
                }


                ColumnHourWithIcon(hours = hoursBrakingWork){
                    Icon(imageVector = Icons.Filled.PauseCircle, contentDescription = "Icon of pause")
                }
                ColumnHourWithIcon(hours = listOf(hoursDay[1])) {
                    Icon(imageVector = Icons.Filled.StopCircle, contentDescription = "Icon of stop")
                }


            }
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 12.dp)
                    .align(Alignment.BottomStart),
                text = day,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
@Composable
fun ColumnHourWithIcon(
    hours: List<String>,
    icon: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .height(89.dp)
            .width(37.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            content = icon,
            color = Color.White.copy(0f)
        )
        WorkBreak(hours = hours)

    }
}
@Composable
fun WorkBreak(
    modifier: Modifier = Modifier,
    hours: List<String>) {
    Column(
        modifier = modifier
            .height(55.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (hours.size) {
            0 -> {
                Text(text = "-")
            }
            1 -> {
                Text(
                    text = hours[0],
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            else -> {
                Text(
                    text = hours[0],
                    style = MaterialTheme.typography.bodyMedium
                )
                Divider(
                    modifier = Modifier
                        .height(14.dp)
                        .width(2.dp),
                )
                Text(
                    text = hours[1],
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }

}


/***************************Recorded Card Day **********************************/

@Composable
fun CardDayHoursMoney(
    date: LocalDate,
    hoursDay: String,
    money: String
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(85.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconDay(day = "D")
                Text(
                    text = "10/09/23",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColumnIconWithHour(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Construction,
                            contentDescription = "Icon Work"
                        )
                    },
                    quantity = hoursDay
                )
                ColumnIconWithHour(
                    icon = {
                        Icon(imageVector = Icons.Filled.Euro,
                            contentDescription = "Icon Work"
                        )
                    },
                    quantity = money
                )
            }

        }

    }
}
@Composable
fun IconDay(
    day: String,
) {
    Surface(
        modifier = Modifier
            .width(21.dp)
            .height(21.dp),
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        shadowElevation = 4.dp
    ) {
        Text(
            modifier = Modifier,
            text = day,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
    }
}
@Composable
fun ColumnIconWithHour(
    icon: @Composable () -> Unit,
    quantity: String
){
    Column(
        modifier = Modifier
            .width(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Surface(
            modifier = Modifier,
            content = icon,
            color = Color.White.copy(0f),
        )
        Text(
            text = quantity,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
/************************************* Card Month ****************************/

@Composable
fun CardMonth(
    age: String,
    hours: String,
    month: String,
    money: String,
    onclick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(100.dp)
            .height(122.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = age)
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clickable { onclick() },
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .height(31.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = month,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ColumnIconWithHour(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Construction,
                                contentDescription = "Icon of construction"
                            ) },
                        quantity = hours
                    )
                    ColumnIconWithHour(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Euro,
                                contentDescription = "Icon of construction"
                            ) },
                        quantity = money
                    )
                }

            }


        }
    }
}
/*****************************Card Days Recorded **************************************/
@Composable
fun CardDayRecorded(
    modifier: Modifier = Modifier,
    date: LocalDate,
    hoursDay: List<String>,
    hoursBrakingWork: List<String>
) {
    Surface {
        Row(
            modifier = modifier
                .defaultMinSize(minWidth = 266.dp)
                .height(61.dp)
                .bottomBorder(2.dp, MaterialTheme.colorScheme.surfaceVariant),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .weight(15f),
                text = "10",
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier.weight(15f),
                contentAlignment = Alignment.Center
            ){
                IconDay(day = "D")
            }

            Text(
                modifier = Modifier.weight(33.3f),
                text = hoursDay[0],
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center

            )
            WorkBreak(
                modifier = Modifier
                    .weight(33.3f),
                hours = hoursBrakingWork)
            Text(
                modifier = Modifier.weight(33.3f),
                text = hoursDay[1],
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
        }
    }

}