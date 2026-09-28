package com.xeniac.warrantyroster_manager.core.presentation.common.ui.components

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.xeniac.warrantyroster_manager.R
import com.xeniac.warrantyroster_manager.core.presentation.common.utils.findActivity
import com.xeniac.warrantyroster_manager.core.presentation.common.utils.openAppSettings
import com.xeniac.warrantyroster_manager.core.presentation.common.utils.permission.PermissionHelper

@Composable
fun PermissionDialog(
    permissionHelper: PermissionHelper,
    isPermanentlyDeclined: Boolean,
    icon: Painter,
    modifier: Modifier = Modifier,
    securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
    dialogProperties: DialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true,
        decorFitsSystemWindows = true,
        securePolicy = securePolicy
    ),
    title: String? = null,
    confirmButtonText: String = when {
        isPermanentlyDeclined -> stringResource(id = R.string.permissions_error_btn_open_settings)
        else -> stringResource(id = R.string.permissions_error_btn_confirm)
    },
    dismissButtonText: String = stringResource(id = R.string.permissions_error_btn_dismiss),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    iconContentColor: Color = MaterialTheme.colorScheme.secondary,
    titleContentColor: Color = MaterialTheme.colorScheme.onSurface,
    textContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val activity = LocalActivity.current ?: context.findActivity()

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties,
        containerColor = containerColor,
        iconContentColor = iconContentColor,
        titleContentColor = titleContentColor,
        textContentColor = textContentColor,
        icon = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        title = title?.let {
            {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        text = {
            Text(
                text = permissionHelper.getMessage(isPermanentlyDeclined).asString(),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    when {
                        isPermanentlyDeclined -> activity.openAppSettings()
                        else -> onConfirmClick()
                    }
                    onDismiss()
                }
            ) {
                Text(
                    text = confirmButtonText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = dismissButtonText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}