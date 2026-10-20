package io.github.web_coder_lab.nexguard

import io.github.web_coder_lab.nexguard.data.preferences.SystemAppDefaultPolicy
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SystemAppDefaultPolicyTest {

    @Test
    fun freshInstallExcludesSystemApps() {
        val installTime = 1_700_000_000_000L
        assertTrue(
            SystemAppDefaultPolicy.shouldExcludeSystemApps(
                defaultsAlreadyApplied = false,
                firstInstallTime = installTime,
                lastUpdateTime = installTime
            )
        )
    }

    @Test
    fun updateFromPreviousVersionKeepsExistingChoices() {
        assertFalse(
            SystemAppDefaultPolicy.shouldExcludeSystemApps(
                defaultsAlreadyApplied = false,
                firstInstallTime = 1_700_000_000_000L,
                lastUpdateTime = 1_700_100_000_000L
            )
        )
    }

    @Test
    fun secondLaunchDoesNotApplyDefaultsAgain() {
        val installTime = 1_700_000_000_000L
        assertFalse(
            SystemAppDefaultPolicy.shouldExcludeSystemApps(
                defaultsAlreadyApplied = true,
                firstInstallTime = installTime,
                lastUpdateTime = installTime
            )
        )
    }
}
