package com.xeniac.warrantyroster_manager.feature_base.presentation

sealed interface BaseAction {
    data object CheckIsAppUpdateStalled : BaseAction
    data object CheckFlexibleUpdateDownloadState : BaseAction
    data object CheckForAppUpdates : BaseAction
    data object GetLatestAppVersion : BaseAction
    data object DismissAppUpdateSheet : BaseAction

    data object RequestInAppReviews : BaseAction
    data object LaunchInAppReview : BaseAction
    data object SetSelectedRateAppOptionToNever : BaseAction
    data object SetSelectedRateAppOptionToRemindLater : BaseAction
    data object DismissAppReviewDialog : BaseAction

    data class OnNotificationPermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDeclined: Boolean
    ) : BaseAction

    data object DismissNotificationPermissionDialog : BaseAction
}