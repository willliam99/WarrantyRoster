package com.xeniac.warrantyroster_manager.feature_base.domain.use_cases

import com.xeniac.warrantyroster_manager.core.domain.repositories.PermissionsDataStoreRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetIsRequestNotificationPermissionShownTodayUseCase @Inject constructor(
    private val repository: PermissionsDataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> = flow {
        return@flow emit(repository.isRequestNotificationPermissionShownToday())
    }
}