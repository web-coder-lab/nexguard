package io.github.web_coder_lab.nexguard

import io.github.web_coder_lab.nexguard.data.preferences.AlertPreference
import io.github.web_coder_lab.nexguard.data.preferences.GuardianPreferences
import io.github.web_coder_lab.nexguard.data.preferences.HistoryPrivacyMode
import io.github.web_coder_lab.nexguard.domain.model.AppItem
import io.github.web_coder_lab.nexguard.domain.model.AppLanguage
import io.github.web_coder_lab.nexguard.domain.model.GuardianThemeMode
import io.github.web_coder_lab.nexguard.domain.model.ProtectionState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GuardianDomainTest {

    @Test
    fun testAppLanguageFromCode() {
        assertEquals(AppLanguage.EN, AppLanguage.fromCode("en"))
        assertEquals(AppLanguage.ZH_CN, AppLanguage.fromCode("zh-CN"))
        assertEquals(AppLanguage.UR, AppLanguage.fromCode("ur"))
        assertEquals(AppLanguage.AR, AppLanguage.fromCode("ar"))
        assertEquals(AppLanguage.JA, AppLanguage.fromCode("ja"))
        assertEquals(AppLanguage.EN, AppLanguage.fromCode("unknown_code"))
    }

    @Test
    fun testRtlLanguages() {
        assertTrue(AppLanguage.AR.isRtl)
        assertTrue(AppLanguage.UR.isRtl)
        assertTrue(AppLanguage.FA.isRtl)
        assertFalse(AppLanguage.EN.isRtl)
        assertFalse(AppLanguage.ZH_CN.isRtl)
    }

    @Test
    fun testGuardianPreferencesDefaults() {
        val prefs = GuardianPreferences()
        assertEquals(AppLanguage.EN, prefs.language)
        assertFalse(prefs.setupCompleted)
        assertEquals(GuardianThemeMode.BLACK, prefs.themeMode)
        assertTrue(prefs.monitoringActive)
        assertEquals(HistoryPrivacyMode.METADATA_ONLY, prefs.historyPrivacyMode)
        assertEquals(AlertPreference.CRITICAL_ONLY, prefs.alertPreference)
        assertEquals(7, prefs.retentionDays)
        assertTrue(prefs.excludedPackages.isEmpty())
    }

    @Test
    fun testAppItemData() {
        val app = AppItem(
            packageName = "com.whatsapp",
            appName = "WhatsApp",
            isSystemApp = false,
            isMonitored = true
        )
        assertEquals("WhatsApp", app.appName)
        assertEquals("com.whatsapp", app.packageName)
        assertFalse(app.isSystemApp)
        assertTrue(app.isMonitored)
    }
}
