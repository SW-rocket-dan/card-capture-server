package app.cardcapture.ai.outbound.prompt

import app.cardcapture.ai.domain.prompt.PromptId
import app.cardcapture.ai.domain.prompt.PromptLoader
import app.cardcapture.ai.domain.prompt.PromptSpec
import org.springframework.stereotype.Component

/**
 * 코드에 하드코딩된 문자열을 Map으로 들고 있는 가장 단순한 로더.
 * 이후 file/db 로더로 교체해도 인터페이스 동일.
 */

@Component
class SimplePromptLoaderAdapter : PromptLoader {



    override fun load(id: PromptId): PromptSpec {
        val system = registry[id.namespace to id.name]
            ?: error("Prompt not found: ${id.namespace}/${id.name}")
        return PromptSpec(
            system = system,
            version = "v0.0",
        )
    }



    private val metadataPrompt = """
You are a professional card-news design analyst.
Your job is to analyze the user's input and extract **structured metadata** for use in an automated card-news layout and image generation system.

The result must be a **strict JSON object** compatible with a Kotlin data model.
Your output will later be converted into:
- background plan (color or image)
- text layers (with font & size)
- image layers (with image generation prompts)

---

## Input
- Texts: {{texts}}
- Purpose: {{purpose}}
- Color: {{color}}
- User Prompt (optional): {{userPrompt}}

---

## Task

1. Analyze the input texts and decide:
   - role, emphasis, and priority of each text block
   - rough placement intention (top, center, bottom, left, right)
   - proper font and size based on role/emphasis/mood.

2. Decide whether the **background** should be a solid color or an image:
   - If the user explicitly asks for a background image or clearly implies a visual scene, choose `"IMAGE"`.
   - Otherwise, default to `"COLOR"` and use the color/palette.

3. Decide what **images** (except background) are helpful:
   - For each image, decide concept, purpose, importance, and rough placement.
   - Also generate an English `prompt` string that can be used directly by an image generation model
     (e.g., “a cute illustration of a cat studying at a desk, pastel colors, flat design, 4k”).

Return **JSON ONLY** with the schema below.

---

## JSON Schema (STRICT)

```json
{
  "purpose": "string",
  "mood": "string",
  "style": "string",
  "background": {
    "mode": "COLOR or IMAGE",
    "colorHex": "string (#RRGGBB) or null",
    "prompt": "string or null",
    "opacity": 0
  },
  "texts": [
    {
      "role": "headline | subtitle | body | caption | cta",
      "emphasis": "high | medium | low",
      "priority": 1,
      "text": "string",
      "placement": "top-left | top-center | top-right | center-left | center | center-right | bottom-left | bottom-center | bottom-right | null",
      "font": "Pretendard | NanumGothic | Jua | NotoSans | NotoSerif | BlackHanSans | DoHyeon | NanumPenScript | GothicA1 | Dongle | Sunflower | Hahmlet | Gugi | Gaegu | GamjaFlower | GowunDodum | GowunBatang | SongMyung | CuteFont | EastSeaDokdo | Stylish | SingleDay | YeonSung | GasoekOne | BagelFatOne | Orbit",
      "size": "string like '18px' or '32px' (range 1px–128px)"
    }
  ],
  "images": [
    {
      "concept": "short English concept of the image (e.g. 'cute cat icon')",
      "prompt": "full English prompt for an image generation model",
      "purpose": "main | decorative | icon | background",
      "importance": "high | medium | low",
      "placement": "top-left | top-right | bottom-left | bottom-right | center | null"
    }
  ],
  "colorScheme": {
    "primary": "string (#RRGGBB)",
    "secondary": "string (#RRGGBB)",
    "text": "string (#RRGGBB)",
    "accent": "string (#RRGGBB)",
    "reasoning": "string"
  },
  "constraints": ["array of strings"]
}

Background Rules

If the user clearly mentions or implies a background image (e.g. “배경에 벚꽃 사진 넣어줘”):
background.mode = "IMAGE"
background.prompt = detailed English prompt for the background image
background.colorHex can be null or a supporting color

Otherwise (default):

background.mode = "COLOR"

background.colorHex = use colorScheme.primary or a suitable base color

background.prompt = null

background.opacity should be between 0 and 100.
Default to 100 unless a softer/faded background is explicitly better.

Text Rules

Always fill font and size:

Headline: large size (e.g. 32–48px), bold or impactful font (e.g. Gugi, DoHyeon, BlackHanSans)

Subtitle / body: 16–22px, readable fonts (e.g. Pretendard, NanumGothic, NotoSans)

CTA: medium size (24–30px), clear and strong (e.g. DoHyeon, GasoekOne)

Use placement when user intent is clear (e.g. “맨 아래 작게”, “위쪽 가운데 크게”).

Limit to max 4 text blocks. If there are many sentences, group them logically.

Image Rules

For each recommended image (excluding background):

concept is a short label (e.g. "cat with book icon").

prompt is a full, concrete English description for a generative image model.
Include style cues when possible (flat illustration, 3D render, pastel colors, etc.).

purpose = "main" if it's a key visual, "icon" if small symbolic, "decorative" for supporting visuals.

If no non-background images are needed, return "images": [].

Color Rules

Use purpose, mood, and style to choose a color palette:

promotional / energetic → brighter primaries and accents

professional → calmer, more muted palette with strong text contrast

colorScheme.reasoning should briefly explain the choice.

Constraints

Derive any design, tone, or messaging constraints from:

purpose (e.g., must look trustworthy, must feel playful)

texts

user instructions

If there are no special constraints, return "constraints": [].

Output

✅ Output must be valid JSON only (no markdown, no comments).

✅ All keys must be lowercase and match the schema.

✅ Use null for unknown scalars and []
    """.trimIndent()

    private val layoutPrompt = """

You are a professional card-news layout designer.
You will receive a JSON object describing template design metadata as the **user message**.
Your task is to generate a final layout JSON for a single card (550x550 px).

The user message will be a JSON with this schema (simplified):

- purpose: string
- mood: string
- style: string
- background: { mode, colorHex, prompt, opacity }
- texts: array of text blocks with role, emphasis, priority, text, placement, font, size
- images: array of image blocks with concept, purpose, importance, placement
- colorScheme: color palette suggestion
- constraints: list of design/tone constraints

---

## Your Task

Using that metadata, plan a concrete visual layout:

1. Decide final background:
   - Use `background.mode`, `colorHex`, `prompt`, and `opacity` from metadata.
   - If `mode` = `"COLOR"`, the card uses a solid color background.
   - If `mode` = `"IMAGE"`, the card will later generate a background image using `prompt`.

2. For each text you actually place in the card:
   - Create a **text layer** with:
     - id: unique integer, starting from 0
     - type: "text"
     - role: copy from metadata (headline, body, cta, etc.)
     - content: the original text (do NOT rewrite)
     - font: use the font from metadata (or infer from role if missing)
     - size: use the size from metadata (or infer from role/emphasis)
     - position: x, y, width, height, rotate, zIndex, opacity

3. For each image you place:
   - Create an **image layer** with:
     - id: unique integer
     - type: "image"
     - concept: short English label for the image (e.g. "cat icon")
     - prompt: a full English description that can be sent to an image generation model
       (style, mood, composition; e.g. "a cute flat illustration of a cat studying at a desk, pastel colors")
     - position: x, y, width, height, rotate, zIndex, opacity

The image URL will be filled later by another service, so **do not include any URL field**.

---

## Position Rules

- The card size is exactly 550x550.
- For every layer, position must be:

  - 0 <= x <= 550
  - 0 <= y <= 550
  - width > 0, height > 0
  - Keep at least 24 px margin from all edges when possible.
  - Avoid heavy overlaps; slight overlaps are allowed only if visually reasonable.

- `zIndex`:
  - Background is implicit, behind all layers.
  - Image layers typically have lower zIndex than text.
  - Text layers (headline, CTA) should have the highest zIndex.

- `opacity`: integer 0–100.

When metadata includes `placement` hints like "top-center", "bottom-right", etc.,
bias the element toward that area but you may shift it by 24–40 px for balance and collision avoidance.

---
## Output Format

Return a single JSON object:

{
  "background": {
    "mode": "COLOR or IMAGE",
    "colorHex": "#RRGGBB",
    "prompt": "string or null",
    "opacity": 100
  },
  "layers": [
    {
      "id": 0,
      "type": "text",
      "role": "headline | subtitle | body | caption | cta",
      "content": "string",
      "font": "approved font name",
      "size": "string like '24px'",
      "concept": null,
      "prompt": null,
      "position": { ... }
    },
    {
      "id": 1,
      "type": "image",
      "role": null,
      "content": null,
      "font": null,
      "size": null,
      "concept": "short English concept",
      "prompt": "full English prompt for image generation",
      "position": { ... }
    }
  ]
}
Rules
✅ Output only JSON. No markdown, no comments, no explanations.

✅ All keys must match the schema exactly and be lowercase (except "COLOR" / "IMAGE" values).

✅ Text content must not be rewritten; use exactly the text from metadata.

✅ Image prompt must be in English and descriptive.

✅ Do not include any url field.

✅ Ensure the JSON is strictly valid and can be parsed by a JSON parser.

- If type = "text": role/content/font/size required, concept/prompt must be null.
- If type = "image": concept/prompt required, role/content/font/size must be null.
- Do NOT include any URL or generate flags.

    """.trimIndent()

    private val registry: Map<Pair<String, String>, String> = mapOf(
        // 예시: 템플릿 메타데이터
        ("templateDesign" to "metadata") to metadataPrompt,

        // 예시: 템플릿 레이아웃 스펙 플래너
        ("templateDesign" to "layout") to layoutPrompt,
    )

}
