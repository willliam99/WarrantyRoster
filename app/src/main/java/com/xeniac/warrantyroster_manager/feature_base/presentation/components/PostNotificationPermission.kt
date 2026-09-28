package com.xeniac.warrantyroster_manager.feature_base.presentation.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.xeniac.warrantyroster_manager.R
import com.xeniac.warrantyroster_manager.core.presentation.common.ui.components.PermissionDialog
import com.xeniac.warrantyroster_manager.core.presentation.common.utils.findActivity
import com.xeniac.warrantyroster_manager.feature_base.presentation.BaseAction
import com.xeniac.warrantyroster_manager.feature_base.presentation.states.PostNotificationPermissionState
import com.xeniac.warrantyroster_manager.feature_base.presentation.utils.PostNotificationsPermissionHelper
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PostNotificationPermission(
    state: PostNotificationPermissionState,
    onAction: (action: BaseAction) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val context = LocalContext.current
        val activity = LocalActivity.current ?: context.findActivity()

        val postNotificationPermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            onAction(
                BaseAction.OnNotificationPermissionResult(
                    isGranted = isGranted,
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                        /* activity = */ activity,
                        /* permission = */ Manifest.permission.POST_NOTIFICATIONS
                    )
                )
            )
        }

        LaunchedEffect(
            key1 = state.isRequestNotificationPermissionShownToday
        ) {
            delay(duration = 500.milliseconds) // Delay to solve the result launcher launching twice issue
            if (!state.isRequestNotificationPermissionShownToday) {
                postNotificationPermissionResultLauncher.launch(
                    input = Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }

        PostNotificationPermissionDialog(
            isVisible = state.isNotificationPermissionDialogVisible,
            isPermanentlyDeclined = state.isNotificationPermissionPermanentlyDeclined,
            onConfirmClick = {
                postNotificationPermissionResultLauncher.launch(
                    input = Manifest.permission.POST_NOTIFICATIONS
                )
            },
            onDismiss = { onAction(BaseAction.DismissNotificationPermissionDialog) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun PostNotificationPermissionDialog(
    isVisible: Boolean,
    isPermanentlyDeclined: Boolean,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        PermissionDialog(
            icon = painterResource(id = R.drawable.ic_core_dialog_post_notification),
            permissionHelper = PostNotificationsPermissionHelper(),
            isPermanentlyDeclined = isPermanentlyDeclined,
            onConfirmClick = onConfirmClick,
            onDismiss = onDismiss,
            modifier = modifier
        )
    }
}