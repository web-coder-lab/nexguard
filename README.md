# NEXGUARD

**Notification health monitor for Android.** NEXGUARD records the notification events Android delivers to your phone, checks the background settings that can block apps, and guides you to the exact fix for your phone brand. Everything stays on your device.

> Status: **v1.0.2, F-Droid readiness candidate.** Free and open source. Tested on a real phone (see [Tested on](#tested-on)).

## Why I built this

On my own phone, every restart killed the apps running in the background. After a reboot I stopped receiving important calls, messages and emails, and I did not know why. Many people with aggressive battery managers have the same problem. NEXGUARD helps you see what is happening and fix the settings you can control.

— Syed Muhammad Hussain Shah, Pakistan

## What it does

- Records when a notification is posted or removed, for the apps you choose (app, time, event type).
- Keeps a local history with a retention period you set (for example 7 days) and a "Clear event log" button.
- Two privacy modes: **metadata only** (recommended) or metadata plus titles. The full message text is not stored.
- Checks notification access and battery-optimization state, and shows plain statuses.
- Phone-specific guidance for OPPO/Realme, Samsung, Xiaomi, Vivo, Huawei, OnePlus, Motorola and Pixel.
- Schedules background checks again after your phone restarts (Android WorkManager).
- More than 20 interface languages, with right-to-left layouts. Not every translation has been reviewed by a native speaker.
- Dark, light and black themes, reduced-motion and haptic switches.

## What it does not do

NEXGUARD is honest about its limits:

- It **cannot** stop Android or a phone maker from killing other apps.
- It **cannot** restart apps that were force-stopped.
- It **cannot** recover a notification that never reached your phone.
- It only sees notifications Android delivers to it. Where Android does not say, NEXGUARD shows *Unknown*.

## Screenshots

The six app screenshots are not in the repository yet. They will be added under `docs/screenshots/` when provided. No placeholder images are used.

## Download

| Source | Status |
|---|---|
| GitHub Releases | [Latest release](https://github.com/web-coder-lab/nexguard/releases/latest) |
| Website | Coming soon |
| F-Droid | Coming soon |

### Install

1. Download `NEXGUARD-1.0.1.apk` from the latest release.
2. Check the file (see below).
3. Open it. If Android asks, allow installs from your browser or file manager.
4. Open NEXGUARD and follow the setup: grant notification access, allow its own alerts, choose apps.

### Verify the download

SHA-256 of `NEXGUARD-1.0.1.apk`:

```
977e57bb6fe8395353afc055b9d2c28090de5c3e7cc284e1661b7b2a0e33d424
```

Signing certificate SHA-256 fingerprint:

```
ea104132b89fcb66aa04e1f8d7390712523c5fae544a8da7e781b085557db17c
```

Check on a computer:

```bash
sha256sum NEXGUARD-1.0.1.apk
apksigner verify --print-certs NEXGUARD-1.0.1.apk
```

## Permissions

| Permission | Why |
|---|---|
| Notification access | Required to receive notification events. |
| Post notifications | Lets NEXGUARD show its own diagnostic alerts. |
| Request ignore battery optimizations | Lets you ask Android to exempt NEXGUARD. |
| Receive boot completed | Schedules diagnostics again after a restart. |
| Query all packages | Lists installed apps so you can choose which to monitor. |
| Vibrate, wake lock, network state | Haptics and the background-work library. |

NEXGUARD does **not** request the internet permission. Verify this against the manifest of the release you install.

## Privacy

- History is stored only on your device.
- No accounts, no analytics, no ads, no cloud upload.
- You choose the retention period and can clear history at any time.

## Tested on

| Phone | Software | Result |
|---|---|---|
| OPPO CPH1909, 4 GB RAM | ColorOS 5.2.1, Android 8.1.0 | v1.0.0 tested by the developer |

Other phones and Android versions are untested. Reports are welcome in [Issues](https://github.com/web-coder-lab/nexguard/issues).

## Coming soon

No dates are confirmed. These are plans, not promises.

- A real self-test (post a test notification and confirm it was received and saved)
- A restart checklist that reminds you to open your important apps
- An "important apps" list with a quiet-app alert
- A restart log
- Exportable diagnostic report

## Build from source

Requirements: Android Studio (current stable) or JDK 17 and the Android SDK.

```bash
git clone https://github.com/web-coder-lab/nexguard.git
cd nexguard
./gradlew assembleDebug
```

Release builds are signed in CI. See `.github/workflows/release.yml`. Never commit a keystore or passwords.

## Contributing

Bug reports and pull requests are welcome. Please open an issue first for larger changes. By contributing you agree that your contribution is released under the project's license.

## License

NEXGUARD is released under the **GNU General Public License v3.0 only** (`GPL-3.0-only`). See [LICENSE](LICENSE).

In short: you may use, study and modify it. If you distribute a modified version, you must keep the copyright notices, release your source under the same license, and state your changes.

The **NEXGUARD name, logo and icon are not licensed for reuse.** A fork must use a different name and icon and must not present itself as the official app.

Copyright (C) 2026 Syed Muhammad Hussain Shah.

## Author

NEXGUARD is created and maintained by **Syed Muhammad Hussain Shah**, from Pakistan. I built it after my own phone kept killing background apps on every restart, and I test every release on my own device. I plan to complete the app over the next few updates.

## Contact

- Email: dickvoice734@gmail.com
- WeChat:

  ![WeChat QR](docs/wechat-qr.png)

- Issues: [GitHub Issues](https://github.com/web-coder-lab/nexguard/issues)

## Credits

Built with Kotlin, Jetpack Compose, Room and WorkManager.
