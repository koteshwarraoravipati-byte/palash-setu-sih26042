# Native Santhali content collection workflow

This workflow prevents unverified language output from entering the classroom pack.

## First review batch

Start with these three Grade 1 classroom prompts from `translation-template.csv`:

1. `count_001` — गिनकर बताओ कि कितने आम हैं।
2. `read_001` — चित्र देखकर शब्द बोलो।
3. `math_001` — किस समूह में अधिक वस्तुएँ हैं?

## What the Santhali reviewer supplies

For every row:

- Natural child-friendly Santhali text in the agreed script.
- Teacher-friendly transliteration.
- Hindi back-translation or meaning confirmation.
- Reviewer name, role, and approval date.
- One slow, clear native recording as WAV or high-quality M4A.
- Confirmation that the phrase is suitable for a primary classroom.

## Recording rules

- Quiet room; keep the device 20–30 cm from the speaker.
- Speak once naturally and once slowly.
- Do not add music, effects, or background conversation.
- File name must match `phrase_id`, for example `count_001.wav`.
- Prefer mono, 44.1 kHz or 48 kHz, with the clearest available recording.
- The reviewer must listen to the saved file before approval.

## Approval gate

Set `review_status=approved` only after written review and audio review are both complete. Until then, keep the status as `needs_review`; the application will display a review warning rather than treating the target text as verified.

## Integration handoff

Send the completed CSV plus the audio files in one folder. The engineering integration will:

1. Validate IDs, empty fields, and duplicate phrase IDs.
2. Copy approved audio into `app/src/main/res/raw/` using lowercase filenames.
3. Replace only rows marked `approved`.
4. Rebuild the APK.
5. Re-test airplane-mode playback with the native reviewer.
6. Record measured latency and reviewer sign-off in `TEST_REPORT.md`.

Never fill a blank target field using machine translation or an unverified web result.
