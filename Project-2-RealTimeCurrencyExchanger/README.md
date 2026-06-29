# Real Time Currency Exchanger

A terminal-based currency conversion tool built in Java. Supports 21 world currencies, live session history, and historical USD/INR rates from 2000 to 2025 — no external libraries, no API keys required.

---

## Demo

```
=======================================
 WELCOME TO REAL TIME CURRENCY CONVERTER
=======================================
     *** AVAILABLE CURRENCIES ***
  USD   - United States Dollar
  EUR   - Euro
  GBP   - British Pound
  INR   - Indian Rupee
  ...

Date : 28/06/2026
Time : 10:45 PM

------ Select Operation ------
1. See INR comparison
2. Exchange amount
3. Exchange History
4. Currency History
5. Exit
>> _
```

---

## Features

- **21 currencies** — USD, EUR, GBP, INR, JPY, CNY, AUD, CAD, CHF, KRW, SGD, HKD, AED, SAR, RUB, BRL, ZAR, MXN, TRY, THB, NZD
- **Any-pair conversion** — any currency to any currency via USD pivot
- **INR comparison table** — all 21 currencies vs INR at a glance
- **Session history** — every conversion logged with name, timestamp, and result
- **Historical rates** — USD ↔ INR yearly averages from 2000 to 2025
- **Zero dependencies** — pure Java standard library, no Maven/Gradle needed

---

## Requirements

| Tool | Version |
|------|---------|
| JDK  | 8 or above |

Check your Java version:
```bash
java -version
```

If not installed, download from [adoptium.net](https://adoptium.net).

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name/Project-2-RealTimeCurrencyExchanger
```

### 2. Compile

**Linux / macOS / Git Bash**
```bash
javac RealTimeCurrencyExchanger/*.java
```

**Windows PowerShell**
```powershell
javac RealTimeCurrencyExchanger\*.java
```

### 3. Run

**Linux / macOS / Git Bash**
```bash
java RealTimeCurrencyExchanger.CurrencyExchanger
```

**Windows PowerShell**
```powershell
java RealTimeCurrencyExchanger.CurrencyExchanger
```

---

## Project Structure

```
Project-2-RealTimeCurrencyExchanger/
├── RealTimeCurrencyExchanger/
│   ├── CurrencyExchanger.java   # Entry point — main menu loop and all 5 operations
│   ├── Currency.java            # Currency model (code, name, symbol)
│   ├── ExchangeRate.java        # Holds a rate between two currencies + convert()
│   ├── ExchangeRecord.java      # Single history entry with timestamp and name
│   └── RateService.java         # Rate map for all 21 currencies + historical USD/INR data
└── README.md
```

---

## Menu Options

| Option | Feature | Description |
|--------|---------|-------------|
| `1` | INR Comparison | Prints a table of all 21 currencies vs INR |
| `2` | Exchange Amount | Convert any amount between any two supported currencies |
| `3` | Exchange History | View all conversions done in the current session |
| `4` | Currency History | Look up the USD ↔ INR average rate for any year from 2000–2025 |
| `5` | Exit | Quit the program |

---

## Supported Currencies

| Code | Currency | Code | Currency |
|------|----------|------|----------|
| USD | United States Dollar | KRW | South Korean Won |
| EUR | Euro | SGD | Singapore Dollar |
| GBP | British Pound | HKD | Hong Kong Dollar |
| INR | Indian Rupee | AED | UAE Dirham |
| JPY | Japanese Yen | SAR | Saudi Riyal |
| CNY | Chinese Yuan | RUB | Russian Ruble |
| AUD | Australian Dollar | BRL | Brazilian Real |
| CAD | Canadian Dollar | ZAR | South African Rand |
| CHF | Swiss Franc | MXN | Mexican Peso |
| NZD | New Zealand Dollar | TRY | Turkish Lira |
| THB | Thai Baht | | |

---

## How Conversion Works

All rates are stored relative to USD. Any currency pair is calculated using:

```
rate(A → B) = rateToUSD(B) / rateToUSD(A)
```

This means every possible pair is supported without storing N² rates.

---

## Historical Data Coverage

| Pair | Years Available |
|------|----------------|
| USD → INR | 2000 – 2025 |
| INR → USD | 2000 – 2025 |

Other historical pairs are not currently supported.

---

## Limitations

- Rates are static mid-market approximations as of June 2025 — not live
- Historical data covers USD ↔ INR only
- Session history resets on program exit (no file persistence)

---

## License

This project is open source and available under the [MIT License](LICENSE).
