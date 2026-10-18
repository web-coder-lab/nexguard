package com.example.ui.home

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatterySaver
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AppLanguage
import com.example.domain.model.ProtectionState
import com.example.domain.model.ProtectionStatus
import com.example.localization.AppStrings
import com.example.ui.components.BadgeType
import com.example.ui.components.GuardianShield
import com.example.ui.components.StatusBadge
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    status: ProtectionStatus,
    currentLanguage: AppLanguage,
    reducedMotion: Boolean,
    hapticEnabled: Boolean,
    onNavigateToSettings: () -> Unit,
    onRunDiagnostics: () -> Unit,
    onFixPermissions: () -> Unit,
    onStartAndMinimize: () -> Unit,
    onStopProtection: () -> Unit
) {
    val view = LocalView.current
    val strings = AppStrings.get(currentLanguage)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showDetailsSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun performHaptic() {
        if (hapticEnabled) {
            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        }
    }

    if (showDetailsSheet) {
        ProtectionDetailsSheet(
            status = status,
            currentLanguage = currentLanguage,
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    showDetailsSheet = false
                }
            },
            onFixPermissions = onFixPermissions
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen"),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 28.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = strings.appName,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = strings.appName,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = strings.appSubtitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }

                IconButton(
                    onClick = {
                        performHaptic()
                        onNavigateToSettings()
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        .testTag("settings_icon_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = strings.actionSettings,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Center: Compact, elegant shield
            GuardianShield(
                state = status.state,
                size = 136.dp,
                reducedMotion = reducedMotion,
                onClick = {
                    performHaptic()
                    showDetailsSheet = true
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Status headline & concise description
            val (statusTitle, statusDesc) = when (status.state) {
                ProtectionState.ACTIVE, ProtectionState.READY ->
                    Pair(strings.statusProtectionActive, strings.statusProtectionActiveDesc)
                ProtectionState.ACTION_NEEDED, ProtectionState.DISCONNECTED ->
                    Pair(strings.statusAccessRequired, strings.statusAccessRequiredDesc)
                ProtectionState.BATTERY_RESTRICTED ->
                    Pair(strings.statusBatteryRestricted, strings.statusBatteryRestrictedDesc)
                ProtectionState.PAUSED ->
                    Pair(strings.statusMonitoringPaused, strings.statusMonitoringPausedDesc)
            }

            Text(
                text = statusTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = statusDesc,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Prominent Primary Action Button
            if (status.state == ProtectionState.ACTION_NEEDED || !status.isListenerGranted) {
                Button(
                    onClick = {
                        performHaptic()
                        onFixPermissions()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("primary_fix_permissions_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = strings.actionFixPermissions, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(14.dp))
            } else if (!status.isMonitoringActive || status.state == ProtectionState.PAUSED) {
                Button(
                    onClick = {
                        performHaptic()
                        onStartAndMinimize()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("start_protection_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = strings.startProtection,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = strings.startProtection, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(14.dp))
            } else {
                // Protection is ACTIVE: Prominent button to minimize to background and keep running
                Button(
                    onClick = {
                        performHaptic()
                        onStartAndMinimize()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("protect_in_background_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = strings.protectInBackground,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = strings.protectInBackground, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {
                        performHaptic()
                        onStopProtection()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .testTag("stop_protection_button"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = strings.stopProtection,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = strings.stopProtection, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Compact diagnostic indicators card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        performHaptic()
                        showDetailsSheet = true
                    }
                    .testTag("status_summary_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CompactStatusRow(
                        icon = Icons.Default.NotificationsActive,
                        label = strings.rowNotificationAccess,
                        badgeText = if (status.isListenerGranted) strings.granted else strings.notGranted,
                        badgeType = if (status.isListenerGranted) BadgeType.SUCCESS else BadgeType.ALERT
                    )

                    CompactStatusRow(
                        icon = Icons.Default.CheckCircle,
                        label = strings.rowMonitoringState,
                        badgeText = if (status.isMonitoringActive) strings.monitoringActive else strings.monitoringUnavailable,
                        badgeType = if (status.isMonitoringActive) BadgeType.SUCCESS else BadgeType.NEUTRAL
                    )

                    CompactStatusRow(
                        icon = Icons.Default.BatterySaver,
                        label = strings.rowBatteryOptimization,
                        badgeText = if (status.isIgnoringBatteryOptimizations) "Unrestricted" else "Optimized",
                        badgeType = if (status.isIgnoringBatteryOptimizations) BadgeType.SUCCESS else BadgeType.WARNING
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Compact Run Diagnostics button
            OutlinedButton(
                onClick = {
                    performHaptic()
                    onRunDiagnostics()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("run_diagnostics_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.QueryStats,
                    contentDescription = strings.actionRunDiagnostics,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = strings.actionRunDiagnostics,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun CompactStatusRow(
    icon: ImageVector,
    label: String,
    badgeText: String,
    badgeType: BadgeType
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        StatusBadge(text = badgeText, type = badgeType)
    }
}
