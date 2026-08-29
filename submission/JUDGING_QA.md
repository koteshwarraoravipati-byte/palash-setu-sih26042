# PALASH Setu judging Q&A

## What is the problem?
Hindi-medium trained teachers are often assigned to tribal-area primary schools but may not know the child’s home language. This creates a delivery gap in mother-tongue-based foundational literacy and numeracy.

## What is the solution?
PALASH Setu is an offline-first Android teaching companion. It combines Hindi teacher input, bounded classroom phrase retrieval, native-reviewed Santhali text/transliteration/audio, lesson outcome mapping, worksheets, and visual flashcards.

## Why Santhali first?
A focused language pack is safer and more demonstrable than claiming three low-resource languages without native validation. Ho and Mundari can be added through the same versioned review workflow.

## Is this cloud translation?
No classroom cloud dependency is required. The prototype keeps the classroom pack local. Hindi speech input uses the Android speech engine with offline preference and must be validated with an offline Hindi pack on the target device.

## How do you prevent hallucinated translations?
The app only returns a local classroom phrase match. If recognition does not match a known phrase, it refuses to invent target-language output and shows a safe fallback. Unreviewed Santhali content remains visibly marked for review.

## How will you prove the three-second requirement?
Measure recognition, phrase matching, audio preparation, and end-to-end response on the actual Android 9+ low-cost device. Report median and worst-case values from a repeatable test set; do not use estimates.

## How is content quality controlled?
A Santhali-speaking teacher or language expert reviews naturalness, meaning, child suitability, transliteration, and audio. Each approved row has reviewer metadata, approval date, and content-pack version.

## What works offline?
The lesson screens, phrase list, reviewed text, transliteration, approved recordings, worksheets, and flashcards. Synchronisation is optional and happens only when connectivity is available.

## What is the next scale step?
Complete a 300–500 phrase Santhali classroom pack, benchmark an on-device Hindi ASR model or engine, pilot with teachers and children, then create separately reviewed Ho and Mundari packs.
