package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.domain.model.AppLanguage
import com.example.domain.model.DiagnosticSeverity
import com.example.domain.model.NexGuardThemeMode
import com.example.localization.AppStrings
import com.example.notification.diagnostics.DiagnosticsEngine
import com.example.notification.diagnostics.ManufacturerHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("NEXGUARD", appName)
    }

    @Test
    fun `test localization for all 25 languages`() {
        for (lang in AppLanguage.entries) {
            val strings = AppStrings.get(lang)
            assertNotNull(strings)
            assertEquals("App name must be NEXGUARD for ${lang.code}", "NEXGUARD", strings.appName)
            assertTrue("Subtitle must not be blank for ${lang.code}", strings.appSubtitle.isNotBlank())
            assertTrue("Finish setup must not be blank for ${lang.code}", strings.finishSetup.isNotBlank())
            assertTrue("Strict gate message must not be blank for ${lang.code}", strings.pleaseGrantRequiredPermissions.isNotBlank())
            assertTrue("Theme Black must not be blank for ${lang.code}", strings.themeBlack.isNotBlank())
            assertTrue("Theme White must not be blank for ${lang.code}", strings.themeWhite.isNotBlank())
            assertTrue("Theme GoldBlack must not be blank for ${lang.code}", strings.themeGoldenBlack.isNotBlank())
            assertTrue("Theme GoldWhite must not be blank for ${lang.code}", strings.themeGoldenWhite.isNotBlank())
            assertTrue("Start protection must not be blank for ${lang.code}", strings.startProtection.isNotBlank())
            assertTrue("Protect in background must not be blank for ${lang.code}", strings.protectInBackground.isNotBlank())
            assertTrue("Stop protection must not be blank for ${lang.code}", strings.stopProtection.isNotBlank())
            assertTrue("Protection notice must not be blank for ${lang.code}", strings.protectionStartedNotice.isNotBlank())
        }
    }

    @Test
    fun `test diagnostics engine generates report`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val report = DiagnosticsEngine.runDiagnostics(context)
        assertNotNull(report)
        assertTrue(report.items.isNotEmpty())
        assertNotNull(report.summary)
        assertNotNull(report.recommendation)
    }

    @Test
    fun `test manufacturer advice available`() {
        val advice = ManufacturerHelper.getManufacturerAdvice()
        assertTrue(advice.isNotBlank())
        val name = ManufacturerHelper.getManufacturerName()
        assertTrue(name.isNotBlank())
    }

    @Test
    fun `test 4 nexguard themes defined`() {
        assertEquals(4, NexGuardThemeMode.entries.size)
        assertTrue(NexGuardThemeMode.entries.contains(NexGuardThemeMode.BLACK))
        assertTrue(NexGuardThemeMode.entries.contains(NexGuardThemeMode.WHITE))
        assertTrue(NexGuardThemeMode.entries.contains(NexGuardThemeMode.GOLDEN_BLACK))
        assertTrue(NexGuardThemeMode.entries.contains(NexGuardThemeMode.GOLDEN_WHITE))
    }
}
