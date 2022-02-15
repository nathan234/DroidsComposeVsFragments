package com.example.droidscomposesample.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data_networking.HolidayDTO
import com.example.droidscomposesample.ui.viewmodel.HolidayViewModel

@Composable
fun HolidayScreen(
    viewModel: HolidayViewModel = hiltViewModel()
) {
    val holidays: List<HolidayDTO> by viewModel.holidaysLiveData.observeAsState(
        initial = emptyList()
    )
    HolidayList(holidays = holidays)
}

@Composable
fun HolidayList(holidays: List<HolidayDTO>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(holidays) { holiday ->
            HolidayItem(holidayDTO = holiday, color = Color.Blue)
        }
        items(holidays) { holiday ->
            HolidayItem(holidayDTO = holiday, color = Color.Black)
        }
        items(holidays) { holiday ->
            HolidayItem(holidayDTO = holiday, color = Color.Magenta)
        }
    }
}

@Composable
fun HolidayItem(holidayDTO: HolidayDTO, color: Color) {
    Row {
        Text(text = "Date: ${holidayDTO.date}", color = color, textAlign = TextAlign.Start)
    }
}