# BEE Boolean Expression Evaluator

A lightweight Java library to **parse and evaluate arbitrarily nested boolean expressions** and calculate **completion rates** for partially completed items.

#### Expression Syntax

- Parentheses () for grouping
- AND / OR operators
- Leaf nodes are identifiers (course IDs, achievement IDs, etc.)
- Example string:

(123 AND 23 AND (53 OR 11) AND (09 OR 221)) OR 523

#### How It Works

1. Parser converts the expression string into a BeeNode tree.
2. Evaluator traverses the tree recursively:
    - AND nodes: sum the completion rates of all children
    - OR nodes: take the maximum completion rate among children
    - Value nodes: 1 if completed, 0 if not
3. Returns a BeeResult with done / total and a getRate() method

---

## Features

- Parse boolean expressions with **AND/OR** operators and nested parentheses
- Represent expressions as a **tree structure (`BeeNode`)**
- Evaluate expressions for a **given set of completed items**
- Calculate **completion rate** (0–100%) instead of just true/false
- Generic design, can be adapted for any type of items (courses, achievements, tasks, etc.)

---

Or simply include the core classes directly in your project:

- `BeeNode` – represents a node in the expression tree
- `BeeResult` – stores the completion progress (`done` / `total`) and calculates the rate
- Parser – converts a string expression into a `BeeNode` tree
- Evaluator – recursively calculates the completion rate for a given set of completed items

---

## License

MIT License – free to use, modify, and distribute.
