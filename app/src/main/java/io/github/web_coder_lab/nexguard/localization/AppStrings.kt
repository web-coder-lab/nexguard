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
    object UrduStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "اطلاع کی صحت کی نگرانی"
        override val selectLanguage = "زبان منتخب کریں"
        override val selectLanguageDesc = "اپنی پسندیدہ زبان منتخب کریں۔ اسے بعد میں بدل سکتے ہیں۔"
        override val searchLanguage = "زبانیں تلاش کریں..."
        override val continueButton = "جاری رکھیں"
        override val nextButton = "اگلا"
        override val backButton = "واپس"
        override val setupTitle = "اجازتوں کی ترتیب"
        override val setupSubtitle = "اطلاعات قابلِ اعتماد رکھنے کے لیے سسٹم رسائی سیٹ کریں"
        override val permissionNotificationListener = "اطلاع کی رسائی (لازمی)"
        override val permissionNotificationListenerDesc = "NEXGUARD کو آپ کی ایپس کی اطلاع کے واقعات مقامی طور پر دیکھنے دیتا ہے تاکہ ترسیل کی پابندیاں معلوم ہوں۔"
        override val permissionGuardianNotifications = "NEXGUARD الرٹس"
        override val permissionGuardianNotificationsDesc = "پس منظر کی ترسیل رکے تو مختصر تشخیصی نوٹس ملیں۔"
        override val permissionBatteryOptimization = "پس منظر کی قابلِ اعتمادی"
        override val permissionBatteryOptimizationDesc = "NEXGUARD کو بیٹری کی سخت پابندیوں سے مستثنیٰ رکھیں تاکہ یہ جڑا رہے۔"
        override val permissionPrivacyNotice = "نجی اور ڈیوائس پر"
        override val permissionPrivacyNoticeDesc = "صرف میٹا ڈیٹا۔ NEXGUARD پیغام کا متن محفوظ یا بھیجتا نہیں۔"
        override val grantAccess = "اجازت دیں"
        override val granted = "فعال"
        override val notGranted = "کارروائی درکار"
        override val finishSetup = "ترتیب مکمل کریں"
        override val pleaseGrantRequiredPermissions = "جاری رکھنے سے پہلے لازمی اجازتیں دیں۔"
        override val allPermissionsRequired = "جاری رکھنے کے لیے اطلاع کی رسائی لازمی ہے۔"
        override val requiredPermissions = "لازمی اجازتیں"
        override val permissionDenied = "اجازت مسترد"
        override val missingNotificationAccess = "غائب: اطلاع کی رسائی"
        override val statusProtectionActive = "نگرانی فعال"
        override val statusProtectionActiveDesc = "پس منظر میں اطلاع کی ترسیل کی نگرانی ہو رہی ہے"
        override val statusAccessRequired = "رسائی درکار"
        override val statusAccessRequiredDesc = "نگرانی شروع کرنے کے لیے اطلاع کی رسائی دیں"
        override val statusBatteryRestricted = "بیٹری محدود"
        override val statusBatteryRestrictedDesc = "سسٹم کی بیٹری بہتری اطلاعات میں تاخیر کر سکتی ہے"
        override val statusMonitoringPaused = "نگرانی رکی ہوئی"
        override val statusMonitoringPausedDesc = "اطلاع کی نگرانی صارف نے روکی ہے"
        override val rowNotificationAccess = "اطلاع کی رسائی"
        override val rowMonitoringState = "نگرانی کی حالت"
        override val rowBatteryOptimization = "بیٹری پالیسی"
        override val rowMonitoredApps = "نگرانی میں ایپس"
        override val rowLastDiagnostic = "سسٹم کی صحت"
        override val actionEnableProtection = "نگرانی فعال کریں"
        override val actionRunDiagnostics = "تشخیص چلائیں"
        override val actionManageApps = "ایپس منظم کریں"
        override val actionSettings = "ترتیبات"
        override val actionFixPermissions = "اجازتیں ٹھیک کریں"
        override val settingsTitle = "ترتیبات"
        override val sectionAppearance = "ظاہری شکل"
        override val themeBlack = "سیاہ"
        override val themeWhite = "سفید"
        override val themeGoldenBlack = "سنہری اور سیاہ"
        override val themeGoldenWhite = "سنہری اور سفید"
        override val reducedMotion = "کم حرکت"
        override val reducedMotionDesc = "غیر ضروری حرکت اور تبدیلیاں بند کریں"
        override val hapticFeedback = "لمسی تاثر"
        override val hapticFeedbackDesc = "بٹن دبانے پر وائبریشن"
        override val sectionLanguage = "زبان"
        override val currentLanguage = "ایپ کی زبان"
        override val sectionMonitoring = "اطلاع کی نگرانی"
        override val monitoringActive = "نگرانی فعال"
        override val monitoringActiveDesc = "ترسیل کی حالت کے لیے مقامی سننے والا فعال ہے"
        override val monitoringUnavailable = "نگرانی دستیاب نہیں"
        override val historyPrivacy = "تاریخ کی رازداری"
        override val historyPrivacyMetadata = "صرف میٹا ڈیٹا (تجویز کردہ)"
        override val historyPrivacyOptIn = "میٹا ڈیٹا + عنوانات"
        override val diagnosticAlerts = "الرٹ کی تعداد"
        override val alertsSilent = "خاموش نگرانی"
        override val alertsCriticalOnly = "صرف اہم الرٹس"
        override val alertsAll = "تمام تشخیصی الرٹس"
        override val sectionAppManagement = "انسٹال شدہ ایپس"
        override val sectionAppManagementDesc = "ہر ایپ کی نگرانی کے اصول سیٹ کریں"
        override val sectionBattery = "بیٹری اور پس منظر"
        override val sectionBatteryDesc = "ڈیوائس کی بیٹری پابندیاں اور رہنمائی دیکھیں"
        override val openBatterySettings = "بیٹری کی ترتیبات کھولیں"
        override val sectionDiagnostics = "تشخیص"
        override val runDiagnosticsNow = "مکمل جانچ چلائیں"
        override val sectionPrivacyStorage = "رازداری اور مقامی ڈیٹا"
        override val eventRetention = "واقعہ کی تاریخ کی مدت"
        override val retention1Day = "1 دن"
        override val retention7Days = "7 دن"
        override val retention30Days = "30 دن"
        override val clearHistory = "واقعہ لاگ صاف کریں"
        override val clearHistoryConfirm = "کیا اطلاع کے واقعات کی تاریخ صاف کرنی ہے؟"
        override val resetSettings = "ترجیحات ری سیٹ کریں"
        override val resetSettingsConfirm = "NEXGUARD کی تمام ترجیحات طے شدہ قدروں پر واپس کریں؟"
        override val cancel = "منسوخ"
        override val confirm = "تصدیق"
        override val save = "محفوظ کریں"
        override val changesSaved = "تبدیلیاں محفوظ ہو گئیں"
        override val changesNotSaved = "تبدیلیاں محفوظ نہیں ہو سکیں"
        override val appManagerTitle = "ایپس منظم کریں"
        override val searchApps = "ایپس تلاش کریں..."
        override val filterAll = "سب"
        override val filterUser = "صارف"
        override val filterSystem = "سسٹم"
        override val monitoredStatus = "نگرانی میں"
        override val unmonitoredStatus = "خارج"
        override val openAppNotificationSettings = "ایپ کی اطلاع کی ترتیبات"
        override val openAppDetailsSettings = "سسٹم ایپ کی معلومات"
        override val appTroubleshootingAdvice = "بہتر اطلاع کی قابلِ اعتمادی کے لیے پس منظر ڈیٹا اور بیٹری استعمال بے پابند رکھیں۔"
        override val noAppsFound = "کوئی ایپ اس فلٹر سے نہیں ملی"
        override val diagnosticReportTitle = "تشخیصی نتائج"
        override val allChecksPassed = "سب نظام معمول پر ہیں"
        override val issuesDetected = "قابلِ اعتمادی متاثر کرنے والے مسائل ملے"
        override val manufacturerGuidance = "ڈیوائس بنانے والے کی ہدایت"
        override val protectionDetailsTitle = "نگرانی کی حالت"
        override val close = "بند کریں"
        override val startProtection = "نگرانی شروع کریں"
        override val protectInBackground = "نگرانی شروع کریں"
        override val stopProtection = "نگرانی روکیں"
        override val protectionStartedNotice = "NEXGUARD اب فعال ہے اور پس منظر میں اطلاعات کی نگرانی کر رہا ہے۔"
        override val aboutTitle = "تعارف"
        override val aboutVersion = "ورژن"
        override val aboutGitHub = "سورس کوڈ"
        override val aboutLicense = "لائسنس"
        override val aboutOpenSource = "اوپن سورس لائبریریاں: AndroidX، Jetpack Compose، Room، DataStore، WorkManager، Kotlin Coroutines۔"
    }
    object ArabicStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "مراقبة صحة الإشعارات"
        override val selectLanguage = "اختيار اللغة"
        override val selectLanguageDesc = "اختر لغتك المفضلة. يمكنك تغييرها لاحقًا."
        override val searchLanguage = "البحث في اللغات..."
        override val continueButton = "متابعة"
        override val nextButton = "التالي"
        override val backButton = "رجوع"
        override val setupTitle = "إعداد الأذونات"
        override val setupSubtitle = "اضبط وصول النظام لإبقاء الإشعارات موثوقة"
        override val permissionNotificationListener = "الوصول إلى الإشعارات (مطلوب)"
        override val permissionNotificationListenerDesc = "يسمح لـ NEXGUARD بمراقبة أحداث الإشعارات محليًا لاكتشاف قيود التسليم."
        override val permissionGuardianNotifications = "تنبيهات NEXGUARD"
        override val permissionGuardianNotificationsDesc = "استلم ملاحظات تشخيصية مختصرة إذا توقف التسليم في الخلفية."
        override val permissionBatteryOptimization = "موثوقية الخلفية"
        override val permissionBatteryOptimizationDesc = "استثنِ NEXGUARD من قيود البطارية القوية ليبقى متصلًا."
        override val permissionPrivacyNotice = "خاص وعلى الجهاز"
        override val permissionPrivacyNoticeDesc = "بيانات وصفية فقط. لا يحفظ NEXGUARD نص الرسالة ولا يرسله."
        override val grantAccess = "منح"
        override val granted = "نشط"
        override val notGranted = "يلزم إجراء"
        override val finishSetup = "إكمال الإعداد"
        override val pleaseGrantRequiredPermissions = "امنح الأذونات المطلوبة قبل المتابعة."
        override val allPermissionsRequired = "الوصول إلى الإشعارات مطلوب للمتابعة."
        override val requiredPermissions = "الأذونات المطلوبة"
        override val permissionDenied = "تم رفض الإذن"
        override val missingNotificationAccess = "ناقص: الوصول إلى الإشعارات"
        override val statusProtectionActive = "المراقبة نشطة"
        override val statusProtectionActiveDesc = "تجري مراقبة تسليم الإشعارات في الخلفية"
        override val statusAccessRequired = "يلزم الوصول"
        override val statusAccessRequiredDesc = "امنح الوصول إلى الإشعارات لبدء المراقبة"
        override val statusBatteryRestricted = "البطارية مقيّدة"
        override val statusBatteryRestrictedDesc = "قد يؤخر تحسين البطارية الإشعارات"
        override val statusMonitoringPaused = "المراقبة متوقفة"
        override val statusMonitoringPausedDesc = "أوقف المستخدم مراقبة الإشعارات"
        override val rowNotificationAccess = "الوصول إلى الإشعارات"
        override val rowMonitoringState = "حالة المراقبة"
        override val rowBatteryOptimization = "سياسة البطارية"
        override val rowMonitoredApps = "التطبيقات المراقبة"
        override val rowLastDiagnostic = "صحة النظام"
        override val actionEnableProtection = "تفعيل المراقبة"
        override val actionRunDiagnostics = "تشغيل التشخيص"
        override val actionManageApps = "إدارة التطبيقات"
        override val actionSettings = "الإعدادات"
        override val actionFixPermissions = "إصلاح الأذونات"
        override val settingsTitle = "الإعدادات"
        override val sectionAppearance = "المظهر"
        override val themeBlack = "أسود"
        override val themeWhite = "أبيض"
        override val themeGoldenBlack = "ذهبي وأسود"
        override val themeGoldenWhite = "ذهبي وأبيض"
        override val reducedMotion = "حركة أقل"
        override val reducedMotionDesc = "إيقاف الحركة والانتقالات غير الضرورية"
        override val hapticFeedback = "استجابة لمسية"
        override val hapticFeedbackDesc = "اهتزاز عند الضغط على الأزرار"
        override val sectionLanguage = "اللغة"
        override val currentLanguage = "لغة التطبيق"
        override val sectionMonitoring = "مراقبة الإشعارات"
        override val monitoringActive = "المراقبة نشطة"
        override val monitoringActiveDesc = "المستمع المحلي لحالة التسليم نشط"
        override val monitoringUnavailable = "المراقبة غير متاحة"
        override val historyPrivacy = "خصوصية السجل"
        override val historyPrivacyMetadata = "بيانات وصفية فقط (موصى به)"
        override val historyPrivacyOptIn = "بيانات وصفية + العناوين"
        override val diagnosticAlerts = "تكرار التنبيه"
        override val alertsSilent = "مراقبة صامتة"
        override val alertsCriticalOnly = "التنبيهات المهمة فقط"
        override val alertsAll = "كل التنبيهات التشخيصية"
        override val sectionAppManagement = "التطبيقات المثبّتة"
        override val sectionAppManagementDesc = "اضبط قواعد المراقبة لكل تطبيق"
        override val sectionBattery = "البطارية والخلفية"
        override val sectionBatteryDesc = "راجع قيود البطارية وإرشادات الشركة المصنّعة"
        override val openBatterySettings = "فتح إعدادات البطارية"
        override val sectionDiagnostics = "التشخيص"
        override val runDiagnosticsNow = "تشغيل فحص كامل"
        override val sectionPrivacyStorage = "الخصوصية والبيانات المحلية"
        override val eventRetention = "مدة الاحتفاظ بالسجل"
        override val retention1Day = "يوم واحد"
        override val retention7Days = "7 أيام"
        override val retention30Days = "30 يومًا"
        override val clearHistory = "مسح سجل الأحداث"
        override val clearHistoryConfirm = "هل تريد مسح سجل أحداث الإشعارات؟"
        override val resetSettings = "إعادة ضبط التفضيلات"
        override val resetSettingsConfirm = "إعادة كل تفضيلات NEXGUARD إلى القيم الافتراضية؟"
        override val cancel = "إلغاء"
        override val confirm = "تأكيد"
        override val save = "حفظ"
        override val changesSaved = "تم حفظ التغييرات"
        override val changesNotSaved = "تعذر حفظ التغييرات"
        override val appManagerTitle = "إدارة التطبيقات"
        override val searchApps = "البحث في التطبيقات..."
        override val filterAll = "الكل"
        override val filterUser = "المستخدم"
        override val filterSystem = "النظام"
        override val monitoredStatus = "تحت المراقبة"
        override val unmonitoredStatus = "مستبعد"
        override val openAppNotificationSettings = "إعدادات إشعارات التطبيق"
        override val openAppDetailsSettings = "معلومات تطبيق النظام"
        override val appTroubleshootingAdvice = "اترك بيانات الخلفية واستخدام البطارية دون قيود لموثوقية أفضل."
        override val noAppsFound = "لا توجد تطبيقات مطابقة"
        override val diagnosticReportTitle = "نتائج التشخيص"
        override val allChecksPassed = "كل الأنظمة تعمل بشكل طبيعي"
        override val issuesDetected = "تم اكتشاف مشكلات تؤثر على الموثوقية"
        override val manufacturerGuidance = "إرشاد الشركة المصنّعة"
        override val protectionDetailsTitle = "حالة المراقبة"
        override val close = "إغلاق"
        override val startProtection = "بدء المراقبة"
        override val protectInBackground = "بدء المراقبة"
        override val stopProtection = "إيقاف المراقبة"
        override val protectionStartedNotice = "NEXGUARD نشط الآن ويراقب الإشعارات في الخلفية."
        override val aboutTitle = "حول"
        override val aboutVersion = "الإصدار"
        override val aboutGitHub = "الشفرة المصدرية"
        override val aboutLicense = "الترخيص"
        override val aboutOpenSource = "مكتبات مفتوحة المصدر: AndroidX وJetpack Compose وRoom وDataStore وWorkManager وKotlin Coroutines."
    }
    object PersianStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "پایش سلامت اعلان‌ها"
        override val selectLanguage = "انتخاب زبان"
        override val selectLanguageDesc = "زبان دلخواه را انتخاب کنید. بعداً می‌توانید تغییرش دهید."
        override val searchLanguage = "جستجوی زبان‌ها..."
        override val continueButton = "ادامه"
        override val nextButton = "بعدی"
        override val backButton = "بازگشت"
        override val setupTitle = "تنظیم مجوزها"
        override val setupSubtitle = "دسترسی سیستم را برای قابل‌اعتماد ماندن اعلان‌ها تنظیم کنید"
        override val permissionNotificationListener = "دسترسی به اعلان‌ها (الزامی)"
        override val permissionNotificationListenerDesc = "به NEXGUARD اجازه می‌دهد رویدادهای اعلان را به‌صورت محلی ببیند تا محدودیت تحویل مشخص شود."
        override val permissionGuardianNotifications = "هشدارهای NEXGUARD"
        override val permissionGuardianNotificationsDesc = "اگر تحویل در پس‌زمینه متوقف شود، یادداشت تشخیصی کوتاه دریافت کنید."
        override val permissionBatteryOptimization = "پایداری پس‌زمینه"
        override val permissionBatteryOptimizationDesc = "NEXGUARD را از محدودیت شدید باتری مستثنا کنید تا متصل بماند."
        override val permissionPrivacyNotice = "خصوصی و روی دستگاه"
        override val permissionPrivacyNoticeDesc = "فقط فراداده. NEXGUARD متن پیام را ذخیره یا ارسال نمی‌کند."
        override val grantAccess = "اجازه"
        override val granted = "فعال"
        override val notGranted = "نیاز به اقدام"
        override val finishSetup = "پایان تنظیم"
        override val pleaseGrantRequiredPermissions = "پیش از ادامه، مجوزهای لازم را بدهید."
        override val allPermissionsRequired = "برای ادامه، دسترسی به اعلان‌ها لازم است."
        override val requiredPermissions = "مجوزهای لازم"
        override val permissionDenied = "مجوز رد شد"
        override val missingNotificationAccess = "موجود نیست: دسترسی به اعلان‌ها"
        override val statusProtectionActive = "پایش فعال"
        override val statusProtectionActiveDesc = "تحویل اعلان در پس‌زمینه پایش می‌شود"
        override val statusAccessRequired = "دسترسی لازم است"
        override val statusAccessRequiredDesc = "برای شروع پایش، دسترسی به اعلان‌ها را بدهید"
        override val statusBatteryRestricted = "باتری محدود"
        override val statusBatteryRestrictedDesc = "بهینه‌سازی باتری ممکن است اعلان‌ها را به تأخیر بیندازد"
        override val statusMonitoringPaused = "پایش متوقف"
        override val statusMonitoringPausedDesc = "پایش اعلان توسط کاربر متوقف شده است"
        override val rowNotificationAccess = "دسترسی به اعلان‌ها"
        override val rowMonitoringState = "وضعیت پایش"
        override val rowBatteryOptimization = "سیاست باتری"
        override val rowMonitoredApps = "برنامه‌های تحت پایش"
        override val rowLastDiagnostic = "سلامت سیستم"
        override val actionEnableProtection = "فعال‌سازی پایش"
        override val actionRunDiagnostics = "اجرای تشخیص"
        override val actionManageApps = "مدیریت برنامه‌ها"
        override val actionSettings = "تنظیمات"
        override val actionFixPermissions = "رفع مجوزها"
        override val settingsTitle = "تنظیمات"
        override val sectionAppearance = "ظاهر"
        override val themeBlack = "سیاه"
        override val themeWhite = "سفید"
        override val themeGoldenBlack = "طلایی و سیاه"
        override val themeGoldenWhite = "طلایی و سفید"
        override val reducedMotion = "حرکت کمتر"
        override val reducedMotionDesc = "حرکت و گذارهای غیرضروری را خاموش کنید"
        override val hapticFeedback = "بازخورد لمسی"
        override val hapticFeedbackDesc = "لرزش هنگام لمس دکمه‌ها"
        override val sectionLanguage = "زبان"
        override val currentLanguage = "زبان برنامه"
        override val sectionMonitoring = "پایش اعلان"
        override val monitoringActive = "پایش فعال"
        override val monitoringActiveDesc = "شنونده محلی وضعیت تحویل فعال است"
        override val monitoringUnavailable = "پایش در دسترس نیست"
        override val historyPrivacy = "حریم خصوصی تاریخچه"
        override val historyPrivacyMetadata = "فقط فراداده (پیشنهادی)"
        override val historyPrivacyOptIn = "فراداده + عنوان‌ها"
        override val diagnosticAlerts = "بسامد هشدار"
        override val alertsSilent = "پایش بی‌صدا"
        override val alertsCriticalOnly = "فقط هشدارهای مهم"
        override val alertsAll = "همه هشدارهای تشخیصی"
        override val sectionAppManagement = "برنامه‌های نصب‌شده"
        override val sectionAppManagementDesc = "قاعده پایش هر برنامه را تنظیم کنید"
        override val sectionBattery = "باتری و پس‌زمینه"
        override val sectionBatteryDesc = "محدودیت باتری و راهنمای سازنده را ببینید"
        override val openBatterySettings = "باز کردن تنظیمات باتری"
        override val sectionDiagnostics = "تشخیص"
        override val runDiagnosticsNow = "اجرای بررسی کامل"
        override val sectionPrivacyStorage = "حریم خصوصی و داده محلی"
        override val eventRetention = "مدت نگهداری تاریخچه"
        override val retention1Day = "۱ روز"
        override val retention7Days = "۷ روز"
        override val retention30Days = "۳۰ روز"
        override val clearHistory = "پاک کردن گزارش رویداد"
        override val clearHistoryConfirm = "تاریخچه رویدادهای اعلان پاک شود؟"
        override val resetSettings = "بازنشانی ترجیحات"
        override val resetSettingsConfirm = "همه ترجیحات NEXGUARD به مقدار پیش‌فرض برگردد؟"
        override val cancel = "لغو"
        override val confirm = "تأیید"
        override val save = "ذخیره"
        override val changesSaved = "تغییرها ذخیره شد"
        override val changesNotSaved = "تغییرها ذخیره نشد"
        override val appManagerTitle = "مدیریت برنامه‌ها"
        override val searchApps = "جستجوی برنامه‌ها..."
        override val filterAll = "همه"
        override val filterUser = "کاربر"
        override val filterSystem = "سیستم"
        override val monitoredStatus = "تحت پایش"
        override val unmonitoredStatus = "مستثنا"
        override val openAppNotificationSettings = "تنظیمات اعلان برنامه"
        override val openAppDetailsSettings = "اطلاعات برنامه سیستم"
        override val appTroubleshootingAdvice = "برای اطمینان بیشتر، داده پس‌زمینه و مصرف باتری را بدون محدودیت بگذارید."
        override val noAppsFound = "برنامه‌ای با این فیلتر پیدا نشد"
        override val diagnosticReportTitle = "نتایج تشخیص"
        override val allChecksPassed = "همه بخش‌ها عادی کار می‌کنند"
        override val issuesDetected = "مشکلاتی که اطمینان را کاهش می‌دهند پیدا شد"
        override val manufacturerGuidance = "راهنمای سازنده دستگاه"
        override val protectionDetailsTitle = "وضعیت پایش"
        override val close = "بستن"
        override val startProtection = "شروع پایش"
        override val protectInBackground = "شروع پایش"
        override val stopProtection = "توقف پایش"
        override val protectionStartedNotice = "NEXGUARD اکنون فعال است و اعلان‌ها را در پس‌زمینه پایش می‌کند."
        override val aboutTitle = "درباره"
        override val aboutVersion = "نسخه"
        override val aboutGitHub = "کد منبع"
        override val aboutLicense = "مجوز"
        override val aboutOpenSource = "کتابخانه‌های متن‌باز: AndroidX، Jetpack Compose، Room، DataStore، WorkManager، Kotlin Coroutines."
    }
    object HindiStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "सूचना स्वास्थ्य मॉनिटर"
        override val selectLanguage = "भाषा चुनें"
        override val selectLanguageDesc = "अपनी पसंदीदा भाषा चुनें। इसे बाद में बदल सकते हैं।"
        override val searchLanguage = "भाषाएँ खोजें..."
        override val continueButton = "जारी रखें"
        override val nextButton = "आगे"
        override val backButton = "वापस"
        override val setupTitle = "अनुमति सेटअप"
        override val setupSubtitle = "सूचनाएँ विश्वसनीय रखने के लिए सिस्टम पहुँच सेट करें"
        override val permissionNotificationListener = "सूचना पहुँच (आवश्यक)"
        override val permissionNotificationListenerDesc = "NEXGUARD को ऐप्स की सूचना घटनाएँ स्थानीय रूप से देखने देता है ताकि डिलीवरी सीमाएँ पता चलें।"
        override val permissionGuardianNotifications = "NEXGUARD अलर्ट"
        override val permissionGuardianNotificationsDesc = "पृष्ठभूमि डिलीवरी रुकने पर संक्षिप्त निदान सूचनाएँ पाएँ।"
        override val permissionBatteryOptimization = "पृष्ठभूमि विश्वसनीयता"
        override val permissionBatteryOptimizationDesc = "NEXGUARD को सख्त बैटरी नियमों से छूट दें ताकि यह जुड़ा रहे।"
        override val permissionPrivacyNotice = "निजी और डिवाइस पर"
        override val permissionPrivacyNoticeDesc = "केवल मेटाडेटा। NEXGUARD संदेश पाठ सहेजता या भेजता नहीं।"
        override val grantAccess = "अनुमति दें"
        override val granted = "सक्रिय"
        override val notGranted = "कार्रवाई चाहिए"
        override val finishSetup = "सेटअप पूरा करें"
        override val pleaseGrantRequiredPermissions = "जारी रखने से पहले आवश्यक अनुमतियाँ दें।"
        override val allPermissionsRequired = "जारी रखने के लिए सूचना पहुँच आवश्यक है।"
        override val requiredPermissions = "आवश्यक अनुमतियाँ"
        override val permissionDenied = "अनुमति अस्वीकृत"
        override val missingNotificationAccess = "गायब: सूचना पहुँच"
        override val statusProtectionActive = "निगरानी सक्रिय"
        override val statusProtectionActiveDesc = "पृष्ठभूमि में सूचना डिलीवरी की निगरानी हो रही है"
        override val statusAccessRequired = "पहुँच आवश्यक"
        override val statusAccessRequiredDesc = "निगरानी शुरू करने के लिए सूचना पहुँच दें"
        override val statusBatteryRestricted = "बैटरी प्रतिबंधित"
        override val statusBatteryRestrictedDesc = "बैटरी ऑप्टिमाइज़ेशन सूचनाओं में देरी कर सकता है"
        override val statusMonitoringPaused = "निगरानी रुकी हुई"
        override val statusMonitoringPausedDesc = "उपयोगकर्ता ने सूचना निगरानी रोकी है"
        override val rowNotificationAccess = "सूचना पहुँच"
        override val rowMonitoringState = "निगरानी स्थिति"
        override val rowBatteryOptimization = "बैटरी नीति"
        override val rowMonitoredApps = "निगरानी वाले ऐप्स"
        override val rowLastDiagnostic = "सिस्टम स्वास्थ्य"
        override val actionEnableProtection = "निगरानी चालू करें"
        override val actionRunDiagnostics = "निदान चलाएँ"
        override val actionManageApps = "ऐप्स प्रबंधित करें"
        override val actionSettings = "सेटिंग्स"
        override val actionFixPermissions = "अनुमतियाँ ठीक करें"
        override val settingsTitle = "सेटिंग्स"
        override val sectionAppearance = "दिखावट"
        override val themeBlack = "काला"
        override val themeWhite = "सफ़ेद"
        override val themeGoldenBlack = "सुनहरा और काला"
        override val themeGoldenWhite = "सुनहरा और सफ़ेद"
        override val reducedMotion = "कम गति"
        override val reducedMotionDesc = "अनावश्यक गति और बदलाव बंद करें"
        override val hapticFeedback = "हैप्टिक फ़ीडबैक"
        override val hapticFeedbackDesc = "बटन दबाने पर कंपन"
        override val sectionLanguage = "भाषा"
        override val currentLanguage = "ऐप भाषा"
        override val sectionMonitoring = "सूचना निगरानी"
        override val monitoringActive = "निगरानी सक्रिय"
        override val monitoringActiveDesc = "डिलीवरी स्थिति के लिए स्थानीय श्रोता सक्रिय है"
        override val monitoringUnavailable = "निगरानी उपलब्ध नहीं"
        override val historyPrivacy = "इतिहास गोपनीयता"
        override val historyPrivacyMetadata = "केवल मेटाडेटा (सुझाया गया)"
        override val historyPrivacyOptIn = "मेटाडेटा + शीर्षक"
        override val diagnosticAlerts = "अलर्ट आवृत्ति"
        override val alertsSilent = "शांत निगरानी"
        override val alertsCriticalOnly = "केवल महत्वपूर्ण अलर्ट"
        override val alertsAll = "सभी निदान अलर्ट"
        override val sectionAppManagement = "इंस्टॉल किए ऐप्स"
        override val sectionAppManagementDesc = "हर ऐप के निगरानी नियम सेट करें"
        override val sectionBattery = "बैटरी और पृष्ठभूमि"
        override val sectionBatteryDesc = "डिवाइस की बैटरी सीमाएँ और OEM मार्गदर्शन देखें"
        override val openBatterySettings = "बैटरी सेटिंग्स खोलें"
        override val sectionDiagnostics = "निदान"
        override val runDiagnosticsNow = "पूर्ण जाँच चलाएँ"
        override val sectionPrivacyStorage = "गोपनीयता और स्थानीय डेटा"
        override val eventRetention = "घटना इतिहास अवधि"
        override val retention1Day = "1 दिन"
        override val retention7Days = "7 दिन"
        override val retention30Days = "30 दिन"
        override val clearHistory = "घटना लॉग साफ़ करें"
        override val clearHistoryConfirm = "क्या सूचना घटना इतिहास साफ़ करना है?"
        override val resetSettings = "प्राथमिकताएँ रीसेट करें"
        override val resetSettingsConfirm = "सभी NEXGUARD प्राथमिकताएँ डिफ़ॉल्ट पर लौटाएँ?"
        override val cancel = "रद्द करें"
        override val confirm = "पुष्टि करें"
        override val save = "सहेजें"
        override val changesSaved = "बदलाव सहेजे गए"
        override val changesNotSaved = "बदलाव सहेजे नहीं जा सके"
        override val appManagerTitle = "ऐप्स प्रबंधित करें"
        override val searchApps = "ऐप्स खोजें..."
        override val filterAll = "सभी"
        override val filterUser = "उपयोगकर्ता"
        override val filterSystem = "सिस्टम"
        override val monitoredStatus = "निगरानी में"
        override val unmonitoredStatus = "बाहर"
        override val openAppNotificationSettings = "ऐप सूचना सेटिंग्स"
        override val openAppDetailsSettings = "सिस्टम ऐप जानकारी"
        override val appTroubleshootingAdvice = "बेहतर विश्वसनीयता के लिए पृष्ठभूमि डेटा और बैटरी उपयोग अप्रतिबंधित रखें।"
        override val noAppsFound = "इस फ़िल्टर से कोई ऐप नहीं मिला"
        override val diagnosticReportTitle = "निदान परिणाम"
        override val allChecksPassed = "सभी सिस्टम सामान्य हैं"
        override val issuesDetected = "विश्वसनीयता प्रभावित करने वाली समस्याएँ मिलीं"
        override val manufacturerGuidance = "डिवाइस निर्माता सलाह"
        override val protectionDetailsTitle = "निगरानी स्थिति"
        override val close = "बंद करें"
        override val startProtection = "निगरानी शुरू करें"
        override val protectInBackground = "निगरानी शुरू करें"
        override val stopProtection = "निगरानी रोकें"
        override val protectionStartedNotice = "NEXGUARD अब सक्रिय है और पृष्ठभूमि में सूचनाओं की निगरानी कर रहा है।"
        override val aboutTitle = "परिचय"
        override val aboutVersion = "संस्करण"
        override val aboutGitHub = "सोर्स कोड"
        override val aboutLicense = "लाइसेंस"
        override val aboutOpenSource = "ओपन-सोर्स लाइब्रेरी: AndroidX, Jetpack Compose, Room, DataStore, WorkManager, Kotlin Coroutines."
    }
    object ChineseSimpStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "通知健康监测"
        override val selectLanguage = "选择语言"
        override val selectLanguageDesc = "选择首选语言。可随时更改。"
        override val searchLanguage = "搜索语言..."
        override val continueButton = "继续"
        override val nextButton = "下一步"
        override val backButton = "返回"
        override val setupTitle = "权限设置"
        override val setupSubtitle = "设置系统权限，让通知更可靠"
        override val permissionNotificationListener = "通知使用权（必需）"
        override val permissionNotificationListenerDesc = "允许 NEXGUARD 在本地监测应用通知事件，以发现投递限制。"
        override val permissionGuardianNotifications = "NEXGUARD 提醒"
        override val permissionGuardianNotificationsDesc = "后台投递受阻时收到简短诊断通知。"
        override val permissionBatteryOptimization = "后台可靠性"
        override val permissionBatteryOptimizationDesc = "将 NEXGUARD 排除在严格电池限制之外，以便保持连接。"
        override val permissionPrivacyNotice = "本地且私密"
        override val permissionPrivacyNoticeDesc = "仅元数据。NEXGUARD 不保存也不发送消息正文。"
        override val grantAccess = "允许"
        override val granted = "已启用"
        override val notGranted = "需要操作"
        override val finishSetup = "完成设置"
        override val pleaseGrantRequiredPermissions = "继续前请授予必需权限。"
        override val allPermissionsRequired = "继续需要通知使用权。"
        override val requiredPermissions = "必需权限"
        override val permissionDenied = "权限被拒绝"
        override val missingNotificationAccess = "缺少：通知使用权"
        override val statusProtectionActive = "监测中"
        override val statusProtectionActiveDesc = "正在后台监测通知投递"
        override val statusAccessRequired = "需要权限"
        override val statusAccessRequiredDesc = "授予通知使用权以开始监测"
        override val statusBatteryRestricted = "电池受限"
        override val statusBatteryRestrictedDesc = "电池优化可能会延迟通知"
        override val statusMonitoringPaused = "监测已暂停"
        override val statusMonitoringPausedDesc = "用户已暂停通知监测"
        override val rowNotificationAccess = "通知使用权"
        override val rowMonitoringState = "监测状态"
        override val rowBatteryOptimization = "电池策略"
        override val rowMonitoredApps = "监测中的应用"
        override val rowLastDiagnostic = "系统健康"
        override val actionEnableProtection = "开启监测"
        override val actionRunDiagnostics = "运行诊断"
        override val actionManageApps = "管理应用"
        override val actionSettings = "设置"
        override val actionFixPermissions = "修复权限"
        override val settingsTitle = "设置"
        override val sectionAppearance = "外观"
        override val themeBlack = "黑色"
        override val themeWhite = "白色"
        override val themeGoldenBlack = "金色与黑色"
        override val themeGoldenWhite = "金色与白色"
        override val reducedMotion = "减少动画"
        override val reducedMotionDesc = "关闭非必要动画和过渡"
        override val hapticFeedback = "触感反馈"
        override val hapticFeedbackDesc = "点击按钮时振动"
        override val sectionLanguage = "语言"
        override val currentLanguage = "应用语言"
        override val sectionMonitoring = "通知监测"
        override val monitoringActive = "监测中"
        override val monitoringActiveDesc = "本地监听器正在检查投递状态"
        override val monitoringUnavailable = "无法监测"
        override val historyPrivacy = "历史隐私"
        override val historyPrivacyMetadata = "仅元数据（推荐）"
        override val historyPrivacyOptIn = "元数据 + 标题"
        override val diagnosticAlerts = "提醒频率"
        override val alertsSilent = "静默监测"
        override val alertsCriticalOnly = "仅重要提醒"
        override val alertsAll = "全部诊断提醒"
        override val sectionAppManagement = "已安装应用"
        override val sectionAppManagementDesc = "设置各应用的监测规则"
        override val sectionBattery = "电池与后台"
        override val sectionBatteryDesc = "查看设备电池限制和厂商指引"
        override val openBatterySettings = "打开电池设置"
        override val sectionDiagnostics = "诊断"
        override val runDiagnosticsNow = "运行完整检查"
        override val sectionPrivacyStorage = "隐私与本地数据"
        override val eventRetention = "事件历史保留"
        override val retention1Day = "1 天"
        override val retention7Days = "7 天"
        override val retention30Days = "30 天"
        override val clearHistory = "清除事件记录"
        override val clearHistoryConfirm = "确定清除通知事件历史吗？"
        override val resetSettings = "重置偏好"
        override val resetSettingsConfirm = "将所有 NEXGUARD 偏好恢复为默认值？"
        override val cancel = "取消"
        override val confirm = "确认"
        override val save = "保存"
        override val changesSaved = "更改已保存"
        override val changesNotSaved = "无法保存更改"
        override val appManagerTitle = "管理应用"
        override val searchApps = "搜索应用..."
        override val filterAll = "全部"
        override val filterUser = "用户"
        override val filterSystem = "系统"
        override val monitoredStatus = "监测中"
        override val unmonitoredStatus = "已排除"
        override val openAppNotificationSettings = "应用通知设置"
        override val openAppDetailsSettings = "系统应用信息"
        override val appTroubleshootingAdvice = "为提高可靠性，请允许不受限制的后台数据和电池使用。"
        override val noAppsFound = "没有符合筛选条件的应用"
        override val diagnosticReportTitle = "诊断结果"
        override val allChecksPassed = "各项检查正常"
        override val issuesDetected = "发现影响可靠性的问题"
        override val manufacturerGuidance = "设备厂商建议"
        override val protectionDetailsTitle = "监测状态"
        override val close = "关闭"
        override val startProtection = "开始监测"
        override val protectInBackground = "开始监测"
        override val stopProtection = "停止监测"
        override val protectionStartedNotice = "NEXGUARD 已启用，正在后台监测通知。"
        override val aboutTitle = "关于"
        override val aboutVersion = "版本"
        override val aboutGitHub = "源代码"
        override val aboutLicense = "许可"
        override val aboutOpenSource = "开源库：AndroidX、Jetpack Compose、Room、DataStore、WorkManager、Kotlin Coroutines。"
    }
    object ChineseTradStrings : Strings {
        override val appName = "NEXGUARD"
        override val appSubtitle = "通知健康監測"
        override val selectLanguage = "選擇語言"
        override val selectLanguageDesc = "選擇偏好語言。之後可隨時變更。"
        override val searchLanguage = "搜尋語言..."
        override val continueButton = "繼續"
        override val nextButton = "下一步"
        override val backButton = "返回"
        override val setupTitle = "權限設定"
        override val setupSubtitle = "設定系統權限，讓通知更可靠"
        override val permissionNotificationListener = "通知存取權（必要）"
        override val permissionNotificationListenerDesc = "允許 NEXGUARD 在本機監測應用程式通知事件，以發現傳送限制。"
        override val permissionGuardianNotifications = "NEXGUARD 提醒"
        override val permissionGuardianNotificationsDesc = "背景傳送受阻時收到簡短診斷通知。"
        override val permissionBatteryOptimization = "背景可靠性"
        override val permissionBatteryOptimizationDesc = "將 NEXGUARD 排除在嚴格電池限制之外，以便維持連線。"
        override val permissionPrivacyNotice = "本機且私密"
        override val permissionPrivacyNoticeDesc = "僅中繼資料。NEXGUARD 不儲存也不傳送訊息內容。"
        override val grantAccess = "允許"
        override val granted = "已啟用"
        override val notGranted = "需要操作"
        override val finishSetup = "完成設定"
        override val pleaseGrantRequiredPermissions = "繼續前請授予必要權限。"
        override val allPermissionsRequired = "繼續需要通知存取權。"
        override val requiredPermissions = "必要權限"
        override val permissionDenied = "權限遭拒"
        override val missingNotificationAccess = "缺少：通知存取權"
        override val statusProtectionActive = "監測中"
        override val statusProtectionActiveDesc = "正在背景監測通知傳送"
        override val statusAccessRequired = "需要權限"
        override val statusAccessRequiredDesc = "授予通知存取權以開始監測"
        override val statusBatteryRestricted = "電池受限"
        override val statusBatteryRestrictedDesc = "電池最佳化可能會延遲通知"
        override val statusMonitoringPaused = "監測已暫停"
        override val statusMonitoringPausedDesc = "使用者已暫停通知監測"
        override val rowNotificationAccess = "通知存取權"
        override val rowMonitoringState = "監測狀態"
        override val rowBatteryOptimization = "電池原則"
        override val rowMonitoredApps = "監測中的應用程式"
        override val rowLastDiagnostic = "系統健康狀態"
        override val actionEnableProtection = "開啟監測"
        override val actionRunDiagnostics = "執行診斷"
        override val actionManageApps = "管理應用程式"
        override val actionSettings = "設定"
        override val actionFixPermissions = "修正權限"
        override val settingsTitle = "設定"
        override val sectionAppearance = "外觀"
        override val themeBlack = "黑色"
        override val themeWhite = "白色"
        override val themeGoldenBlack = "金色與黑色"
        override val themeGoldenWhite = "金色與白色"
        override val reducedMotion = "減少動畫"
        override val reducedMotionDesc = "關閉非必要動畫與轉場"
        override val hapticFeedback = "觸覺回饋"
        override val hapticFeedbackDesc = "點按按鈕時震動"
        override val sectionLanguage = "語言"
        override val currentLanguage = "應用程式語言"
        override val sectionMonitoring = "通知監測"
        override val monitoringActive = "監測中"
        override val monitoringActiveDesc = "本機監聽器正在檢查傳送狀態"
        override val monitoringUnavailable = "無法監測"
        override val historyPrivacy = "歷程隱私"
        override val historyPrivacyMetadata = "僅中繼資料（建議）"
        override val historyPrivacyOptIn = "中繼資料 + 標題"
        override val diagnosticAlerts = "提醒頻率"
        override val alertsSilent = "靜音監測"
        override val alertsCriticalOnly = "僅重要提醒"
        override val alertsAll = "全部診斷提醒"
        override val sectionAppManagement = "已安裝應用程式"
        override val sectionAppManagementDesc = "設定各應用程式的監測規則"
        override val sectionBattery = "電池與背景"
        override val sectionBatteryDesc = "查看裝置電池限制與廠商指引"
        override val openBatterySettings = "開啟電池設定"
        override val sectionDiagnostics = "診斷"
        override val runDiagnosticsNow = "執行完整檢查"
        override val sectionPrivacyStorage = "隱私與本機資料"
        override val eventRetention = "事件歷程保留"
        override val retention1Day = "1 天"
        override val retention7Days = "7 天"
        override val retention30Days = "30 天"
        override val clearHistory = "清除事件記錄"
        override val clearHistoryConfirm = "確定要清除通知事件歷程嗎？"
        override val resetSettings = "重設偏好"
        override val resetSettingsConfirm = "將所有 NEXGUARD 偏好恢復為預設值？"
        override val cancel = "取消"
        override val confirm = "確認"
        override val save = "儲存"
        override val changesSaved = "變更已儲存"
        override val changesNotSaved = "無法儲存變更"
        override val appManagerTitle = "管理應用程式"
        override val searchApps = "搜尋應用程式..."
        override val filterAll = "全部"
        override val filterUser = "使用者"
        override val filterSystem = "系統"
        override val monitoredStatus = "監測中"
        override val unmonitoredStatus = "已排除"
        override val openAppNotificationSettings = "應用程式通知設定"
        override val openAppDetailsSettings = "系統應用程式資訊"
        override val appTroubleshootingAdvice = "為提高可靠性，請允許不受限制的背景資料與電池使用。"
        override val noAppsFound = "沒有符合篩選條件的應用程式"
        override val diagnosticReportTitle = "診斷結果"
        override val allChecksPassed = "各項檢查正常"
        override val issuesDetected = "發現影響可靠性的問題"
        override val manufacturerGuidance = "裝置廠商建議"
        override val protectionDetailsTitle = "監測狀態"
        override val close = "關閉"
        override val startProtection = "開始監測"
        override val protectInBackground = "開始監測"
        override val stopProtection = "停止監測"
        override val protectionStartedNotice = "NEXGUARD 已啟用，正在背景監測通知。"
        override val aboutTitle = "關於"
        override val aboutVersion = "版本"
        override val aboutGitHub = "原始碼"
        override val aboutLicense = "授權"
        override val aboutOpenSource = "開源程式庫：AndroidX、Jetpack Compose、Room、DataStore、WorkManager、Kotlin Coroutines。"
    }
    object RussianStrings : Strings by EnglishStrings {
    }

    object SpanishStrings : Strings by EnglishStrings {
    }

    object FrenchStrings : Strings by EnglishStrings {
    }

    object GermanStrings : Strings by EnglishStrings {
    }

    object PortugueseStrings : Strings by EnglishStrings {
    }

    object JapaneseStrings : Strings by EnglishStrings {
    }

    object KoreanStrings : Strings by EnglishStrings {
    }

    object TurkishStrings : Strings by EnglishStrings {
    }

    object IndonesianStrings : Strings by EnglishStrings {
    }

    object ItalianStrings : Strings by EnglishStrings {
    }

    object DutchStrings : Strings by EnglishStrings {
    }

    object PolishStrings : Strings by EnglishStrings {
    }

    object UkrainianStrings : Strings by EnglishStrings {
    }

    object VietnameseStrings : Strings by EnglishStrings {
    }

    object ThaiStrings : Strings by EnglishStrings {
    }

    object BengaliStrings : Strings by EnglishStrings {
    }

    object PunjabiStrings : Strings by EnglishStrings {
    }

    object SwahiliStrings : Strings by EnglishStrings {
    }

}
