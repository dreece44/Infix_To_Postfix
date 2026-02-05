# Infix to Postfix Converter (Java)

A console-based Java program that converts mathematical expressions from **infix notation** (e.g., `A+(B*C)`) to **postfix notation** (Reverse Polish Notation, e.g., `A B C * +`) using stacks and operator precedence.

This project also includes input validation to catch common formatting mistakes before conversion.

---

## Features
- Converts infix expressions to postfix using a stack-based algorithm
- Supports:
  - Operators: `+  -  *  /  %`
  - Parentheses: `( )`
  - Operands: letters (`A–Z`, `a–z`) and digits (multi-digit numbers supported)
- Removes whitespace automatically before processing
- Built-in commands:
  - `list` → displays allowed characters
  - `end` → exits the program

---

## Example Usage

### Input (Infix)

(3+4)*2

### Output (Postfix)

This equation in Postfix Notation is: 3 4 + 2 *


---

## How It Works (High-Level)
1. **Tokenization**
   - The program scans the input and groups multi-digit numbers together (ex: `123` stays `123`), while operators and parentheses become separate tokens.
2. **Conversion (Infix → Postfix)**
   - Operands (numbers/letters) are sent directly to the postfix output.
   - Operators are pushed onto an operator stack and popped based on precedence rules.
   - Parentheses cause operators to pop until the matching `(` is found.
3. **Finalization**
   - Any remaining operators are popped and appended to the postfix result.

---

## Error Handling / Validation
The program checks for:
- **Invalid operators** (anything not supported)
- **Invalid expressions** (ex: multiple letters in a row without an operator)
- **Unbalanced parentheses**

If an error is detected, it prints an exception message instead of converting.

---

## Project Files
- `Infix_Postfix_Conversion.java`
  - Main program loop and conversion logic
  - Commands: `list` (allowed characters) and `end` (terminate)
- `Error_Cases.java`
  - Validation checks for invalid operands/operators and parenthesis mismatches

> Note: Both classes are in the package `CSCI3200`, so keep them in a matching folder structure (recommended: `src/CSCI3200/`) or update/remove the package line if needed.

---

## Running in Eclipse
1. Import the project into Eclipse.
2. Ensure files are in `src/CSCI3200/`.
3. Run `Infix_Postfix_Conversion.java` as a **Java Application**.
4. Type an infix expression (or `list`, or `end`).

---

## Running from the Command Line
From the folder that contains the `CSCI3200` directory:

```bash
javac CSCI3200/Error_Cases.java CSCI3200/Infix_Postfix_Conversion.java
java CSCI3200.Infix_Postfix_Conversion
