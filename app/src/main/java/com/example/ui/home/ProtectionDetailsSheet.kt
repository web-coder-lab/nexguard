package com.example.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatterySaver
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AppLanguage
import com.example.domain.model.ProtectionState
import com.example.domain.model.ProtectionStatus
import com.example.localization.AppStrings
import com.example.ui.components.BadgeType
import com.example.ui.components.StatusBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtectionDetailsSheet(
    status: ProtectionStatus,
    currentLanguage: AppLanguage,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onFixPermissions: () -> Unit
) {
    val strings = AppStrings.get(currentLanguage)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("protection_details_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = strings.protectionDetailsTitle,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = strings.protectionDetailsTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            val stateTitle = when (status.state) {
                ProtectionState.ACTIVE, ProtectionState.READY -> strings.statusProtectionActive
                ProtectionState.ACTION_NEEDED, ProtectionState.DISCONNECTED -> strings.statusAccessRequired
                ProtectionState.BATTERY_RESTRICTED -> strings.statusBatteryRestricted
                ProtectionState.PAUSED -> strings.statusMonitoringPaused
            }

            val stateDesc = when (status.state) {
                ProtectionState.ACTIVE, ProtectionState.READY -> strings.statusProtectionActiveDesc
                ProtectionState.ACTION_NEEDED, ProtectionState.DISCONNECTED -> strings.statusAccessRequiredDesc
                ProtectionState.BATTERY_RESTRICTED -> strings.statusBatteryRestrictedDesc
                ProtectionState.PAUSED -> strings.statusMonitoringPausedDesc
            }

            Text(
                text = stateDesc,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Details rows
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DetailRow(
                    label = strings.rowNotificationAccess,
                    value = if (status.isListenerGranted) strings.granted else strings.notGranted,
                    badgeType = if (status.isListenerGranted) BadgeType.SUCCESS else BadgeType.ALERT
                )
                DetailRow(
                    label = strings.rowBatteryOptimization,
                    value = if (status.isIgnoringBatteryOptimizations) "Unrestricted" else "Optimized",
                    badgeType = if (status.isIgnoringBatteryOptimizations) BadgeType.SUCCESS else BadgeType.WARNING
                )
                DetailRow(
                    label = strings.rowMonitoringState,
                    value = if (status.isMonitoringActive) "Active" else "Paused",
                    badgeType = if (status.isMonitoringActive) BadgeType.SUCCESS else BadgeType.NEUTRAL
                )
                DetailRow(
                    label = strings.rowMonitoredApps,
                    value = "${status.monitoredAppsCount} apps",
                    badgeType = BadgeType.INFO
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (!status.isListenerGranted || !status.isIgnoringBatteryOptimizations) {
                Button(
                    onClick = {
                        onDismiss()
                        onFixPermissions()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("fix_permissions_from_sheet_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = strings.actionFixPermissions, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("close_sheet_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(text = strings.close, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    badgeType: BadgeType
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
        StatusBadge(text = value, type = badgeType)
    }
}
