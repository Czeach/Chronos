package com.czech.chronos.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.ui.viewModels.SearchViewModel
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.Fonts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.TimeZone

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResultList(
    list: List<CurrentTime>,
    onCheckedChange: (Boolean, CurrentTime) -> Unit,
) {
    LazyColumn {
        items(
            items = list,
        ) { data ->
            var checked by remember {
                mutableStateOf(data.checked)
            }
            SearchResultItem(
                data = data,
                checked = checked,
                onCheckedChange = { newValue ->
                    checked = newValue
                    onCheckedChange(newValue, data)
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResultItem(
    data: CurrentTime,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val timeFormatter = remember { DateUtil.timeFormat }

    if (data.timezoneLocation.isNullOrEmpty()) {

    }

    var localTime by remember {
        mutableStateOf(
            ZonedDateTime.now(ZoneId.of(data.timezoneLocation))
        )
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            while (true) {
                localTime = ZonedDateTime.now(ZoneId.of(data.timezoneLocation))
                delay(1000L)
            }
        }
    }

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                modifier = modifier
                    .padding(start = 6.dp),
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.outlineVariant,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.scrim
                )
            )
            Text(
                text = data.requestedLocation.toString(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.exo,
                fontWeight = FontWeight.W400,
                modifier = modifier
                    .weight(1f)
                    .padding(start = 4.dp)

            )
            Text(
                text = timeFormatter.format(localTime),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
                modifier = modifier
                    .padding(end = 14.dp)
            )
        }
    }
}