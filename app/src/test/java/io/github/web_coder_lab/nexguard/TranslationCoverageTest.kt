package io.github.web_coder_lab.nexguard

import io.github.web_coder_lab.nexguard.domain.model.AppLanguage
import io.github.web_coder_lab.nexguard.localization.AppStrings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.reflect.Modifier

class TranslationCoverageTest {

    private val english = AppStrings.get(AppLanguage.EN)
    private val keys = english.javaClass.declaredMethods
        .filter { it.parameterCount == 0 && it.returnType == String::class.java && it.name.startsWith("get") }
        .map { it.name.removePrefix("get").replaceFirstChar { c -> c.lowercase() } }

    @Test
    fun everyNonEnglishLanguageOverridesEveryKey() {
        AppLanguage.entries.filter { it != AppLanguage.EN }.forEach { language ->
            val strings = AppStrings.get(language)
            val clazz = strings.javaClass
            keys.forEach { key ->
                val methodName = "get" + key.replaceFirstChar { it.uppercase() }
                val method = clazz.methods.first { it.name == methodName && it.parameterCount == 0 }
                assertEquals(
                    "$language must override $key",
                    clazz,
                    method.declaringClass
                )
                val value = method.invoke(strings) as String
                assertTrue("$language $key is empty", value.isNotBlank())
            }
        }
    }

    @Test
    fun placeholdersMatchEnglish() {
        val placeholder = Regex("%(\\d+\\$)?[sd]")
        AppLanguage.entries.filter { it != AppLanguage.EN }.forEach { language ->
            val strings = AppStrings.get(language)
            keys.forEach { key ->
                val methodName = "get" + key.replaceFirstChar { it.uppercase() }
                val englishValue = english.javaClass.methods.first { it.name == methodName }.invoke(english) as String
                val value = strings.javaClass.methods.first { it.name == methodName }.invoke(strings) as String
                assertEquals(
                    "$language $key placeholders",
                    placeholder.findAll(englishValue).map { it.value }.toList(),
                    placeholder.findAll(value).map { it.value }.toList()
                )
            }
        }
    }

    @Test
    fun statusStringsDoNotUseOldProtectionWording() {
        val statusKeys = keys.filter {
            it.startsWith("status") || it == "startProtection" || it == "stopProtection" || it == "protectInBackground" || it == "actionEnableProtection" || it == "protectionDetailsTitle"
        }
        val forbidden = listOf("NexGuard", "Guardian", "Protection", "Protect")
        AppLanguage.entries.forEach { language ->
            val strings = AppStrings.get(language)
            statusKeys.forEach { key ->
                val methodName = "get" + key.replaceFirstChar { it.uppercase() }
                val value = strings.javaClass.methods.first { it.name == methodName }.invoke(strings) as String
                forbidden.forEach { word ->
                    assertFalse("$language $key contains $word: $value", value.contains(word, ignoreCase = true))
                }
            }
        }
    }
}
