package com.example.ui.diagnostics

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AppLanguage
import com.example.domain.model.DiagnosticActionType
import com.example.domain.model.DiagnosticItem
import com.example.domain.model.DiagnosticReport
import com.example.domain.model.DiagnosticSeverity
import com.example.localization.AppStrings
import com.example.notification.diagnostics.ManufacturerHelper
import com.example.ui.components.BadgeType
import com.example.ui.components.StatusBadge

@Composable
fun DiagnosticsDialog(
    report: DiagnosticReport,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onReRun: () -> Unit
) {
    val context = LocalContext.current
    val strings = AppStrings.get(currentLanguage)

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("diagnostics_dialog"),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strings.diagnosticReportTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                StatusBadge(
                    text = when (report.overallStatus) {
                        DiagnosticSeverity.GOOD -> "Healthy"
                        DiagnosticSeverity.WARNING -> "Attention"
                        DiagnosticSeverity.ALERT -> "Critical"
                        DiagnosticSeverity.INFO -> "Info"
                    },
                    type = when (report.overallStatus) {
                        DiagnosticSeverity.GOOD -> BadgeType.SUCCESS
                        DiagnosticSeverity.WARNING -> BadgeType.WARNING
                        DiagnosticSeverity.ALERT -> BadgeType.ALERT
                        DiagnosticSeverity.INFO -> BadgeType.INFO
                    }
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Summary Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = report.summary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = report.recommendation,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

                // Findings List
                report.items.forEach { item ->
                    DiagnosticItemView(
                        item = item,
                        context = context
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onReRun,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Re-check", fontSize = 13.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = strings.close, fontSize = 13.sp)
            }
        }
    )
}

@Composable
private fun DiagnosticItemView(
    item: DiagnosticItem,
    context: Context
) {
    val (icon, badgeType) = when (item.severity) {
        DiagnosticSeverity.GOOD -> Pair(Icons.Default.CheckCircle, BadgeType.SUCCESS)
        DiagnosticSeverity.WARNING -> Pair(Icons.Default.Warning, BadgeType.WARNING)
        DiagnosticSeverity.ALERT -> Pair(Icons.Default.BatteryAlert, BadgeType.ALERT)
        DiagnosticSeverity.INFO -> Pair(Icons.Default.Info, BadgeType.INFO)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = when (item.severity) {
                        DiagnosticSeverity.GOOD -> MaterialTheme.colorScheme.primary
                        DiagnosticSeverity.WARNING -> MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                        DiagnosticSeverity.ALERT -> MaterialTheme.colorScheme.error
                        DiagnosticSeverity.INFO -> MaterialTheme.colorScheme.secondary
                    },
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = item.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            StatusBadge(
                text = item.severity.name,
                type = badgeType
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = item.description,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            lineHeight = 16.sp
        )

        if (item.actionType != DiagnosticActionType.NONE) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    when (item.actionType) {
                        DiagnosticActionType.OPEN_NOTIFICATION_LISTENER -> {
                            try {
                                val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            } catch (_: Exception) {}
                        }
                        DiagnosticActionType.OPEN_BATTERY_SETTINGS -> {
                            try {
                                val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            } catch (_: Exception) {}
                        }
                        DiagnosticActionType.OPEN_OEM_AUTOSTART -> {
                            ManufacturerHelper.openManufacturerAutostartSettings(context)
                        }
                        DiagnosticActionType.OPEN_NOTIFICATION_PERMISSION -> {
                            try {
                                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            } catch (_: Exception) {}
                        }
                        DiagnosticActionType.CHECK_NETWORK -> {
                            try {
                                val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            } catch (_: Exception) {}
                        }
                        DiagnosticActionType.NONE -> {}
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Open Setting", fontSize = 12.sp)
            }
        }
    }
}
