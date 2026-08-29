# Team Sprit setup checklist

## Local setup

The project has already been compiled and signed locally with Java 17, Gradle 8.9, Android SDK 35, and Build Tools 35.0.0. Android Studio is optional for opening and inspecting the project.

1. Open the `palash-setu` folder in Android Studio, or install the APK from `app/build/outputs/apk/debug/app-debug.apk`.
2. Use an Android 9+ phone/tablet or emulator.
3. For voice testing, grant microphone permission and ensure the device has an offline Hindi speech-recognition pack. The app requests offline preference but the device speech engine controls whether recognition is truly on-device.
4. Turn on airplane mode and repeat the Home → Live Class → Lessons → Teaching Aids flow.
5. Record the observed recognition-to-lookup time on the target device; do not claim sub-three-second latency until measured there.

## First content task

Ask the Santhali-speaking student/teacher to review these three Hindi phrases first:

- गिनकर बताओ कि कितने आम हैं।
-  चित्र देखकर शब्द बोलो।
- ध्यान से सुनो और दोहराओ।

For each phrase collect: natural Santhali text, teacher transliteration, slow native audio, reviewer name, approval date.

## First engineering task after the base app runs

Replace the demo phrase action in `MainActivity.kt` with:

- offline Hindi ASR adapter;
- local approved phrase lookup;
- Android raw audio playback;
- measured latency logging.

Never mark sample content as approved. The current app visibly says “native review required” so the internal demo does not misrepresent unverified language output.
