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

    @Test
    fun statusStringsAreNotIdenticalToProtectionGlossaryWords() {
        val statusKeys = listOf(
            "statusProtectionActive",
            "statusProtectionActiveDesc",
            "statusMonitoringPaused",
            "statusMonitoringPausedDesc",
            "actionEnableProtection",
            "startProtection",
            "stopProtection",
            "protectInBackground",
            "protectionDetailsTitle",
            "protectionStartedNotice"
        )
        AppLanguage.entries.forEach { language ->
            val strings = AppStrings.get(language)
            val glossary = protectionGlossary.getValue(language)
            statusKeys.forEach { key ->
                val methodName = "get" + key.replaceFirstChar { it.uppercase() }
                val value = strings.javaClass.methods.first { it.name == methodName }.invoke(strings) as String
                val withoutBrand = value.replace("NEXGUARD", "", ignoreCase = true)
                glossary.forEach { word ->
                    assertFalse(
                        "$language $key is identical to glossary word $word",
                        withoutBrand.equals(word, ignoreCase = true)
                    )
                    assertFalse(
                        "$language $key contains glossary word $word: $value",
                        withoutBrand.contains(word, ignoreCase = true)
                    )
                }
            }
        }
    }

    companion object {
        val protectionGlossary: Map<AppLanguage, List<String>> = mapOf(
            AppLanguage.EN to listOf("protection", "protect", "guard"),
            AppLanguage.ZH_CN to listOf("保护", "防护", "守卫"),
            AppLanguage.ZH_TW to listOf("保護", "防護", "守衛"),
            AppLanguage.RU to listOf("защита", "защитить", "страж"),
            AppLanguage.PT_BR to listOf("proteção", "proteger", "guarda"),
            AppLanguage.UR to listOf("حفاظت", "محفوظ", "گارڈ"),
            AppLanguage.AR to listOf("حماية", "يحمي", "حارس"),
            AppLanguage.HI to listOf("सुरक्षा", "सुरक्षित", "गार्ड"),
            AppLanguage.ES to listOf("protección", "proteger", "guarda"),
            AppLanguage.FR to listOf("protection", "protéger", "garde"),
            AppLanguage.DE to listOf("Schutz", "schützen", "Wächter"),
            AppLanguage.TR to listOf("koruma", "korumak", "bekçi"),
            AppLanguage.ID to listOf("perlindungan", "melindungi", "penjaga"),
            AppLanguage.JA to listOf("保護", "守る", "番人"),
            AppLanguage.KO to listOf("보호", "지키다", "경비"),
            AppLanguage.BN to listOf("সুরক্ষা", "রক্ষা", "প্রহরী"),
            AppLanguage.PA to listOf("ਸੁਰੱਖਿਆ", "ਰੱਖਿਆ", "ਪਹਿਰੇਦਾਰ"),
            AppLanguage.FA to listOf("محافظت", "محافظ", "نگهبان"),
            AppLanguage.IT to listOf("protezione", "proteggere", "guardia"),
            AppLanguage.VI to listOf("bảo vệ", "che chở", "canh gác"),
            AppLanguage.TH to listOf("การป้องกัน", "ป้องกัน", "ผู้พิทักษ์"),
            AppLanguage.PL to listOf("ochrona", "chronić", "strażnik"),
            AppLanguage.UK to listOf("захист", "захистити", "вартовий"),
            AppLanguage.NL to listOf("bescherming", "beschermen", "bewaker"),
            AppLanguage.SW to listOf("ulinzi", "kulinda", "mlinzi")
        )
    }
}
