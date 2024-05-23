package com.healthtech.misalud.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("OpaqueUnitKey")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(calendarState: UseCaseState, onCalendarOK: (String) -> Unit, allowedDays: List<String>) {
    fun ClosedRange<LocalDate>.toIterable(): Iterable<LocalDate> = Iterable {
        object : Iterator<LocalDate> {
            private var nextDate = start

            override fun hasNext(): Boolean = nextDate <= endInclusive

            override fun next(): LocalDate = nextDate.also { nextDate = nextDate.plusDays(1) }
        }
    }

    val boundary = LocalDate.ofEpochDay(17145)..LocalDate.now().plusYears(10)

    val allowedDates = allowedDays.map { date ->
        ZonedDateTime.parse(
            date,
            DateTimeFormatter.ISO_DATE_TIME
        ).withZoneSameInstant(ZoneId.systemDefault()).toLocalDate()
    }

    val disabledDates = remember(boundary, allowedDates) {
        boundary.toIterable() - allowedDates.toSet()
    }

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            style = CalendarStyle.MONTH,
            disabledDates = disabledDates,
            boundary = boundary,
        ),
        selection = CalendarSelection.Date { newDate ->
            onCalendarOK(newDate.toString())
        },
    )
}