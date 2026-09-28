package com.xeniac.warrantyroster_manager.feature_base.presentation.utils

import com.xeniac.warrantyroster_manager.R
import com.xeniac.warrantyroster_manager.core.presentation.common.utils.UiText
import com.xeniac.warrantyroster_manager.feature_base.domain.errors.StoreRequestNotificationPermissionDateError

fun StoreRequestNotificationPermissionDateError.asUiText(): UiText = when (this) {
    StoreRequestNotificationPermissionDateError.SomethingWentWrong -> UiText.StringResource(R.string.error_something_went_wrong)
}