package com.xeniac.warrantyroster_manager.feature_base.domain.use_cases

import com.xeniac.warrantyroster_manager.core.domain.models.Result
import com.xeniac.warrantyroster_manager.core.domain.repositories.PermissionsDataStoreRepository
import com.xeniac.warrantyroster_manager.feature_base.domain.errors.StoreRequestNotificationPermissionDateError
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
class StoreRequestNotificationPermissionDateUseCase @Inject constructor(
    private val repository: PermissionsDataStoreRepository
) {
    operator fun invoke(): Flow<Result<Unit, StoreRequestNotificationPermissionDateError>> = flow {
        return@flow try {
            repository.storeRequestNotificationPermissionDate()
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            Timber.e("Store request notification permission date Exception:")
            e.printStackTrace()
            emit(Result.Error(StoreRequestNotificationPermissionDateError.SomethingWentWrong))
        }
    }
}