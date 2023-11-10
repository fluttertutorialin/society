package data.remote.mapper

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import domain.entity.Date

fun Long.toDate(): Date {
    val instant = Instant.fromEpochMilliseconds(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)

    val day = localDateTime.dayOfMonth
    val month = localDateTime.month
    val year = localDateTime.year

    return Date(day, month, year)
}
