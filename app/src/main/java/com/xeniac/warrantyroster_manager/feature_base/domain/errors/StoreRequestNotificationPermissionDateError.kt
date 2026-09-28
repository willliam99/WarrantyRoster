package com.xeniac.warrantyroster_manager.feature_base.domain.errors

import com.xeniac.warrantyroster_manager.core.domain.errors.Error

sealed class StoreRequestNotificationPermissionDateError : Error() {
    data object SomethingWentWrong : StoreRequestNotificationPermissionDateError()
}