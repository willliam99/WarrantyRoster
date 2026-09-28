package com.xeniac.warrantyroster_manager.core.domain.utils

import com.xeniac.warrantyroster_manager.core.domain.models.RequestNotificationPermissionDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char
import kotlinx.datetime.parse
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.Instant

object PermissionHelper {

    fun RequestNotificationPermissionDate?.isRequestShownToday(
        timeZone: TimeZone = TimeZone.currentSystemDefault(),
        dateTimeFormat: DateTimeFormat<DateTimeComponents> = DateTimeComponents.Format {
            /**
             * DateTime Format: yyyy-mm-ddThh:mm:ss.000000Z
             * Sample: 2024-09-24T18:51:35.000000Z
             */
            dateTime(format = LocalDateTime.Formats.ISO)
            char(value = 'Z') // 'Z' is the zone designator for the zero UTC offset
        }
    ): Boolean = this?.let { requestDate ->
        val todayLocalDate = Clock.System.todayIn(timeZone = timeZone)
        val shownLocalDate = Instant.parse(
            input = requestDate,
            format = dateTimeFormat
        ).toLocalDateTime(timeZone = timeZone)

        shownLocalDate.date == todayLocalDate
    } ?: false
}