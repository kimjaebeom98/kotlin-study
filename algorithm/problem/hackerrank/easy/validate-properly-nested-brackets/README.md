# Validate Properly Nested Brackets

> HackerRank · Easy

## Problem Statement

Given a string, check if all brackets ('()', '{}', '[]') are properly matched and nested. Return 1 if valid, otherwise return 0.

**Example**

Input

```
code_snippet = if (a[0] > b[1]) { doSomething(); }
```

Output

```
1
```

Explanation

All brackets are properly matched: `'('` with `')'`, `'['` with `']'`, and `'{'` with `'}'`. No mismatches or improper nesting.

## Input Format

- The function takes a single parameter, `code_snippet`, which is a STRING.

## Constraints

- `0 <= code_snippet.length <= 1000`
- `code_snippet` consists of printable ASCII characters (character codes 32 to 126 inclusive)
- `code_snippet` may contain any combination of `'('`, `')'`, `'{'`, `'}'`, `'['`, `']'`, letters, digits, symbols, and whitespace
- `code_snippet` may be empty

## Output Format

- The function returns a BOOLEAN value, 1 for True and 0 for False.

## Sample Input 0

```
int x = 42; // no brackets here
```

## Sample Output 0

```
1
```

## Sample Input 1

```
() {} []
```

## Sample Output 1

```
1
```
