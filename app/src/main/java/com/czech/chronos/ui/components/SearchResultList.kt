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
import java.time.ZoneId
import java.time.ZonedDateTime

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResultList(
    list: List<CurrentTime>,
    viewModel: SearchViewModel
) {



    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = list,
        ) { data ->

            SearchResultItem(
                data = data,
                checked = data.checked,
                onCheckedChange = { checked ->
                    if (!checked) viewModel.deleteCurrentTimeFromDB(data.requestedLocation.toString())
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
) {

    val timeFormatter = remember { DateUtil.timeFormat }

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
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier
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
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)

            )
            Text(
                text = timeFormatter.format(localTime),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(end = 14.dp)
            )
        }
    }
}