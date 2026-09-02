# Exercise 1 — Phase 1: Shape Sorter

**Time:** ~30 minutes. Language: Java. You may use any IDE. A JSON library (Jackson or Gson) is on the classpath — use it if you want.

## The problem

You are given a list of integers. Each number describes a shape:

- **Odd** number → it is the **radius of a circle**
- **Even** number → it is the **side of a square**

Build the shapes, then return a **JSON array** describing them, **sorted ascending by perimeter**.

| Shape  | Input is | Perimeter |
|--------|----------|-----------|
| Circle | radius   | `2πr`     |
| Square | side     | `4s`      |

## Output format

A single JSON array, one object per shape, in sorted order:

```json
[
  { "value": 3, "shape": "Circle", "perimeter": 18.8 }
]
```

- `value` is the original input number
- `shape` is exactly `"Circle"` or `"Square"`
- `perimeter` is a JSON **number**, rounded to **at most one decimal place**, with no trailing `.0`
  (e.g. `18.84955...` → `18.8`, `43.98229...` → `44`)
- Key order within an object does not matter; array order does

## Example

Input:

```java
Arrays.asList(16, 3, 25, 2, 10, 7)
```

Expected output:

```json
[
  { "value": 2,  "shape": "Square", "perimeter": 8 },
  { "value": 3,  "shape": "Circle", "perimeter": 18.8 },
  { "value": 10, "shape": "Square", "perimeter": 40 },
  { "value": 7,  "shape": "Circle", "perimeter": 44 },
  { "value": 16, "shape": "Square", "perimeter": 64 },
  { "value": 25, "shape": "Circle", "perimeter": 157.1 }
]
```

Note that `10` sorts before `7` — the order is by perimeter, not by input value.

Whitespace and indentation are not significant — the array is compared as parsed JSON, not as text.

## What to implement

Implement this interface:

```java
public interface ShapeSorter {
    String sortShapes(List<Integer> parameters);
}
```

`ShapeSorterTest` is the spec — your implementation must make it pass. Do not modify the test.

## What we are looking for

This is a small problem, so the answer is not "does it work" — it is **how you structure it**. Assume this code will keep changing: new shape types, different selection rules, a different sort order.

We will be looking at:

- How you model the shapes and where the perimeter calculation lives
- How a number is turned into the right shape type
- How the sort order is expressed, and how easy it is to swap
- Where JSON serialization lives — a shape should not have to know it is being rendered as JSON
- Naming, and whether the code reads without comments

## Questions to expect afterwards

Be ready to talk through:

- What is the time complexity of your solution?
- What edge cases are not covered by the test? (empty list, negative numbers, `null`, duplicates, very large values)
- If we wanted XML instead of JSON tomorrow, what would change?
- Would you change anything if the input were 10 million numbers?

---

**When you are done, tell us — there is a Phase 2.**
