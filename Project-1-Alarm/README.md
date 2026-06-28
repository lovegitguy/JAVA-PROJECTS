# ⏰ Java Alarm Clock (with MP3 Support)
 
A simple command-line alarm clock built in Java. Set an alarm in 12-hour format and optionally play your own MP3 file when it goes off — or use the default system beep.
 
---
 
## Features
 
- Set alarm in 12-hour format (AM/PM)
- Shows current date and time (IST)
- Shows how much time is left until the alarm
- Play a custom MP3 file when the alarm rings
- Falls back to system beep if no MP3 is provided
- Press Enter to stop the alarm
---
 
## Requirements
 
- Java JDK 8 or higher
- [JLayer library](https://search.maven.org/remotecontent?filepath=javazoom/jl/1.0.1/jl-1.0.1.jar) (`jl-1.0.1.jar`) — needed for MP3 playback
---
 
## Setup
 
### Step 1 — Download JLayer
 
Download the jar file from the link below and place it in the **same folder** as `Alarm.java`:
 
```
https://search.maven.org/remotecontent?filepath=javazoom/jl/1.0.1/jl-1.0.1.jar
```
 
Your folder should look like this:
 
```
📁 your-folder/
├── Alarm.java
└── jl-1.0.1.jar
```
 
### Step 2 — Compile
 
**Windows (PowerShell or CMD):**
```powershell
javac -cp .;jl-1.0.1.jar Alarm.java
```
 
**macOS / Linux:**
```bash
javac -cp .:jl-1.0.1.jar Alarm.java
```
 
### Step 3 — Run
 
**Windows:**
```powershell
java -cp .;jl-1.0.1.jar Alarm
```
 
**macOS / Linux:**
```bash
java -cp .:jl-1.0.1.jar Alarm
```
 
---
 
## How to Use
 
Once the program starts, follow the prompts:
 
```
**** WELCOME TO SET ALARM ****
Date : 28-06-2026
Time : 09:30 AM
 
Enter MP3 path (or press Enter to skip) : C:\Music\alarm.mp3
MP3 loaded: alarm.mp3
 
Set your alarm
Enter your hour (1-12) : 10
Enter your minutes (0-59) : 00
Enter am/pm : am
 
Your alarm is set for : 10:00 AM
Your alarm is set for : 0 hours and 30 minutes
This is a 12 hour format setting!
 
Monitoring clock... Do not close this window.
```
 
When the alarm goes off:
 
```
Wake Up Wake Up!
Press [ENTER] to turn off the alarm...
 
Alarm stopped successfully!
```
 
---
 
## MP3 Tips
 
- Provide the **full path** to your MP3 file, for example:
  - Windows: `C:\Users\YourName\Music\alarm.mp3`
  - macOS/Linux: `/home/yourname/music/alarm.mp3`
- If the file is not found or is not an `.mp3`, the program will automatically fall back to the system beep
- Press Enter at any time to stop the alarm
---
 
## One-liner Compile and Run (Windows)
 
```powershell
javac -cp .;jl-1.0.1.jar Alarm.java; if ($?) { java -cp .;jl-1.0.1.jar Alarm }
```
 
---
 
## Notes
 
- The clock is set to **IST (Asia/Kolkata)** timezone — change `ZoneId.of("Asia/Kolkata")` in the code if you are in a different timezone
- The alarm loops the MP3 (or beep) until you press Enter
- Do not close the terminal window while the alarm is running
 
