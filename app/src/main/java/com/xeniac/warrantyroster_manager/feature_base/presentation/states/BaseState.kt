package com.xeniac.warrantyroster_manager.feature_base.presentation.states

import com.google.android.play.core.review.ReviewInfo
import com.xeniac.warrantyroster_manager.core.domain.models.PreviousRateAppRequestDateTime
import com.xeniac.warrantyroster_manager.core.domain.models.RateAppOption
import com.xeniac.warrantyroster_manager.feature_base.domain.models.LatestAppUpdateInfo

data class BaseState(
    val isAppReviewDialogVisible: Boolean = false,
    val selectedRateAppOption: RateAppOption? = null,
    val previousRateAppRequestDateTime: PreviousRateAppRequestDateTime? = null,
    val inAppReviewInfo: ReviewInfo? = null,
    val latestAppUpdateInfo: LatestAppUpdateInfo? = null,
    val postNotificationPermissionState: PostNotificationPermissionState = PostNotificationPermissionState()
)