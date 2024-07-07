# Simple Notes App

## Overview
This Notes App is a mobile application designed for Android, built using Java. It allows users to create, edit, and manage their personal notes efficiently. The app utilizes Android's `SharedPreferences` to store notes persistently, ensuring that notes are saved across sessions.

## Features
- **Create Notes:** Add new notes with just a few taps.
- **Edit Notes:** Update existing notes by tapping on any note in the list.
- **Delete Notes:** Long press on a note to delete it with confirmation.
- **Persistent Storage:** Notes are stored locally on the device and persist between sessions.

## Setup

### Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK) 8 or higher**: You need to have JDK installed on your machine. You can download it from [Oracle's official site](https://www.oracle.com/java/technologies/javase-downloads.html).
- Android Studio
- Android SDK

### Cloning the Repository
Start by cloning the repository to your local machine:
git clone https://github.com/jpmal22/simple-notes-androidapp.git
cd [REPOSITORY_NAME]

### Usage

**Running the App**
- In Android Studio, click on the 'Run' button.
- Choose your desired device or emulator.
- The app should launch and display a list of existing notes or a default welcome note.

**Adding a Note**
- Tap on the menu option to add a new note. This will open a new screen where you can type your note.
- After typing, navigate back to save the note automatically.

**Editing a Note**
- Tap on any existing note in the list to edit it.
- Make changes and navigate back to save the updates automatically.

**Deleting a Note**
- Long press on any note you wish to delete.
- Confirm deletion in the popup dialog.

### Architecture

The app uses MainActivity to display and manage the list of notes and EditNote activity to handle the creation and editing of individual notes. Notes are saved in SharedPreferences in the form of a HashSet to ensure no duplicates and persistence across sessions.

### Authors

Jose Paolo Alejandro - https://github.com/jpmal22/

