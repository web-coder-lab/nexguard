package com.example.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BatterySaver
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.preferences.AlertPreference
import com.example.data.preferences.GuardianPreferences
import com.example.data.preferences.HistoryPrivacyMode
import com.example.domain.model.AppLanguage
import com.example.domain.model.DiagnosticReport
import com.example.domain.model.GuardianThemeMode
import com.example.localization.AppStrings
import com.example.notification.diagnostics.ManufacturerHelper
import com.example.ui.components.BadgeType
import com.example.ui.components.StatusBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    preferences: GuardianPreferences,
    onBack: () -> Unit,
    onUpdateTheme: (GuardianThemeMode) -> Unit,
    onUpdateLanguage: (AppLanguage) -> Unit,
    onUpdateReducedMotion: (Boolean) -> Unit,
    onUpdateHaptics: (Boolean) -> Unit,
    onUpdateMonitoring: (Boolean) -> Unit,
    onUpdateHistoryPrivacy: (HistoryPrivacyMode) -> Unit,
    onUpdateAlertPreference: (AlertPreference) -> Unit,
    onUpdateRetentionDays: (Int) -> Unit,
    onNavigateToAppManager: () -> Unit,
    onRunDiagnostics: () -> Unit,
    onClearHistory: () -> Unit,
    onResetPreferences: () -> Unit
) {
    BackHandler { onBack() }

    val context = LocalContext.current
    val strings = AppStrings.get(preferences.language)

    var showLanguageDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var showAlertPrefDialog by remember { mutableStateOf(false) }
    var showRetentionDialog by remember { mutableStateOf(false) }
    var showClearHistoryDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    // Dialogs
    if (showLanguageDialog) {
        LanguageDialog(
            currentLanguage = preferences.language,
            onDismiss = { showLanguageDialog = false },
            onSelect = {
                onUpdateLanguage(it)
                showLanguageDialog = false
            }
        )
    }

    if (showThemeDialog) {
        ThemeDialog(
            currentTheme = preferences.themeMode,
            currentLanguage = preferences.language,
            onDismiss = { showThemeDialog = false },
            onSelect = {
                onUpdateTheme(it)
                showThemeDialog = false
            }
        )
    }

    if (showAlertPrefDialog) {
        AlertPreferenceDialog(
            current = preferences.alertPreference,
            currentLanguage = preferences.language,
            onDismiss = { showAlertPrefDialog = false },
            onSelect = {
                onUpdateAlertPreference(it)
                showAlertPrefDialog = false
            }
        )
    }

    if (showRetentionDialog) {
        RetentionDialog(
            currentDays = preferences.retentionDays,
            currentLanguage = preferences.language,
            onDismiss = { showRetentionDialog = false },
            onSelect = {
                onUpdateRetentionDays(it)
                showRetentionDialog = false
            }
        )
    }

    if (showClearHistoryDialog) {
        AlertDialog(
            onDismissRequest = { showClearHistoryDialog = false },
            title = { Text(strings.clearHistory) },
            text = { Text(strings.clearHistoryConfirm) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClearHistory()
                        showClearHistoryDialog = false
                    },
                    modifier = Modifier.testTag("confirm_clear_history_button")
                ) {
                    Text(strings.confirm)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearHistoryDialog = false }) {
                    Text(strings.cancel)
                }
            }
        )
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text(strings.resetSettings) },
            text = { Text(strings.resetSettingsConfirm) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onResetPreferences()
                        showResetDialog = false
                    },
                    modifier = Modifier.testTag("confirm_reset_settings_button")
                ) {
                    Text(strings.confirm)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text(strings.cancel)
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("settings_screen"),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = strings.settingsTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("settings_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // SECTION A: APPEARANCE
            SettingsSection(title = strings.sectionAppearance) {
                SettingsClickableItem(
                    icon = Icons.Default.Palette,
                    title = "Theme",
                    subtitle = when (preferences.themeMode) {
                        GuardianThemeMode.BLACK -> strings.themeBlack
                        GuardianThemeMode.WHITE -> strings.themeWhite
                        GuardianThemeMode.GOLDEN_BLACK -> strings.themeGoldenBlack
                        GuardianThemeMode.GOLDEN_WHITE -> strings.themeGoldenWhite
                    },
                    onClick = { showThemeDialog = true },
                    testTag = "settings_theme_item"
                )

                SettingsToggleItem(
                    title = strings.reducedMotion,
                    subtitle = strings.reducedMotionDesc,
                    checked = preferences.reducedMotion,
                    onCheckedChange = onUpdateReducedMotion,
                    testTag = "settings_reduced_motion_toggle"
                )

                SettingsToggleItem(
                    title = strings.hapticFeedback,
                    subtitle = strings.hapticFeedbackDesc,
                    checked = preferences.hapticFeedback,
                    onCheckedChange = onUpdateHaptics,
                    testTag = "settings_haptic_toggle"
                )
            }

            // SECTION B: LANGUAGE
            SettingsSection(title = strings.sectionLanguage) {
                SettingsClickableItem(
                    icon = Icons.Default.Language,
                    title = strings.currentLanguage,
                    subtitle = "${preferences.language.nativeName} (${preferences.language.englishName})",
                    onClick = { showLanguageDialog = true },
                    testTag = "settings_language_item"
                )
            }

            // SECTION C: NOTIFICATION MONITORING
            SettingsSection(title = strings.sectionMonitoring) {
                SettingsToggleItem(
                    title = strings.monitoringActive,
                    subtitle = strings.monitoringActiveDesc,
                    checked = preferences.monitoringActive,
                    onCheckedChange = onUpdateMonitoring,
                    testTag = "settings_monitoring_toggle"
                )

                SettingsClickableItem(
                    icon = Icons.Default.NotificationsActive,
                    title = strings.diagnosticAlerts,
                    subtitle = when (preferences.alertPreference) {
                        AlertPreference.SILENT -> strings.alertsSilent
                        AlertPreference.CRITICAL_ONLY -> strings.alertsCriticalOnly
                        AlertPreference.ALL -> strings.alertsAll
                    },
                    onClick = { showAlertPrefDialog = true },
                    testTag = "settings_alert_pref_item"
                )

                SettingsClickableItem(
                    icon = Icons.Default.Security,
                    title = strings.historyPrivacy,
                    subtitle = if (preferences.historyPrivacyMode == HistoryPrivacyMode.METADATA_ONLY)
                        strings.historyPrivacyMetadata else strings.historyPrivacyOptIn,
                    onClick = {
                        val next = if (preferences.historyPrivacyMode == HistoryPrivacyMode.METADATA_ONLY)
                            HistoryPrivacyMode.METADATA_AND_TITLE else HistoryPrivacyMode.METADATA_ONLY
                        onUpdateHistoryPrivacy(next)
                    },
                    testTag = "settings_history_privacy_item"
                )
            }

            // SECTION D: APP MANAGEMENT
            SettingsSection(title = strings.sectionAppManagement) {
                SettingsClickableItem(
                    icon = Icons.Default.Apps,
                    title = strings.appManagerTitle,
                    subtitle = strings.sectionAppManagementDesc,
                    onClick = onNavigateToAppManager,
                    testTag = "settings_manage_apps_item"
                )
            }

            // SECTION E: BATTERY & BACKGROUND
            SettingsSection(title = strings.sectionBattery) {
                SettingsClickableItem(
                    icon = Icons.Default.BatterySaver,
                    title = strings.openBatterySettings,
                    subtitle = "Adjust Android battery optimization policy",
                    onClick = { openBatteryOptimizationSettings(context) },
                    testTag = "settings_battery_settings_item"
                )

                SettingsClickableItem(
                    icon = Icons.Default.BatterySaver,
                    title = "${ManufacturerHelper.getManufacturerName()} Optimization",
                    subtitle = ManufacturerHelper.getManufacturerAdvice(),
                    onClick = { ManufacturerHelper.openManufacturerAutostartSettings(context) },
                    testTag = "settings_oem_battery_item"
                )
            }

            // SECTION F: DIAGNOSTICS
            SettingsSection(title = strings.sectionDiagnostics) {
                SettingsClickableItem(
                    icon = Icons.Default.QueryStats,
                    title = strings.runDiagnosticsNow,
                    subtitle = if (preferences.lastDiagnosticSummary.isNotEmpty())
                        preferences.lastDiagnosticSummary else "Test listener, battery, and background status",
                    onClick = onRunDiagnostics,
                    testTag = "settings_run_diagnostics_item"
                )
            }

            // SECTION G: PRIVACY & STORAGE
            SettingsSection(title = strings.sectionPrivacyStorage) {
                SettingsClickableItem(
                    icon = Icons.Default.Security,
                    title = strings.eventRetention,
                    subtitle = "${preferences.retentionDays} days",
                    onClick = { showRetentionDialog = true },
                    testTag = "settings_retention_item"
                )

                SettingsClickableItem(
                    icon = Icons.Default.DeleteSweep,
                    title = strings.clearHistory,
                    subtitle = "Erase all local notification event logs",
                    onClick = { showClearHistoryDialog = true },
                    testTag = "settings_clear_history_item"
                )

                SettingsClickableItem(
                    icon = Icons.Default.RestartAlt,
                    title = strings.resetSettings,
                    subtitle = "Reset preferences to factory defaults",
                    onClick = { showResetDialog = true },
                    testTag = "settings_reset_preferences_item"
                )
            }

            // App Version Footer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Guardian v1.0.0 • Local-First & Private",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
private fun SettingsClickableItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    testTag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag(testTag),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun SettingsToggleItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    testTag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag(testTag),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
private fun LanguageDialog(
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onSelect: (AppLanguage) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val filtered = remember(query) {
        if (query.isBlank()) AppLanguage.entries
        else AppLanguage.entries.filter {
            it.nativeName.contains(query, ignoreCase = true) ||
                    it.englishName.contains(query, ignoreCase = true)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Language") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Search...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    filtered.forEach { lang ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(lang) }
                                .padding(vertical = 10.dp, horizontal = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(lang.nativeName, fontWeight = FontWeight.SemiBold)
                                Text(
                                    lang.englishName,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                            if (lang == currentLanguage) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
private fun ThemeDialog(
    currentTheme: GuardianThemeMode,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onSelect: (GuardianThemeMode) -> Unit
) {
    val strings = AppStrings.get(currentLanguage)
    val themes = listOf(
        Pair(GuardianThemeMode.BLACK, strings.themeBlack),
        Pair(GuardianThemeMode.WHITE, strings.themeWhite),
        Pair(GuardianThemeMode.GOLDEN_BLACK, strings.themeGoldenBlack),
        Pair(GuardianThemeMode.GOLDEN_WHITE, strings.themeGoldenWhite)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(strings.sectionAppearance) },
        text = {
            Column {
                themes.forEach { (mode, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(mode) }
                            .padding(vertical = 12.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(label, fontWeight = FontWeight.Medium)
                        if (mode == currentTheme) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text(strings.close) } }
    )
}

@Composable
private fun AlertPreferenceDialog(
    current: AlertPreference,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onSelect: (AlertPreference) -> Unit
) {
    val strings = AppStrings.get(currentLanguage)
    val options = listOf(
        Pair(AlertPreference.SILENT, strings.alertsSilent),
        Pair(AlertPreference.CRITICAL_ONLY, strings.alertsCriticalOnly),
        Pair(AlertPreference.ALL, strings.alertsAll)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(strings.diagnosticAlerts) },
        text = {
            Column {
                options.forEach { (pref, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(pref) }
                            .padding(vertical = 12.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(label, fontWeight = FontWeight.Medium)
                        if (pref == current) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text(strings.close) } }
    )
}

@Composable
private fun RetentionDialog(
    currentDays: Int,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onSelect: (Int) -> Unit
) {
    val strings = AppStrings.get(currentLanguage)
    val options = listOf(
        Pair(1, strings.retention1Day),
        Pair(7, strings.retention7Days),
        Pair(30, strings.retention30Days)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(strings.eventRetention) },
        text = {
            Column {
                options.forEach { (days, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(days) }
                            .padding(vertical = 12.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(label, fontWeight = FontWeight.Medium)
                        if (days == currentDays) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text(strings.close) } }
    )
}

private fun openBatteryOptimizationSettings(context: Context) {
    try {
        val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (_: Exception) {
        val fallback = Intent(Settings.ACTION_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(fallback)
    }
}
