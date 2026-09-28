package com.xeniac.warrantyroster_manager.feature_base.domain.use_cases

import dagger.Lazy
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
data class BaseUseCases @Inject constructor(
    val checkFlexibleUpdateDownloadStateUseCase: Lazy<CheckFlexibleUpdateDownloadStateUseCase>,
    val checkIsFlexibleUpdateStalledUseCase: Lazy<CheckIsFlexibleUpdateStalledUseCase>,
    val checkIsImmediateUpdateStalledUseCase: Lazy<CheckIsImmediateUpdateStalledUseCase>,
    val checkForAppUpdatesUseCase: Lazy<CheckForAppUpdatesUseCase>,
    val requestInAppReviewsUseCase: Lazy<RequestInAppReviewsUseCase>,
    val getLatestAppVersionUseCase: Lazy<GetLatestAppVersionUseCase>,
    val getSelectedRateAppOptionUseCase: Lazy<GetSelectedRateAppOptionUseCase>,
    val storeSelectedRateAppOptionUseCase: Lazy<StoreSelectedRateAppOptionUseCase>,
    val getPreviousRateAppRequestDateTimeUseCase: Lazy<GetPreviousRateAppRequestDateTimeUseCase>,
    val storePreviousRateAppRequestDateTimeUseCase: Lazy<StorePreviousRateAppRequestDateTimeUseCase>,
    val getIsRequestNotificationPermissionShownTodayUseCase: Lazy<GetIsRequestNotificationPermissionShownTodayUseCase>,
    val storeRequestNotificationPermissionDateUseCase: Lazy<StoreRequestNotificationPermissionDateUseCase>
)