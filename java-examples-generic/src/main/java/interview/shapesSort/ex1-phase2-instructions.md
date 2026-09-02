# Exercise 1 — Phase 2: Triangles and Ranges

> **Interviewer notes:** hand this out only after Phase 1 is working. Do not show it earlier.
>
> What happens to a value outside 0–30 is **deliberately left unspecified**. Do not volunteer it. A candidate who notices the gap and asks is telling you something; a candidate who silently picks a behaviour is telling you something else. Either is worth discussing — the omission is the test.

**Time:** ~20 minutes.

The requirements changed. Two things are different.

## Change 1 — a new shape

Add an **equilateral triangle**, where the input value is the length of a side.

| Shape    | Input is | Perimeter |
|----------|----------|-----------|
| Circle   | radius   | `2πr`     |
| Square   | side     | `4s`      |
| Triangle | side     | `3s`      |

## Change 2 — a new selection rule

Odd/even is gone. The shape is now chosen by the **value range**:

| Value   | Shape    |
|---------|----------|
| 0 – 10  | Circle   |
| 11 – 20 | Square   |
| 21 – 30 | Triangle |

Ranges are inclusive on both ends.

Everything else is unchanged: still sorted ascending by perimeter, still the same JSON output format.

## Example

Input:

```java
Arrays.asList(20, 5, 30, 12, 21, 10)
```

Expected output:

```json
[
  { "value": 5,  "shape": "Circle",   "perimeter": 31.4 },
  { "value": 12, "shape": "Square",   "perimeter": 48 },
  { "value": 10, "shape": "Circle",   "perimeter": 62.8 },
  { "value": 21, "shape": "Triangle", "perimeter": 63 },
  { "value": 20, "shape": "Square",   "perimeter": 80 },
  { "value": 30, "shape": "Triangle", "perimeter": 90 }
]
```

## What we are looking for

This phase is the real exercise. We are watching **how much of Phase 1 you have to change**.

- Adding `Triangle` should mean adding a class, not editing several
- The selection rule is the part that changed — is it isolated, or spread through your code?
- Did you have to touch the sorting or the JSON output at all? You should not have to

If your Phase 1 design does not absorb this cleanly, that is fine — **say so, and tell us what you would restructure**. We would rather hear that than watch a design get patched.

## Questions to expect afterwards

- What did you do with a value outside 0–30, and why?
- The ranges are currently hardcoded. Where would they come from if they were configuration?
- What if two shapes have the same perimeter? What is your ordering then, and is it stable?
- Suppose ranges could overlap and the first match wins. What changes?

---

**When you are done, tell us — there is a Phase 3.**
