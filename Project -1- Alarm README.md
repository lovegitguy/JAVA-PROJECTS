# Console Alarm Clock (Java)

A lightweight, multi-threaded console application written in Java that tracks real-time clock cycles and triggers a system alarm at a user-specified time.

The application utilizes the `java.time` API for precise time tracking and Java multithreading concepts to run the alarm sound in the background while allowing the user to terminate it safely.

---

## 🚀 Features

* **Real-time Time Tracking**
  - Fetches and displays the current date and time.
  - Uses Indian Standard Time (`Asia/Kolkata`) timezone for accurate time calculation.

* **Smart Time-Left Calculator**
  - Calculates the exact hours and minutes remaining until the alarm triggers.
  - Handles same-day and next-day alarm differences smoothly.

* **Background Alarm Thread**
  - Creates a dedicated background thread (`beepThread`) for continuous alarm sound.
  - Prevents the main program from freezing while the alarm is running.

* **Thread-Safe Alarm Control**
  - Uses a `volatile` boolean flag (`alarmRunning`) for safe communication between threads.
  - Allows the background alarm thread to stop immediately after user input.

* **Input Validation**
  - Checks invalid user inputs:
    - Hour range (1-12)
    - Minute range (0-59)
    - AM/PM format

* **12-Hour Format Support**
  - Accepts user-friendly AM/PM input.
  - Internally converts the time into 24-hour format for calculations.

---

## 🛠️ Technical Architecture

The application follows a simple multi-threaded architecture where time monitoring and alarm execution are handled efficiently.

```
              Main Thread
                  |
                  |
        User Sets Alarm Time
                  |
                  |
          Checks Time Every 1s
                  |
                  |
          Alarm Time Reached?
                  |
                  |
        Starts Background Thread
                  |
                  |
        -------------------------
        |                       |
        |                       |
   Main Thread             beepThread
        |                       |
 Waits for ENTER          Plays Alarm Sound
        |                       |
 User Stops Alarm         Runs Until Signal
        |                       |
        -------------------------
                  |
        alarmRunning = false
                  |
          Thread Terminates
```

---

## 📋 Prerequisites

* **Java Development Kit (JDK):**
  - Version 9 or higher.
  - Uses `Duration.toMinutesPart()` introduced in Java 9.

* **Operating System Audio:**
  - System terminal/console beep must be enabled to hear the alarm sound.

---

## 💻 How To Run

### 1. Clone or Download the File

Ensure the file name is:

```
Alarm.java
```

---

### 2. Compile the Program

Open terminal inside the project folder:

```bash
javac Alarm.java
```

---

### 3. Run the Application

```bash
java Alarm
```

---

## 🕹️ Usage Example

```
**** WELCOME TO SET ALARM ****

Date : 15-06-2026
Time : 11:24 AM

Set your alarm

Enter your hour (1-12) : 7
Enter your minutes (0-59) : 30
Enter am/pm : am


Your alarm is set for : 7:30 AM
Your alarm is set for : 20 hours and 6 minutes

This is a 12 hour format setting!

Monitoring clock... Do not close this window.


Wake Up Wake Up!

Press [ENTER] to turn off the alarm...

[User presses ENTER]

Alarm stopped successfully!
```

---

## ⚙️ Code Structure Highlights

### `ZoneId.of("Asia/Kolkata")`

Ensures accurate timezone-based time tracking independent of system timezone.

### `volatile boolean alarmRunning`

Provides memory visibility between threads and allows the main thread to safely stop the background alarm thread.

### `Thread`

Used to create a separate execution path for the alarm sound without blocking the main application.

### `freshStart.withNano(0)`

Removes nanosecond precision to allow reliable comparison between current time and user-defined alarm time.

---

## 🔮 Future Improvements

- Custom MP3 alarm support
- JavaFX GUI interface
- File picker for selecting alarm sounds
- Multiple alarm scheduling
- Snooze functionality
- Desktop notifications

---
