# 💸 ExpenseTracker

A terminal-based **Personal Expense Tracker** built in Java. Log expenses, filter by month, analyze category-wise spending, and persist data across sessions — all from the command line.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Compile](#compile)
  - [Run](#run)
- [Usage](#usage)
- [Data Persistence](#data-persistence)
- [Class Design](#class-design)
- [Limitations & Future Scope](#limitations--future-scope)
- [Author](#author)

---

## Overview

ExpenseTracker is a lightweight CLI application that lets you record and analyze your daily expenses without any external database or dependency. Data is stored locally in a CSV file, making it portable and easy to inspect.

Built with core Java — no frameworks, no libraries — focusing on clean OOP design, file I/O, and practical data processing logic.

---

## Features

| Feature | Description |
|---|---|
| Add Expense | Log amount, category, and date |
| View All | Print every recorded expense in a readable format |
| Monthly Report | Filter and summarize expenses by `MM/YYYY` |
| Category Analysis | Break down total spending per category and surface the highest |
| Save to CSV | Persist the current session's data to `expenses.csv` |
| Load from CSV | Restore previously saved expenses from disk |

---

## Project Structure

```
ExpenseTracker/
├── ExpenseTracker.java     # Entry point — main menu loop
├── ExpenseManager.java     # Core logic — add, display, filter, report
├── Expense.java            # Data model — amount, category, date
└── expenses.csv            # Auto-generated on first save
```

> All three classes are currently in a single file for simplicity. See [Future Scope](#limitations--future-scope) for planned refactoring.

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A terminal (Command Prompt, PowerShell, Bash, etc.)

Verify your installation:

```bash
java -version
javac -version
```

### Compile

```bash
javac ExpenseTracker.java
```

### Run

```bash
java ExpenseTracker
```

---

## Usage

On launch, you'll see the main menu:

```
Personal Expense Tracker

1. Add Expense
2. Display Expenses
3. Monthly Report
4. Highest Expense Category
5. Save Data
6. Load Data
7. Exit
```

**Adding an expense:**

```
Enter amount: 450
Enter category: Food
Enter date (DD/MM/YYYY): 15/06/2025
Expense added successfully.
```

**Monthly report:**

```
Enter month and year (MM/YYYY): 06/2025

Monthly Report for 06/2025
Date: 15/06/2025 | Category: Food | Amount: Rs.450.0
Total Expense: Rs.450.0
Number of Entries: 1
```

**Category breakdown:**

```
Category Wise Spending:
Food : Rs.1200.0
Travel : Rs.850.0
Utilities : Rs.600.0
Highest Spending Category: Food (Rs.1200.0)
```

---

## Data Persistence

Expenses are saved to and loaded from `expenses.csv` in the project root. The format is:

```
amount,category,date
450.0,Food,15/06/2025
850.0,Travel,20/06/2025
```

- **Save (Option 5):** Writes the current in-memory list to `expenses.csv`, overwriting any previous data.
- **Load (Option 6):** Reads `expenses.csv` and replaces the in-memory list. Always load before adding new entries if you want to preserve history.

> ⚠️ Unsaved expenses are lost on exit. Always save before selecting option 7.

---

## Class Design

### `Expense`
Pure data model with three fields: `amount` (double), `category` (String), `date` (String).

| Method | Purpose |
|---|---|
| `display()` | Returns a human-readable string for console output |
| `toCSV()` | Serializes the object to a comma-separated line for file storage |

### `ExpenseManager`
Encapsulates all business logic. Operates on an `ArrayList<Expense>`.

| Method | Purpose |
|---|---|
| `addExpense()` | Creates and stores a new `Expense` object |
| `displayAll()` | Prints all expenses with index |
| `monthlyReport()` | Filters by `MM/YYYY` substring match and prints totals |
| `highestCategory()` | Aggregates per category and identifies the maximum |
| `saveToFile()` | Writes CSV via `BufferedWriter` |
| `loadFromFile()` | Reads and parses CSV via `BufferedReader` |

### `ExpenseTracker` (Main)
Drives the `do-while` menu loop. Handles all user input via `Scanner` and delegates to `ExpenseManager`.

---

## Limitations & Future Scope

**Current limitations:**
- All three classes are in a single `.java` file — not idiomatic for larger projects
- Date is stored as a plain `String`; no validation or `LocalDate` parsing
- Category matching is case-insensitive but date matching relies on raw substring comparison
- No duplicate-load guard; loading twice stacks entries unexpectedly
- `Save` overwrites the file completely — no append/merge support

**Planned improvements:**
- [ ] Refactor into separate `.java` files with proper package structure
- [ ] Replace `String` dates with `LocalDate` and add format validation
- [ ] Add input validation and error recovery for invalid menu choices
- [ ] Implement date range filtering (e.g., weekly reports)
- [ ] Add a budget limit feature with overspend alerts
- [ ] Export summary as a formatted text report

---
