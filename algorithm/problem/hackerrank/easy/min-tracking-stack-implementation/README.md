# Min-Tracking Stack Implementation

> HackerRank · Easy

## Problem Statement

Implement a stack that supports *push*, *pop*, *top*, and *getMin* operations in O(1) time, where *getMin* returns the minimum element.

**Example**

Input

```
n = 10
operations = ['push 2', 'push 0', 'push 3', 'push 0', 'getMin', 'pop', 'getMin', 'pop', 'top', 'getMin']
```

Output

```
[0,0,0,0]
```

Explanation

```
- push 2 → stack = [2], min = 2
- push 0 → stack = [2,0], min = 0
- push 3 → stack = [2,0,3], min = 0
- push 0 → stack = [2,0,3,0], min = 0
- getMin → returns 0
- pop → removes 0, stack = [2,0,3], min = 0
- getMin → returns 0
- pop → removes 3, stack = [2,0], min = 0
- top → returns 0
- getMin → returns 0
```

## Input Format

- `operations`: array of n number of operations, each matching exactly one of:
  - `"push x"` where x is an integer and `0 <= x <= 100`
  - `"pop"`
  - `"top"`
  - `"getMin"`
- The next n lines contain the value of elements in the array.
- At any point in the sequence, the number of "pop" operations performed so far must be strictly less than the number of preceding "push" operations (so that the stack is never empty when "pop", "top", or "getMin" is called).

## Constraints

- For each "push x" operation, `0 <= x <= 100` and x is an integer
- Each entry in `operations` must match the pattern `^(push \d+|pop|top|getMin)$`
- pop, top, and getMin operations are only invoked when the stack is non-empty
- Total number of push operations `<= n` where n is the length of operations array

## Output Format

- An integer array of length equal to the total number of "top" and "getMin" operations in the input

## Sample Input 0

```
2
push 5
getMin
```

## Sample Output 0

```
5
```

## Sample Input 1

```
2
push 0
top
```

## Sample Output 1

```
0
```
