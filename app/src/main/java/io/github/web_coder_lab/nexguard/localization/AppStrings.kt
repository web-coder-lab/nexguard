package io.github.web_coder_lab.nexguard.localization

import io.github.web_coder_lab.nexguard.domain.model.AppLanguage

object AppStrings {

    fun get(language: AppLanguage): Strings = when (language) {
        AppLanguage.EN -> EnglishStrings
        AppLanguage.ZH_CN -> ChineseSimpStrings
        AppLanguage.ZH_TW -> ChineseTradStrings
        AppLanguage.RU -> RussianStrings
        AppLanguage.PT_BR -> PortugueseStrings
        AppLanguage.UR -> UrduStrings
        AppLanguage.AR -> ArabicStrings
        AppLanguage.HI -> HindiStrings
        AppLanguage.ES -> SpanishStrings
        AppLanguage.FR -> FrenchStrings
        AppLanguage.DE -> GermanStrings
        AppLanguage.TR -> TurkishStrings
        AppLanguage.ID -> IndonesianStrings
        AppLanguage.JA -> JapaneseStrings
        AppLanguage.KO -> KoreanStrings
        AppLanguage.BN -> BengaliStrings
        AppLanguage.PA -> PunjabiStrings
        AppLanguage.FA -> PersianStrings
        AppLanguage.IT -> ItalianStrings
        AppLanguage.VI -> VietnameseStrings
        AppLanguage.TH -> ThaiStrings
        AppLanguage.PL -> PolishStrings
        AppLanguage.UK -> UkrainianStrings
        AppLanguage.NL -> DutchStrings
        AppLanguage.SW -> SwahiliStrings
    }

    interface Strings {
        val appName: String
        val appSubtitle: String
        val selectLanguage: String
        val selectLanguageDesc: String
        val searchLanguage: String
        val continueButton: String
        val nextButton: String
        val backButton: String
        val setupTitle: String
        val setupSubtitle: String
        val permissionNotificationListener: String
        val permissionNotificationListenerDesc: String
        val permissionGuardianNotifications: String
        val permissionGuardianNotificationsDesc: String
        val permissionBatteryOptimization: String
        val permissionBatteryOptimizationDesc: String
        val permissionPrivacyNotice: String
        val permissionPrivacyNoticeDesc: String
        val grantAccess: String
        val granted: String
        val notGranted: String
        val finishSetup: String
        val pleaseGrantRequiredPermissions: String
        val allPermissionsRequired: String
        val requiredPermissions: String
        val permissionDenied: String
        val missingNotificationAccess: String
        val statusProtectionActive: String
        val statusProtectionActiveDesc: String
        val statusAccessRequired: String
        val statusAccessRequiredDesc: String
        val statusBatteryRestricted: String
        val statusBatteryRestrictedDesc: String
        val statusMonitoringPaused: String
        val statusMonitoringPausedDesc: String
        val rowNotificationAccess: String
        val rowMonitoringState: String
        val rowBatteryOptimization: String
        val rowMonitoredApps: String
        val rowLastDiagnostic: String
        val actionEnableProtection: String
        val actionRunDiagnostics: String
        val actionManageApps: String
        val actionSettings: String
        val actionFixPermissions: String
        val settingsTitle: String
        val sectionAppearance: String
        val themeBlack: String
        val themeWhite: String
        val themeGoldenBlack: String
        val themeGoldenWhite: String
        val reducedMotion: String
        val reducedMotionDesc: String
        val hapticFeedback: String
        val hapticFeedbackDesc: String
        val sectionLanguage: String
        val currentLanguage: String
        val sectionMonitoring: String
        val monitoringActive: String
        val monitoringActiveDesc: String
        val monitoringUnavailable: String
        val historyPrivacy: String
        val historyPrivacyMetadata: String
        val historyPrivacyOptIn: String
        val diagnosticAlerts: String
        val alertsSilent: String
        val alertsCriticalOnly: String
        val alertsAll: String
        val sectionAppManagement: String
        val sectionAppManagementDesc: String
        val sectionBattery: String
        val sectionBatteryDesc: String
        val openBatterySettings: String
        val sectionDiagnostics: String
        val runDiagnosticsNow: String
        val sectionPrivacyStorage: String
        val eventRetention: String
        val retention1Day: String
        val retention7Days: String
        val retention30Days: String
        val clearHistory: String
        val clearHistoryConfirm: String
        val resetSettings: String
        val resetSettingsConfirm: String
        val cancel: String
        val confirm: String
        val save: String
        val changesSaved: String
        val changesNotSaved: String
        val appManagerTitle: String
        val searchApps: String
        val filterAll: String
        val filterUser: String
        val filterSystem: String
        val monitoredStatus: String
        val unmonitoredStatus: String
        val openAppNotificationSettings: String
        val openAppDetailsSettings: String
        val appTroubleshootingAdvice: String
        val noAppsFound: String
        val diagnosticReportTitle: String
        val allChecksPassed: String
        val issuesDetected: String
        val manufacturerGuidance: String
        val protectionDetailsTitle: String
        val close: String
        val startProtection: String
        val protectInBackground: String
        val stopProtection: String
        val protectionStartedNotice: String
        val aboutTitle: String
        val aboutVersion: String
        val aboutGitHub: String
        val aboutLicense: String
        val aboutOpenSource: String
    }

    object EnglishStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "Notification Health Monitor"
        override val selectLanguage = "Choose Language"
        override val selectLanguageDesc = "Select your preferred language. You can change this anytime."
        override val searchLanguage = "Search languages..."
        override val continueButton = "Continue"
        override val nextButton = "Next"
        override val backButton = "Back"
        override val setupTitle = "Permission Setup"
        override val setupSubtitle = "Configure system access to keep notifications reliable"
        override val permissionNotificationListener = "Notification Access (Required)"
        override val permissionNotificationListenerDesc = "Allows NEXGUARD to monitor notification events locally from your apps to detect delivery restrictions."
        override val permissionGuardianNotifications = "NEXGUARD Alerts"
        override val permissionGuardianNotificationsDesc = "Receive concise diagnostic notices if background delivery is blocked."
        override val permissionBatteryOptimization = "Background Reliability"
        override val permissionBatteryOptimizationDesc = "Exempt NEXGUARD from aggressive OS battery kill rules so it stays connected."
        override val permissionPrivacyNotice = "Private & On-Device"
        override val permissionPrivacyNoticeDesc = "Metadata only. NEXGUARD never logs or transmits notification message contents."
        override val grantAccess = "Grant"
        override val granted = "Active"
        override val notGranted = "Action Needed"
        override val finishSetup = "Complete Setup"
        override val pleaseGrantRequiredPermissions = "Please grant the required permissions before continuing."
        override val allPermissionsRequired = "Notification Access is required to continue."
        override val requiredPermissions = "Required Permissions"
        override val permissionDenied = "Permission Denied"
        override val missingNotificationAccess = "Missing: Notification Access"
        override val statusProtectionActive = "Monitoring Active"
        override val statusProtectionActiveDesc = "Monitoring notification delivery in background"
        override val statusAccessRequired = "Access Required"
        override val statusAccessRequiredDesc = "Grant notification access to start monitoring"
        override val statusBatteryRestricted = "Battery Restricted"
        override val statusBatteryRestrictedDesc = "OS battery optimization may delay notifications"
        override val statusMonitoringPaused = "Monitoring Paused"
        override val statusMonitoringPausedDesc = "Notification monitoring is paused by user"
        override val rowNotificationAccess = "Notification Access"
        override val rowMonitoringState = "Monitoring State"
        override val rowBatteryOptimization = "Battery Policy"
        override val rowMonitoredApps = "Monitored Apps"
        override val rowLastDiagnostic = "System Health"
        override val actionEnableProtection = "Enable Monitoring"
        override val actionRunDiagnostics = "Run Diagnostics"
        override val actionManageApps = "Manage Apps"
        override val actionSettings = "Settings"
        override val actionFixPermissions = "Fix Permissions"
        override val settingsTitle = "Settings"
        override val sectionAppearance = "Appearance"
        override val themeBlack = "Black"
        override val themeWhite = "White"
        override val themeGoldenBlack = "Golden & Black"
        override val themeGoldenWhite = "Golden & White"
        override val reducedMotion = "Reduced Motion"
        override val reducedMotionDesc = "Disable non-essential pulse and transitions"
        override val hapticFeedback = "Haptic Feedback"
        override val hapticFeedbackDesc = "Vibrate on interactions and button taps"
        override val sectionLanguage = "Language"
        override val currentLanguage = "App Language"
        override val sectionMonitoring = "Notification Monitoring"
        override val monitoringActive = "Monitoring active"
        override val monitoringActiveDesc = "Active local listener for delivery status"
        override val monitoringUnavailable = "Monitoring unavailable"
        override val historyPrivacy = "History Privacy"
        override val historyPrivacyMetadata = "Metadata only (Recommended)"
        override val historyPrivacyOptIn = "Metadata + Titles"
        override val diagnosticAlerts = "Alert Frequency"
        override val alertsSilent = "Silent Monitoring"
        override val alertsCriticalOnly = "Critical Alerts Only"
        override val alertsAll = "All Diagnostic Alerts"
        override val sectionAppManagement = "Installed Apps"
        override val sectionAppManagementDesc = "Configure individual app monitoring rules"
        override val sectionBattery = "Battery & Background"
        override val sectionBatteryDesc = "Review device battery restrictions & OEM guidance"
        override val openBatterySettings = "Open Battery Settings"
        override val sectionDiagnostics = "Diagnostics Engine"
        override val runDiagnosticsNow = "Run Full Check"
        override val sectionPrivacyStorage = "Privacy & Local Data"
        override val eventRetention = "Event History Retention"
        override val retention1Day = "1 Day"
        override val retention7Days = "7 Days"
        override val retention30Days = "30 Days"
        override val clearHistory = "Clear Event Log"
        override val clearHistoryConfirm = "Are you sure you want to clear the notification event history?"
        override val resetSettings = "Reset Preferences"
        override val resetSettingsConfirm = "Reset all NEXGUARD preferences to default values?"
        override val cancel = "Cancel"
        override val confirm = "Confirm"
        override val save = "Save"
        override val changesSaved = "Changes saved"
        override val changesNotSaved = "Changes could not be saved"
        override val appManagerTitle = "Manage Apps"
        override val searchApps = "Search apps..."
        override val filterAll = "All"
        override val filterUser = "User"
        override val filterSystem = "System"
        override val monitoredStatus = "Monitored"
        override val unmonitoredStatus = "Excluded"
        override val openAppNotificationSettings = "App Notification Settings"
        override val openAppDetailsSettings = "System App Info"
        override val appTroubleshootingAdvice = "Ensure unrestricted background data and battery usage in system settings for best notification reliability."
        override val noAppsFound = "No applications match your filter"
        override val diagnosticReportTitle = "Diagnostic Results"
        override val allChecksPassed = "All systems functioning normally"
        override val issuesDetected = "Issues detected affecting reliability"
        override val manufacturerGuidance = "Device Manufacturer Advice"
        override val protectionDetailsTitle = "Monitoring Status"
        override val close = "Close"
        override val startProtection = "Start Monitoring"
        override val protectInBackground = "Start Monitoring"
        override val stopProtection = "Stop Monitoring"
        override val protectionStartedNotice = "NEXGUARD is now active and monitoring notifications in the background."
        override val aboutTitle = "About"
        override val aboutVersion = "Version"
        override val aboutGitHub = "Source code"
        override val aboutLicense = "License"
        override val aboutOpenSource = "Open-source libraries: AndroidX, Jetpack Compose, Room, DataStore, WorkManager, Kotlin Coroutines."
    }

    object ChineseSimpStrings : Strings by EnglishStrings {
        override val appSubtitle = "智能通知保护"
        override val selectLanguage = "选择语言"
        override val selectLanguageDesc = "请选择您的首选语言。您可以随时在设置中更改。"
        override val searchLanguage = "搜索语言..."
        override val continueButton = "继续"
        override val nextButton = "下一步"
        override val backButton = "返回"
        override val setupTitle = "权限配置"
        override val setupSubtitle = "配置系统权限以保障通知稳定接收"
        override val permissionNotificationListener = "通知使用权 (必需)"
        override val permissionNotificationListenerDesc = "允许 NexGuard 在本地监听通知事件，以排查延迟或限制。"
        override val permissionGuardianNotifications = "NexGuard 提醒"
        override val permissionGuardianNotificationsDesc = "当后台传递受阻时接收简明诊断通知。"
        override val permissionBatteryOptimization = "后台常驻保护"
        override val permissionBatteryOptimizationDesc = "解除电池优化以防系统在后台误杀 NexGuard。"
        override val permissionPrivacyNotice = "纯本地隐私"
        override val permissionPrivacyNoticeDesc = "仅记录时间与元数据，绝不上传或记录消息内容。"
        override val grantAccess = "授予"
        override val granted = "已激活"
        override val notGranted = "需要处理"
        override val finishSetup = "完成配置"
        override val pleaseGrantRequiredPermissions = "请在继续前授予必需的权限。"
        override val allPermissionsRequired = "必须授予通知使用权才能继续。"
        override val requiredPermissions = "必需权限"
        override val permissionDenied = "权限被拒绝"
        override val missingNotificationAccess = "缺少：通知使用权"
        override val statusProtectionActive = "保护已激活"
        override val statusProtectionActiveDesc = "正在后台实时监测通知可靠性"
        override val statusAccessRequired = "需要通知权限"
        override val statusAccessRequiredDesc = "请开启通知使用权以激活实时保护"
        override val statusBatteryRestricted = "受到电池限制"
        override val statusBatteryRestrictedDesc = "系统省电策略可能导致通知延迟"
        override val statusMonitoringPaused = "保护已暂停"
        override val statusMonitoringPausedDesc = "用户已手动暂停通知监测"
        override val actionEnableProtection = "开启保护"
        override val actionRunDiagnostics = "运行诊断"
        override val actionManageApps = "管理应用"
        override val actionSettings = "设置"
        override val actionFixPermissions = "修复权限"
        override val settingsTitle = "设置"
        override val sectionAppearance = "外观主题"
        override val themeBlack = "纯黑"
        override val themeWhite = "纯白"
        override val themeGoldenBlack = "黑金"
        override val themeGoldenWhite = "白金"
        override val reducedMotion = "减弱动画"
        override val hapticFeedback = "触感反馈"
        override val sectionLanguage = "语言"
        override val currentLanguage = "应用语言"
        override val sectionMonitoring = "通知监测"
        override val monitoringActive = "监测已激活"
        override val monitoringUnavailable = "监测不可用"
        override val sectionAppManagement = "应用管理"
        override val sectionBattery = "电池与后台"
        override val sectionDiagnostics = "诊断引擎"
        override val runDiagnosticsNow = "立即诊断"
        override val sectionPrivacyStorage = "隐私与存储"
        override val clearHistory = "清空事件记录"
        override val resetSettings = "恢复默认设置"
        override val cancel = "取消"
        override val confirm = "确认"
        override val save = "保存"
        override val changesSaved = "更改已保存"
        override val changesNotSaved = "无法保存更改"
        override val appManagerTitle = "应用管理"
        override val searchApps = "搜索应用..."
        override val filterAll = "全部"
        override val filterUser = "用户"
        override val filterSystem = "系统"
        override val close = "关闭"
        override val startProtection = "开启保护"
        override val protectInBackground = "后台持续保护"
        override val stopProtection = "停止保护"
        override val protectionStartedNotice = "NexGuard 已在后台实时保护您的通知。"
    }

    object ChineseTradStrings : Strings by ChineseSimpStrings {
        override val appSubtitle = "智慧通知保護"
        override val selectLanguage = "選擇語言"
        override val continueButton = "繼續"
        override val nextButton = "下一步"
        override val backButton = "返回"
        override val setupTitle = "權限設定"
        override val setupSubtitle = "配置系統權限以確保通知穩定接收"
        override val permissionNotificationListener = "通知存取權 (必需)"
        override val permissionNotificationListenerDesc = "允許 NexGuard 在本機監聽通知事件，以排查延遲或限制。"
        override val granted = "已啟用"
        override val notGranted = "需要設定"
        override val finishSetup = "完成設定"
        override val pleaseGrantRequiredPermissions = "請在繼續之前授予必要的權限。"
        override val allPermissionsRequired = "必須授予通知存取權才能繼續。"
        override val statusProtectionActive = "保護已啟用"
        override val statusAccessRequired = "需要通知權限"
        override val settingsTitle = "設定"
        override val themeBlack = "純黑"
        override val themeWhite = "純白"
        override val themeGoldenBlack = "黑金"
        override val themeGoldenWhite = "白金"
        override val changesSaved = "變更已儲存"
        override val startProtection = "開啟保護"
        override val protectInBackground = "背景持續保護"
        override val stopProtection = "停止保護"
        override val protectionStartedNotice = "NexGuard 已在背景即時保護您的通知。"
    }

    object RussianStrings : Strings by EnglishStrings {
        override val appSubtitle = "Умная защита уведомлений"
        override val selectLanguage = "Выберите язык"
        override val selectLanguageDesc = "Выберите предпочитаемый язык. Вы можете изменить его в любое время."
        override val continueButton = "Продолжить"
        override val nextButton = "Далее"
        override val backButton = "Назад"
        override val setupTitle = "Настройка разрешений"
        override val permissionNotificationListener = "Доступ к уведомлениям (Обязательно)"
        override val grantAccess = "Разрешить"
        override val granted = "Активно"
        override val notGranted = "Требуется действие"
        override val finishSetup = "Завершить настройку"
        override val pleaseGrantRequiredPermissions = "Пожалуйста, предоставьте необходимые разрешения перед продолжением."
        override val allPermissionsRequired = "Для продолжения необходим доступ к уведомлениям."
        override val statusProtectionActive = "Защита активна"
        override val statusAccessRequired = "Требуется доступ"
        override val actionEnableProtection = "Включить защиту"
        override val actionRunDiagnostics = "Диагностика"
        override val actionManageApps = "Управление приложениями"
        override val actionSettings = "Настройки"
        override val settingsTitle = "Настройки"
        override val themeBlack = "Черная"
        override val themeWhite = "Белая"
        override val themeGoldenBlack = "Золото и Черный"
        override val themeGoldenWhite = "Золото и Белый"
        override val monitoringActive = "Мониторинг активен"
        override val monitoringUnavailable = "Мониторинг недоступен"
        override val cancel = "Отмена"
        override val confirm = "Подтвердить"
        override val save = "Сохранить"
        override val changesSaved = "Изменения сохранены"
        override val changesNotSaved = "Не удалось сохранить изменения"
        override val close = "Закрыть"
    }

    object SpanishStrings : Strings by EnglishStrings {
        override val appSubtitle = "Protección inteligente de notificaciones"
        override val selectLanguage = "Elegir idioma"
        override val continueButton = "Continuar"
        override val nextButton = "Siguiente"
        override val backButton = "Atrás"
        override val setupTitle = "Configuración de permisos"
        override val permissionNotificationListener = "Acceso a notificaciones (Obligatorio)"
        override val grantAccess = "Permitir"
        override val granted = "Activo"
        override val notGranted = "Acción requerida"
        override val finishSetup = "Completar configuración"
        override val pleaseGrantRequiredPermissions = "Por favor, conceda los permisos requeridos antes de continuar."
        override val allPermissionsRequired = "Se requiere acceso a notificaciones para continuar."
        override val statusProtectionActive = "Protección activa"
        override val statusAccessRequired = "Acceso requerido"
        override val actionEnableProtection = "Activar protección"
        override val actionRunDiagnostics = "Ejecutar diagnóstico"
        override val actionManageApps = "Gestionar aplicaciones"
        override val actionSettings = "Ajustes"
        override val settingsTitle = "Ajustes"
        override val themeBlack = "Negro"
        override val themeWhite = "Blanco"
        override val themeGoldenBlack = "Dorado y Negro"
        override val themeGoldenWhite = "Dorado y Blanco"
        override val monitoringActive = "Monitoreo activo"
        override val monitoringUnavailable = "Monitoreo no disponible"
        override val cancel = "Cancelar"
        override val confirm = "Confirmar"
        override val save = "Guardar"
        override val changesSaved = "Cambios guardados"
        override val changesNotSaved = "No se pudieron guardar los cambios"
        override val close = "Cerrar"
    }

    object PortugueseStrings : Strings by EnglishStrings {
        override val appSubtitle = "Proteção inteligente de notificações"
        override val selectLanguage = "Escolher idioma"
        override val continueButton = "Continuar"
        override val nextButton = "Avançar"
        override val backButton = "Voltar"
        override val setupTitle = "Configuração de permissões"
        override val permissionNotificationListener = "Acesso a notificações (Obrigatório)"
        override val grantAccess = "Conceder"
        override val granted = "Ativo"
        override val notGranted = "Ação necessária"
        override val finishSetup = "Concluir configuração"
        override val pleaseGrantRequiredPermissions = "Por favor, conceda as permissões necessárias antes de continuar."
        override val allPermissionsRequired = "O acesso a notificações é obrigatório para continuar."
        override val statusProtectionActive = "Proteção ativa"
        override val actionRunDiagnostics = "Diagnóstico"
        override val actionManageApps = "Gerenciar aplicativos"
        override val actionSettings = "Configurações"
        override val settingsTitle = "Configurações"
        override val themeBlack = "Preto"
        override val themeWhite = "Branco"
        override val themeGoldenBlack = "Dourado e Preto"
        override val themeGoldenWhite = "Dourado e Branco"
        override val monitoringActive = "Monitoramento ativo"
        override val monitoringUnavailable = "Monitoramento indisponível"
        override val changesSaved = "Alterações salvas"
        override val close = "Fechar"
    }

    object ArabicStrings : Strings by EnglishStrings {
        override val appSubtitle = "الحماية الذكية للإشعارات"
        override val selectLanguage = "اختر اللغة"
        override val continueButton = "متابعة"
        override val nextButton = "التالي"
        override val backButton = "رجوع"
        override val setupTitle = "إعداد الأذونات"
        override val permissionNotificationListener = "الوصول إلى الإشعارات (مطلوب)"
        override val grantAccess = "منح الإذن"
        override val granted = "نشط"
        override val notGranted = "مطلوب إجراء"
        override val finishSetup = "إكمال الإعداد"
        override val pleaseGrantRequiredPermissions = "يرجى منح الأذونات المطلوبة قبل المتابعة."
        override val allPermissionsRequired = "الوصول إلى الإشعارات مطلوب للمتابعة."
        override val requiredPermissions = "الأذونات المطلوبة"
        override val permissionDenied = "تم رفض الإذن"
        override val missingNotificationAccess = "مفقود: الوصول إلى الإشعارات"
        override val statusProtectionActive = "الحماية نشطة"
        override val statusAccessRequired = "مطلوب إذن الوصول"
        override val actionRunDiagnostics = "تشخيص النظام"
        override val actionManageApps = "إدارة التطبيقات"
        override val actionSettings = "الإعدادات"
        override val settingsTitle = "الإعدادات"
        override val themeBlack = "أسود"
        override val themeWhite = "أبيض"
        override val themeGoldenBlack = "ذهبي وأسود"
        override val themeGoldenWhite = "ذهبي وأبيض"
        override val monitoringActive = "المراقبة نشطة"
        override val monitoringUnavailable = "المراقبة غير متاحة"
        override val cancel = "إلغاء"
        override val confirm = "تأكيد"
        override val save = "حفظ"
        override val changesSaved = "تم حفظ التغييرات"
        override val close = "إغلاق"
        override val startProtection = "بدء الحماية"
        override val protectInBackground = "الحماية في الخلفية"
        override val stopProtection = "إيقاف الحماية"
        override val protectionStartedNotice = "NexGuard نشط الآن ويحمي الإشعارات في الخلفية."
    }

    object UrduStrings : Strings by EnglishStrings {
        override val appSubtitle = "سمارٹ نوٹیفکیشن پروٹیکشن"
        override val selectLanguage = "زبان منتخب کریں"
        override val continueButton = "جاری رکھیں"
        override val nextButton = "اگلا"
        override val backButton = "پیچھے"
        override val setupTitle = "اجازتیں سیٹ اپ"
        override val permissionNotificationListener = "نوٹیفکیشن تک رسائی (ضروری)"
        override val grantAccess = "اجازت دیں"
        override val granted = "فعال"
        override val notGranted = "توجہ طلب"
        override val finishSetup = "مکمل کریں"
        override val pleaseGrantRequiredPermissions = "براہ کرم آگے بڑھنے سے پہلے مطلوبہ اجازتیں دیں۔"
        override val allPermissionsRequired = "جاری رکھنے کے لیے نوٹیفکیشن تک رسائی درکار ہے۔"
        override val requiredPermissions = "ضروری اجازتیں"
        override val permissionDenied = "اجازت مسترد کر دی گئی"
        override val missingNotificationAccess = "غائب: نوٹیفکیشن تک رسائی"
        override val statusProtectionActive = "حفاظت فعال ہے"
        override val actionRunDiagnostics = "تشخیص چلائیں"
        override val actionManageApps = "ایپس کا انتظام"
        override val actionSettings = "ترتیبات"
        override val settingsTitle = "ترتیبات"
        override val themeBlack = "سیاہ"
        override val themeWhite = "سفید"
        override val themeGoldenBlack = "سنہری اور سیاہ"
        override val themeGoldenWhite = "سنہری اور سفید"
        override val monitoringActive = "نگرانی فعال ہے"
        override val monitoringUnavailable = "نگرانی دستیاب نہیں ہے"
        override val cancel = "منسوخ"
        override val confirm = "تصدیق"
        override val save = "محفوظ کریں"
        override val changesSaved = "تبدیلیاں محفوظ ہو گئیں"
        override val close = "بند کریں"
        override val startProtection = "حفاظت شروع کریں"
        override val protectInBackground = "پس منظر میں حفاظت جاری رکھیں"
        override val stopProtection = "حفاظت روکیں"
        override val protectionStartedNotice = "NexGuard اب پس منظر میں نوٹیفکیشنز کی حفاظت کر رہا ہے۔"
    }

    object HindiStrings : Strings by EnglishStrings {
        override val appSubtitle = "स्मार्ट नोटिफिकेशन सुरक्षा"
        override val selectLanguage = "भाषा चुनें"
        override val continueButton = "आगे बढ़ें"
        override val nextButton = "आगे"
        override val backButton = "पीछे"
        override val setupTitle = "अनुमति सेटअप"
        override val permissionNotificationListener = "नोटिफिकेशन एक्सेस (आवश्यक)"
        override val grantAccess = "अनुमति दें"
        override val granted = "सक्रिय"
        override val notGranted = "कार्रवाई आवश्यक"
        override val finishSetup = "सेटअप पूर्ण करें"
        override val pleaseGrantRequiredPermissions = "कृपया आगे बढ़ने से पहले आवश्यक अनुमतियां दें।"
        override val allPermissionsRequired = "आगे बढ़ने के लिए नोटिफिकेशन एक्सेस आवश्यक है।"
        override val statusProtectionActive = "सुरक्षा सक्रिय है"
        override val actionRunDiagnostics = "डायग्नोस्टिक्स"
        override val actionManageApps = "ऐप्स प्रबंधित करें"
        override val actionSettings = "सेटिंग्स"
        override val settingsTitle = "सेटिंग्स"
        override val themeBlack = "काला"
        override val themeWhite = "सफेद"
        override val themeGoldenBlack = "सुनहरा और काला"
        override val themeGoldenWhite = "सुनहरा और सफेद"
        override val monitoringActive = "निगरानी सक्रिय है"
        override val monitoringUnavailable = "निगरानी अनुपलब्ध है"
        override val cancel = "रद्द करें"
        override val confirm = "पुष्टि करें"
        override val save = "सहेजें"
        override val changesSaved = "परिवर्तन सहेजे गए"
        override val close = "बंद करें"
        override val startProtection = "सुरक्षा शुरू करें"
        override val protectInBackground = "बैकग्राउंड में सुरक्षा जारी रखें"
        override val stopProtection = "सुरक्षा रोकें"
        override val protectionStartedNotice = "NexGuard अब बैकग्राउंड में नोटिफिकेशन की सुरक्षा कर रहा है।"
    }

    object FrenchStrings : Strings by EnglishStrings {
        override val appSubtitle = "Protection intelligente des notifications"
        override val selectLanguage = "Choisir la langue"
        override val continueButton = "Continuer"
        override val nextButton = "Suivant"
        override val backButton = "Retour"
        override val setupTitle = "Configuration des autorisations"
        override val permissionNotificationListener = "Accès aux notifications (Requis)"
        override val grantAccess = "Autoriser"
        override val granted = "Actif"
        override val notGranted = "Action requise"
        override val finishSetup = "Terminer la configuration"
        override val pleaseGrantRequiredPermissions = "Veuillez accorder les autorisations requises avant de continuer."
        override val allPermissionsRequired = "L'accès aux notifications est requis pour continuer."
        override val statusProtectionActive = "Protection active"
        override val actionRunDiagnostics = "Diagnostics"
        override val actionManageApps = "Gérer les applications"
        override val actionSettings = "Paramètres"
        override val settingsTitle = "Paramètres"
        override val themeBlack = "Noir"
        override val themeWhite = "Blanc"
        override val themeGoldenBlack = "Or et Noir"
        override val themeGoldenWhite = "Or et Blanc"
        override val monitoringActive = "Surveillance active"
        override val monitoringUnavailable = "Surveillance indisponible"
        override val cancel = "Annuler"
        override val confirm = "Confirmer"
        override val save = "Enregistrer"
        override val changesSaved = "Modifications enregistrées"
        override val close = "Fermer"
    }

    object GermanStrings : Strings by EnglishStrings {
        override val appSubtitle = "Intelligenter Benachrichtigungsschutz"
        override val selectLanguage = "Sprache wählen"
        override val continueButton = "Weiter"
        override val nextButton = "Weiter"
        override val backButton = "Zurück"
        override val setupTitle = "Berechtigungs-Einrichtung"
        override val permissionNotificationListener = "Benachrichtigungszugriff (Erforderlich)"
        override val grantAccess = "Erlauben"
        override val granted = "Aktiv"
        override val notGranted = "Handlung erforderlich"
        override val finishSetup = "Einrichtung abschließen"
        override val pleaseGrantRequiredPermissions = "Bitte erteilen Sie die erforderlichen Berechtigungen, bevor Sie fortfahren."
        override val allPermissionsRequired = "Der Benachrichtigungszugriff ist erforderlich, um fortzufahren."
        override val statusProtectionActive = "Schutz aktiv"
        override val actionRunDiagnostics = "Diagnose"
        override val actionManageApps = "Apps verwalten"
        override val actionSettings = "Einstellungen"
        override val settingsTitle = "Einstellungen"
        override val themeBlack = "Schwarz"
        override val themeWhite = "Weiß"
        override val themeGoldenBlack = "Gold & Schwarz"
        override val themeGoldenWhite = "Gold & Weiß"
        override val monitoringActive = "Überwachung aktiv"
        override val monitoringUnavailable = "Überwachung nicht verfügbar"
        override val cancel = "Abbrechen"
        override val confirm = "Bestätigen"
        override val save = "Speichern"
        override val changesSaved = "Änderungen gespeichert"
        override val close = "Schließen"
    }

    object JapaneseStrings : Strings by EnglishStrings {
        override val appSubtitle = "スマート通知保護"
        override val selectLanguage = "言語を選択"
        override val continueButton = "次へ"
        override val nextButton = "次へ"
        override val backButton = "戻る"
        override val setupTitle = "権限の設定"
        override val permissionNotificationListener = "通知へのアクセス (必須)"
        override val grantAccess = "許可する"
        override val granted = "有効"
        override val notGranted = "対応が必要"
        override val finishSetup = "設定を完了"
        override val pleaseGrantRequiredPermissions = "続行する前に必要な権限を許可してください。"
        override val allPermissionsRequired = "続行するには通知アクセスが必要です。"
        override val statusProtectionActive = "保護が有効です"
        override val actionRunDiagnostics = "診断を実行"
        override val actionManageApps = "アプリ管理"
        override val actionSettings = "設定"
        override val settingsTitle = "設定"
        override val themeBlack = "ブラック"
        override val themeWhite = "ホワイト"
        override val themeGoldenBlack = "ゴールド＆ブラック"
        override val themeGoldenWhite = "ゴールド＆ホワイト"
        override val monitoringActive = "監視中"
        override val monitoringUnavailable = "監視不可"
        override val cancel = "キャンセル"
        override val confirm = "確認"
        override val save = "保存"
        override val changesSaved = "変更が保存されました"
        override val close = "閉じる"
    }

    object KoreanStrings : Strings by EnglishStrings {
        override val appSubtitle = "스마트 알림 보호"
        override val selectLanguage = "언어 선택"
        override val continueButton = "계속"
        override val nextButton = "다음"
        override val backButton = "뒤로"
        override val setupTitle = "권한 설정"
        override val permissionNotificationListener = "알림 접근 권한 (필수)"
        override val grantAccess = "권한 허용"
        override val granted = "활성화됨"
        override val notGranted = "조치 필요"
        override val finishSetup = "설정 완료"
        override val pleaseGrantRequiredPermissions = "계속하기 전에 필요한 권한을 허용해 주세요."
        override val allPermissionsRequired = "계속하려면 알림 접근 권한이 필요합니다."
        override val statusProtectionActive = "보호 활성화됨"
        override val actionRunDiagnostics = "진단 실행"
        override val actionManageApps = "앱 관리"
        override val actionSettings = "설정"
        override val settingsTitle = "설정"
        override val themeBlack = "블랙"
        override val themeWhite = "화이트"
        override val themeGoldenBlack = "골드 & 블랙"
        override val themeGoldenWhite = "골드 & 화이트"
        override val monitoringActive = "모니터링 활성"
        override val monitoringUnavailable = "모니터링 불가"
        override val cancel = "취소"
        override val confirm = "확인"
        override val save = "저장"
        override val changesSaved = "변경사항 저장됨"
        override val close = "닫기"
    }

    object TurkishStrings : Strings by EnglishStrings {
        override val appSubtitle = "Akıllı Bildirim Koruması"
        override val selectLanguage = "Dil Seçin"
        override val continueButton = "Devam Et"
        override val nextButton = "İleri"
        override val backButton = "Geri"
        override val permissionNotificationListener = "Bildirim Erişimi (Zorunlu)"
        override val pleaseGrantRequiredPermissions = "Lütfen devam etmeden önce gerekli izinleri verin."
        override val allPermissionsRequired = "Devam etmek için bildirim erişimi gereklidir."
        override val granted = "Aktif"
        override val notGranted = "Eylem Gerekli"
        override val statusProtectionActive = "Koruma Aktif"
        override val actionRunDiagnostics = "Tanılama"
        override val actionManageApps = "Uygulamaları Yönet"
        override val settingsTitle = "Ayarlar"
        override val themeBlack = "Siyah"
        override val themeWhite = "Beyaz"
        override val themeGoldenBlack = "Altın ve Siyah"
        override val themeGoldenWhite = "Altın ve Beyaz"
        override val changesSaved = "Değişiklikler kaydedildi"
    }

    object IndonesianStrings : Strings by EnglishStrings {
        override val appSubtitle = "Perlindungan Notifikasi Pintar"
        override val selectLanguage = "Pilih Bahasa"
        override val continueButton = "Lanjutkan"
        override val nextButton = "Berikutnya"
        override val backButton = "Kembali"
        override val permissionNotificationListener = "Akses Notifikasi (Wajib)"
        override val pleaseGrantRequiredPermissions = "Harap berikan izin yang diperlukan sebelum melanjutkan."
        override val allPermissionsRequired = "Akses notifikasi diperlukan untuk melanjutkan."
        override val granted = "Aktif"
        override val notGranted = "Perlu Tindakan"
        override val statusProtectionActive = "Perlindungan Aktif"
        override val actionRunDiagnostics = "Jalankan Diagnostik"
        override val actionManageApps = "Kelola Aplikasi"
        override val settingsTitle = "Pengaturan"
        override val themeBlack = "Hitam"
        override val themeWhite = "Putih"
        override val themeGoldenBlack = "Emas & Hitam"
        override val themeGoldenWhite = "Emas & Putih"
        override val changesSaved = "Perubahan disimpan"
    }

    object ItalianStrings : Strings by EnglishStrings {
        override val appSubtitle = "Protezione intelligente delle notifiche"
        override val selectLanguage = "Scegli lingua"
        override val continueButton = "Continua"
        override val nextButton = "Avanti"
        override val backButton = "Indietro"
        override val permissionNotificationListener = "Accesso alle notifiche (Obbligatorio)"
        override val pleaseGrantRequiredPermissions = "Si prega di concedere i permessi richiesti prima di continuare."
        override val allPermissionsRequired = "L'accesso alle notifiche è obbligatorio per continuare."
        override val granted = "Attivo"
        override val notGranted = "Azione richiesta"
        override val statusProtectionActive = "Protezione attiva"
        override val actionRunDiagnostics = "Diagnostica"
        override val actionManageApps = "Gestisci app"
        override val settingsTitle = "Impostazioni"
        override val themeBlack = "Nero"
        override val themeWhite = "Bianco"
        override val themeGoldenBlack = "Oro e Nero"
        override val themeGoldenWhite = "Oro e Bianco"
        override val changesSaved = "Modifiche salvate"
    }

    object PolishStrings : Strings by EnglishStrings {
        override val appSubtitle = "Inteligentna ochrona powiadomień"
        override val selectLanguage = "Wybierz język"
        override val continueButton = "Kontynuuj"
        override val nextButton = "Dalej"
        override val backButton = "Wstecz"
        override val permissionNotificationListener = "Dostęp do powiadomień (Wymagany)"
        override val pleaseGrantRequiredPermissions = "Przed kontynuowaniem należy przyznać wymagane uprawnienia."
        override val allPermissionsRequired = "Dostęp do powiadomień jest wymagany, aby kontynuować."
        override val granted = "Aktywny"
        override val notGranted = "Wymagane działanie"
        override val statusProtectionActive = "Ochrona aktywna"
        override val actionRunDiagnostics = "Diagnostyka"
        override val settingsTitle = "Ustawienia"
        override val themeBlack = "Czarny"
        override val themeWhite = "Biały"
        override val themeGoldenBlack = "Złoty i Czarny"
        override val themeGoldenWhite = "Złoty i Biały"
        override val changesSaved = "Zmiany zostały zapisane"
    }

    object UkrainianStrings : Strings by EnglishStrings {
        override val appSubtitle = "Розумний захист сповіщень"
        override val selectLanguage = "Оберіть мову"
        override val continueButton = "Продовжити"
        override val nextButton = "Далі"
        override val backButton = "Назад"
        override val permissionNotificationListener = "Доступ до сповіщень (Обов\'язково)"
        override val pleaseGrantRequiredPermissions = "Будь ласка, надайте необхідні дозволи перед продовженням."
        override val allPermissionsRequired = "Для продовження потрібен доступ до сповіщень."
        override val granted = "Активно"
        override val notGranted = "Потрібна дія"
        override val statusProtectionActive = "Захист активний"
        override val actionRunDiagnostics = "Діагностика"
        override val settingsTitle = "Налаштування"
        override val themeBlack = "Чорна"
        override val themeWhite = "Біла"
        override val themeGoldenBlack = "Золото та Чорний"
        override val themeGoldenWhite = "Золото та Білий"
        override val changesSaved = "Зміни збережено"
    }

    object DutchStrings : Strings by EnglishStrings {
        override val appSubtitle = "Slimme meldingenbescherming"
        override val selectLanguage = "Kies taal"
        override val continueButton = "Doorgaan"
        override val nextButton = "Volgende"
        override val backButton = "Terug"
        override val permissionNotificationListener = "Toegang tot meldingen (Vereist)"
        override val pleaseGrantRequiredPermissions = "Verleen de vereiste machtigingen voordat u doorgaat."
        override val allPermissionsRequired = "Toegang tot meldingen is vereist om door te gaan."
        override val granted = "Actief"
        override val notGranted = "Actie vereist"
        override val statusProtectionActive = "Bescherming actief"
        override val actionRunDiagnostics = "Diagnose"
        override val settingsTitle = "Instellingen"
        override val themeBlack = "Zwart"
        override val themeWhite = "Wit"
        override val themeGoldenBlack = "Goud & Zwart"
        override val themeGoldenWhite = "Goud & Wit"
        override val changesSaved = "Wijzigingen opgeslagen"
    }

    object VietnameseStrings : Strings by EnglishStrings {
        override val appSubtitle = "Bảo vệ thông báo thông minh"
        override val selectLanguage = "Chọn ngôn ngữ"
        override val continueButton = "Tiếp tục"
        override val nextButton = "Tiếp theo"
        override val backButton = "Quay lại"
        override val permissionNotificationListener = "Quyền truy cập thông báo (Bắt buộc)"
        override val pleaseGrantRequiredPermissions = "Vui lòng cấp quyền bắt buộc trước khi tiếp tục."
        override val allPermissionsRequired = "Cần có quyền truy cập thông báo để tiếp tục."
        override val granted = "Đang hoạt động"
        override val notGranted = "Cần hành động"
        override val statusProtectionActive = "Bảo vệ đang bật"
        override val actionRunDiagnostics = "Chẩn đoán"
        override val settingsTitle = "Cài đặt"
        override val themeBlack = "Đen"
        override val themeWhite = "Trắng"
        override val themeGoldenBlack = "Vàng kim & Đen"
        override val themeGoldenWhite = "Vàng kim & Trắng"
        override val changesSaved = "Đã lưu thay đổi"
    }

    object ThaiStrings : Strings by EnglishStrings {
        override val appSubtitle = "การปกป้องการแจ้งเตือนอัจฉริยะ"
        override val selectLanguage = "เลือกภาษา"
        override val continueButton = "ดำเนินการต่อ"
        override val nextButton = "ถัดไป"
        override val backButton = "ย้อนกลับ"
        override val permissionNotificationListener = "การเข้าถึงการแจ้งเตือน (จำเป็น)"
        override val pleaseGrantRequiredPermissions = "โปรดอนุญาตสิทธิ์ที่จำเป็นก่อนดำเนินการต่อ"
        override val allPermissionsRequired = "ต้องได้รับสิทธิ์การเข้าถึงการแจ้งเตือนเพื่อดำเนินการต่อ"
        override val granted = "เปิดใช้งาน"
        override val notGranted = "ต้องดำเนินการ"
        override val statusProtectionActive = "การป้องกันทำงานอยู่"
        override val actionRunDiagnostics = "วินิจฉัยระบบ"
        override val settingsTitle = "การตั้งค่า"
        override val themeBlack = "ดำ"
        override val themeWhite = "ขาว"
        override val themeGoldenBlack = "ทองและดำ"
        override val themeGoldenWhite = "ทองและขาว"
        override val changesSaved = "บันทึกการเปลี่ยนแปลงแล้ว"
    }

    object BengaliStrings : Strings by EnglishStrings {
        override val appSubtitle = "স্মার্ট বিজ্ঞপ্তি সুরক্ষা"
        override val selectLanguage = "ভাষা নির্বাচন করুন"
        override val continueButton = "চালিয়ে যান"
        override val nextButton = "পরবর্তী"
        override val backButton = "ফিরে যান"
        override val permissionNotificationListener = "বিজ্ঞপ্তি অ্যাক্সেস (প্রয়োজনীয়)"
        override val pleaseGrantRequiredPermissions = "চালিয়ে যাওয়ার আগে অনুগ্রহ করে প্রয়োজনীয় অনুমতি প্রদান করুন।"
        override val allPermissionsRequired = "চালিয়ে যাওয়ার জন্য বিজ্ঞপ্তি অ্যাক্সেস প্রয়োজন।"
        override val granted = "সক্রিয়"
        override val notGranted = "পদক্ষেপ প্রয়োজন"
        override val statusProtectionActive = "সুরক্ষা সক্রিয়"
        override val actionRunDiagnostics = "ডায়াগনস্টিকস"
        override val settingsTitle = "সেটিংস"
        override val themeBlack = "কালো"
        override val themeWhite = "সাদা"
        override val themeGoldenBlack = "সোনালী ও কালো"
        override val themeGoldenWhite = "সোনালী ও সাদা"
        override val changesSaved = "পরিবর্তন সংরক্ষিত হয়েছে"
    }

    object PunjabiStrings : Strings by EnglishStrings {
        override val appSubtitle = "ਸਮਾਰਟ ਸੂਚਨਾ ਸੁਰੱਖਿਆ"
        override val selectLanguage = "ਭਾਸ਼ਾ ਚੁਣੋ"
        override val continueButton = "ਜਾਰੀ ਰੱਖੋ"
        override val nextButton = "ਅੱਗੇ"
        override val backButton = "ਪਿੱਛੇ"
        override val permissionNotificationListener = "ਸੂਚਨਾ ਪਹੁੰਚ (ਲੋੜੀਂਦੀ)"
        override val pleaseGrantRequiredPermissions = "ਕਿਰਪਾ ਕਰਕੇ ਜਾਰੀ ਰੱਖਣ ਤੋਂ ਪਹਿਲਾਂ ਲੋੜੀਂਦੀਆਂ ਇਜਾਜ਼ਤਾਂ ਦਿਓ।"
        override val allPermissionsRequired = "ਜਾਰੀ ਰੱਖਣ ਲਈ ਸੂਚਨਾ ਪਹੁੰਚ ਦੀ ਲੋੜ ਹੈ।"
        override val granted = "ਸਰਗਰਮ"
        override val notGranted = "ਕਾਰਵਾਈ ਦੀ ਲੋੜ"
        override val statusProtectionActive = "ਸੁਰੱਖਿਆ ਸਰਗਰਮ ਹੈ"
        override val settingsTitle = "ਸੈਟਿੰਗਾਂ"
        override val themeBlack = "ਕਾਲਾ"
        override val themeWhite = "ਚਿੱਟਾ"
        override val themeGoldenBlack = "ਸੁਨਹਿਰੀ ਅਤੇ ਕਾਲਾ"
        override val themeGoldenWhite = "ਸੁਨਹਿਰੀ ਅਤੇ ਚਿੱਟਾ"
        override val changesSaved = "ਤਬਦੀਲੀਆਂ ਸੁਰੱਖਿਅਤ ਕੀਤੀਆਂ ਗਈਆਂ"
    }

    object PersianStrings : Strings by EnglishStrings {
        override val appSubtitle = "محافظت هوشمند از اعلان‌ها"
        override val selectLanguage = "انتخاب زبان"
        override val continueButton = "ادامه"
        override val nextButton = "بعدی"
        override val backButton = "بازگشت"
        override val permissionNotificationListener = "دسترسی به اعلان‌ها (الزامی)"
        override val pleaseGrantRequiredPermissions = "لطفاً قبل از ادامه، دسترسی‌های لازم را اعطا کنید."
        override val allPermissionsRequired = "برای ادامه، دسترسی به اعلان‌ها الزامی است."
        override val granted = "فعال"
        override val notGranted = "اقدام لازم است"
        override val statusProtectionActive = "محافظت فعال است"
        override val settingsTitle = "تنظیمات"
        override val themeBlack = "مشکی"
        override val themeWhite = "سفید"
        override val themeGoldenBlack = "طلایی و مشکی"
        override val themeGoldenWhite = "طلایی و سفید"
        override val changesSaved = "تغییرات ذخیره شد"
    }

    object SwahiliStrings : Strings by EnglishStrings {
        override val appSubtitle = "Ulinzi Bora wa Arifa"
        override val selectLanguage = "Chagua Lugha"
        override val continueButton = "Endelea"
        override val nextButton = "Ifuatayo"
        override val backButton = "Nyuma"
        override val permissionNotificationListener = "Ufikiaji wa Arifa (Inahitajika)"
        override val pleaseGrantRequiredPermissions = "Tafadhali ruhusu vibali vinavyohitajika kabla ya kuendelea."
        override val allPermissionsRequired = "Ufikiaji wa arifa unahitajika ili kuendelea."
        override val granted = "Imewashwa"
        override val notGranted = "Hatua Inahitajika"
        override val statusProtectionActive = "Ulinzi Uwashwa"
        override val settingsTitle = "Mipangilio"
        override val themeBlack = "Nyeusi"
        override val themeWhite = "Nyeupe"
        override val themeGoldenBlack = "Dhahabu na Nyeusi"
        override val themeGoldenWhite = "Dhahabu na Nyeupe"
        override val changesSaved = "Mabadiliko yamehifadhiwa"
    }
}
