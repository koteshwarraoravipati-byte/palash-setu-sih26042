# PALASH Setu architecture

```text
Teacher Hindi voice / phrase button
          |
Offline speech adapter
          |
Classroom phrase matcher
          |
Approved Santhali content pack
     /         |          \
  text   transliteration   native audio
          |
  worksheet / flashcard renderer

Optional sync when online -> versioned content pack -> local device
```

## Safety boundaries

- Phrase matching is restricted to classroom-domain content.
- Unreviewed target text is never presented as approved language.
- Audio uses approved native recordings; target-language TTS is not assumed.
- Sync is optional; classroom operation remains local.
- Low-confidence speech uses visible phrase buttons and replay fallback.

## MVP latency budget

Measure on the actual target device:

| Component | Target budget | Must be measured? |
|---|---:|---|
| Hindi speech recognition | 1.5 s | Yes |
| Phrase match | 0.5 s | Yes |
| Audio preparation | 1.0 s | Yes |
| Total | <= 3.0 s | Yes |
