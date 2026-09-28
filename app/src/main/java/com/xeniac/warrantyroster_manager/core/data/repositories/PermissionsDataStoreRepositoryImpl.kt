package com.xeniac.warrantyroster_manager.core.data.repositories

import androidx.datastore.core.DataStore
import com.xeniac.warrantyroster_manager.core.domain.models.PermissionsPreferences
import com.xeniac.warrantyroster_manager.core.domain.repositories.PermissionsDataStoreRepository
import com.xeniac.warrantyroster_manager.core.domain.utils.PermissionHelper.isRequestShownToday
import kotlinx.coroutines.flow.first
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Clock

class PermissionsDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<PermissionsPreferences>
) : PermissionsDataStoreRepository {

    override suspend fun isRequestNotificationPermissionShownToday(): Boolean = try {
        dataStore.data.first().requestNotificationPermissionDate
            ?.isRequestShownToday() ?: false
    } catch (e: Exception) {
        Timber.e("Get request notification permission date failed:")
        e.printStackTrace()
        false
    }

    override suspend fun storeRequestNotificationPermissionDate(
        dateTimeFormat: DateTimeFormat<DateTimeComponents>
    ) {
        try {
            val shownDate = Clock.System.now()
            dataStore.updateData {
                it.copy(requestNotificationPermissionDate = shownDate.format(dateTimeFormat))
            }
            Timber.i("Request notification permission date stored.")
        } catch (e: Exception) {
            Timber.e("Store request notification permission date failed:")
            e.printStackTrace()
        }
    }
}