package io.github.web_coder_lab.nexguard.domain.model

enum class AppLanguage(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val isRtl: Boolean = false
) {
    EN("en", "English", "English"),
    ZH_CN("zh-CN", "简体中文", "Simplified Chinese"),
    ZH_TW("zh-TW", "繁體中文", "Traditional Chinese"),
    RU("ru", "Русский", "Russian"),
    PT_BR("pt-BR", "Português (Brasil)", "Portuguese (Brazil)"),
    UR("ur", "اردو", "Urdu", isRtl = true),
    AR("ar", "العربية", "Arabic", isRtl = true),
    HI("hi", "हिन्दी", "Hindi"),
    ES("es", "Español", "Spanish"),
    FR("fr", "Français", "French"),
    DE("de", "Deutsch", "German"),
    TR("tr", "Türkçe", "Turkish"),
    ID("id", "Bahasa Indonesia", "Indonesian"),
    JA("ja", "日本語", "Japanese"),
    KO("ko", "한국어", "Korean"),
    BN("bn", "বাংলা", "Bengali"),
    PA("pa", "ਪੰਜਾਬੀ", "Punjabi"),
    FA("fa", "فارسی", "Persian", isRtl = true),
    IT("it", "Italiano", "Italian"),
    VI("vi", "Tiếng Việt", "Vietnamese"),
    TH("th", "ไทย", "Thai"),
    PL("pl", "Polski", "Polish"),
    UK("uk", "Українська", "Ukrainian"),
    NL("nl", "Nederlands", "Dutch"),
    SW("sw", "Kiswahili", "Swahili");

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.firstOrNull { it.code.equals(code, ignoreCase = true) }
                ?: entries.firstOrNull { it.code.startsWith(code, ignoreCase = true) }
                ?: EN
        }
    }
}
