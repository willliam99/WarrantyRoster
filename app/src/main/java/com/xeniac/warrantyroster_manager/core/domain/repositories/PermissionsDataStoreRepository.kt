package com.xeniac.warrantyroster_manager.core.domain.repositories

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

interface PermissionsDataStoreRepository {

    suspend fun isRequestNotificationPermissionShownToday(): Boolean

    suspend fun storeRequestNotificationPermissionDate(
        dateTimeFormat: DateTimeFormat<DateTimeComponents> = DateTimeComponents.Format {
            /**
             * DateTime Format: yyyy-mm-ddThh:mm:ss.000000Z
             * Sample: 2024-09-24T18:51:35.000000Z
             */
            dateTime(format = LocalDateTime.Formats.ISO)
            char(value = 'Z') // 'Z' is the zone designator for the zero UTC offset
        }
    )
}