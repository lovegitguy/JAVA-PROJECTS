# 🔐 Smart Password Generator

A terminal-based Java tool that generates a password from a user's name, PAN number, and date of birth, then scores the result against a basic strength rubric.

![Java](https://img.shields.io/badge/Java-8%2B-orange)
![No Dependencies](https://img.shields.io/badge/dependencies-none-blue)
![License](https://img.shields.io/badge/license-MIT-green)

---

## ⚠️ Security Notice

This project generates passwords **deterministically** from personally identifiable information (name, PAN, date of birth). Anyone who knows — or can guess — a user's name, PAN, and DOB can reconstruct the exact same password, since there is no randomness involved at all.

This makes the tool unsuitable for generating real, account-facing passwords. It's best understood as a string-manipulation and control-flow exercise — practicing substring extraction, modular logic, and a scoring rubric — not as a security tool. See [Known Limitations](#known-limitations) for what a real fix would look like.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [How the Password Is Built](#how-the-password-is-built)
- [Strength Scoring](#strength-scoring)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Known Limitations](#known-limitations)
- [Roadmap](#roadmap)
- [Author](#author)

---

## Overview

Smart Password Generator takes a batch of users (name, PAN, date of birth) entered interactively, derives a password for each from fixed slices of that input, and reports a Weak / Medium / Strong rating based on length, character variety, and special-character presence.

Built with core Java only — no frameworks, no libraries, no external randomness source.

---

## Features

| Feature | Description |
|---|---|
| Batch Mode | Processes any number of users in one run |
| Deterministic Generation | Builds a password from fixed slices of name, PAN, and DOB |
| Strength Scoring | Rates each generated password Weak, Medium, or Strong |
| Formatted Output | Prints a readable summary block per user |

---

## How the Password Is Built

For each user, `generatePassword` concatenates four parts:

| Part | Source | Rule |
|---|---|---|
| Name part | `name` | First 3 characters, uppercased — or the full name uppercased if shorter than 3 chars |
| PAN part | `PAN` | Last 4 characters — or the full PAN if shorter than 4 chars |
| DOB part | `DOB` | Digits 1–2 and 5–6 of the cleaned date (slashes/hyphens stripped) — e.g., `DDMMYYYY` → `DD` + `YY` |
| Special part | `name.length() % 3` | One of three fixed 2-character symbol pairs (`@#`, `&*`, `^!`), chosen by name length mod 3 |

Example: `"Amitesh"`, PAN `"ABCDE1234F"`, DOB `15/06/2003` →
`AMI` + `234F` + `1503` + special part for `len % 3` → a password like `AMI234F1503^!`.

Every part is a fixed-position slice of public-ish or guessable input — there's no per-generation entropy anywhere in this function.

---

## Strength Scoring

`checkStrength` adds up points and maps the total to a label:

| Signal | Points |
|---|---|
| Length ≥ 12 | +2 |
| Length 8–11 | +1 |
| Contains uppercase | +1 |
| Contains lowercase | +1 |
| Contains digit | +1 |
| Contains special character (`!@#$%^&*()-_=+[]{}\|;:,.<>?`) | +2 |

| Score | Label |
|---|---|
| ≥ 6 | Strong |
| 4–5 | Medium |
| < 4 | Weak |

Worth noting: this rubric scores the *shape* of a password (length, character classes) — it has no concept of predictability. A password can score "Strong" here while still being trivially reconstructable by anyone who knows the three inputs that built it, because the rubric only ever sees the output string, never how it was derived.

---

## Getting Started

### Prerequisites

- JDK 8 or higher
- A terminal

```bash
java -version
javac -version
```

### Compile

```bash
javac SmartPasswordGenerator.java
```

### Run

```bash
java SmartPasswordGenerator
```

---

## Usage

```
============================================
     SMART PASSWORD GENERATOR - JAVA
============================================
How many users do you want to test? : 1

--- User 1 ---
Enter Name          : Amitesh
Enter PAN Number    : ABCDE1234F
Enter Date of Birth (DD/MM/YYYY) : 15/06/2003
--------------------------------------------
User       : Amitesh
Password   : AMI234F1503^!
Strength   : Medium
--------------------------------------------

Thank you for using Smart Password Generator!
```

---

## Known Limitations

- **Zero entropy.** The password is a pure function of name, PAN, and DOB — same inputs always produce the same output. There is no `SecureRandom`, no salt, no per-run variation anywhere in `generatePassword`.
- **Built from guessable/semi-public data.** PAN numbers and dates of birth are not secrets in most threat models (they appear on documents, forms, and ID cards). Deriving a password from them defeats the purpose of a password.
- **No input validation.** Empty names, malformed PANs, or DOBs that don't clean to at least 6 digits are handled by length checks (`< 3`, `< 4`, `< 6`), but there's no rejection of clearly invalid input — a DOB like `"abc"` is silently accepted and just produces a short `DOBpart`.
- **No uniqueness guarantee across users.** Two users with the same first 3 name letters, last 4 PAN digits, and DD/YY happen to collide on identical passwords; the generator has no awareness of other users in the batch.
- **Special-character set is only 3 fixed pairs.** `specialPart` has exactly 3 possible values (`@#`, `&*`, `^!`), chosen by `name.length() % 3` — an attacker doesn't even need to guess this part, just compute it.
- **No automated tests.** Verified by manual runs only.

---

## Roadmap

- [ ] Replace deterministic generation with `SecureRandom`-backed character selection
- [ ] Use PII (name, DOB) only to generate an optional memorable *hint*, never the password itself
- [ ] Add configurable password length and character-class requirements
- [ ] Add input validation with explicit rejection (and re-prompt) for malformed PAN/DOB
- [ ] Add a JUnit suite for `checkStrength` covering each scoring branch
- [ ] Replace the custom strength rubric with entropy-based scoring (e.g., bits of entropy from charset size × length)
- [ ] Split into separate classes/files (`PasswordGenerator`, `StrengthChecker`, `Main`)

---
