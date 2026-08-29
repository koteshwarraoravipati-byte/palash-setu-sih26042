# PALASH Setu verification report — Team Sprit

## Verified locally

- Gradle build: `assembleDebug` — SUCCESS
- Build mode: offline dependency mode
- Package: `com.teamsprit.palashsetu`
- Version: `0.1.0` / version code `1`
- Minimum Android: API 28 (Android 9)
- Target and compile SDK: API 35
- APK: `app/build/outputs/apk/debug/app-debug.apk`
- APK size: approximately 53.6 MB
- APK SHA-256: `5579B7060D95D43633A273F94C208B81F6602657DBC3BC9527381CAAC17C29C6`
- Manifest microphone permission: present (`android.permission.RECORD_AUDIO`)

## Code-path checks

- Home, Live Class, Lessons, and Teaching Aids screens compile.
- Live Class supports manual phrase selection without a network.
- Live Class optionally requests microphone permission and starts the Android speech-recognition engine with Hindi locale and offline preference.
- A deterministic local phrase lookup is used after recognition.
- Unmatched speech does not generate or guess Santhali text.
- Recognition-to-lookup elapsed time is shown in the UI when a result is returned.
- Santhali sample output remains visibly marked as pending native review.

## Not yet verified

- Installation and interaction on the team's physical Android device: no ADB device was available during this run.
- True offline Hindi speech recognition: requires an Android device with an installed offline Hindi speech pack and airplane-mode testing.
- Native-reviewed Santhali translations, transliteration, and recordings: not supplied yet.
- End-to-end Hindi-to-Santhali voice latency: must be measured on the target low-cost device.
- Translation accuracy and audio quality: must not be claimed before native review and pilot testing.

## Device test checklist

1. Enable Developer Options and USB debugging, then connect the device.
2. Install the APK.
3. Grant microphone permission.
4. Download the Hindi offline speech pack in device language settings.
5. Turn on airplane mode.
6. Test a known Hindi phrase and record the displayed elapsed time.
7. Test an unknown phrase and confirm the app refuses to invent an output.
8. Capture screenshots/video for the SIH demo.
