# PALASH Setu — Team Sprit

Offline AI-assisted vernacular pedagogy MVP for SIH2026 problem statement **SIH26042**.

> Prototype language: Santhali. This repository intentionally contains no invented Santhali translations or audio. Native-speaker review is required before publishing a content pack.

## Included

- Android 9+ compatible Kotlin + Jetpack Compose application
- Offline teacher dashboard
- Lesson library mapped to FLN outcomes
- Classroom phrase flow with a deterministic safe fallback
- Optional Hindi microphone input using the Android speech-recognition engine with offline preference
- Worksheet and visual flashcard prototype screens
- Dataset templates and native-speaker review workflow
- Architecture and demo documentation

## Current MVP boundary

The first build uses local sample content and a deterministic phrase flow. The live screen now accepts optional Hindi microphone input, requests offline recognition preference, measures recognition-to-lookup time, and falls back safely when no local phrase matches. Approved Santhali text and native audio recordings should be connected only after validation. The app must never hallucinate a tribal-language translation.

Important: Android's speech engine is device-dependent. `EXTRA_PREFER_OFFLINE` is a request, not proof that a device is offline. Validate with airplane mode on and an installed Hindi offline language pack.

## Run

1. Open this folder in Android Studio Ladybug or newer.
2. Allow Gradle sync.
3. Run on an Android 9+ phone/tablet or emulator.
4. Open **Live Class**, **Lessons**, and **Teaching Aids**.
5. Turn on airplane mode and repeat the flow.

The current app uses only local data. No API key or internet connection is required.

## Add reviewed content

Use `dataset/translation-template.csv` or the JSONL template. Ask a Santhali-speaking teacher/expert to review every target-language field and record the audio. Mark `review_status=approved` only after review. Add approved recordings under `app/src/main/res/raw/` with lowercase filenames.

## Roadmap

- Phase 1: validated Santhali content pack, native recordings, worksheet export
- Phase 2: offline Hindi ASR adapter benchmarked on the actual tablet
- Phase 3: compact translation fallback and optional sync service
- Phase 4: Ho and Mundari packs after separate native review

## Build status

The debug APK builds successfully with Android Platform 35 and Java 17. Package ID: `com.teamsprit.palashsetu`. Minimum Android version: Android 9 (API 28). The APK is generated under `app/build/outputs/apk/debug/`.

## Demo honesty

Do not report translation accuracy, audio quality, or sub-three-second latency until tested with native-reviewed data on the actual device.
