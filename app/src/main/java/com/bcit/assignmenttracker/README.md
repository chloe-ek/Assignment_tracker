# 📚 Assignment Tracker App

A clean, mobile-first Android app built with **Jetpack Compose** and **Room** 
to help students manage and track their assignments with ease.

## ✨ Features

- 📝 **Add/Edit Assignments**  
  Users can add new assignments or edit existing ones with details like title, course, due date, difficulty level (with star rating), and personal notes.

- 📆 **Due Date Picker**  
  The app uses a custom pastel-themed `DatePickerDialog` for consistent and user-friendly date input.

- 📌 **Upcoming Deadlines**  
  View assignments due **today**, in **1 day**, or in **2 days** through a dedicated "Upcoming" tab.

- ✅ **Mark as Completed**  
  Swipe through and mark tasks as done; completed tasks are moved to a separate section.

- 💬 **Motivational Quotes API Integration**  
  Fetch random motivational quotes via the [Zen quotes API](https://zenquotes.io/api) to help stay inspired.

- 🗑️ **Delete Assignments**  
  From the edit screen, users can also delete existing assignments.

## 🧱 Architecture & Code Design

- **Jetpack Compose**: Modern UI toolkit for building responsive layouts.
- **Room (RoomDB)**: Local persistence layer to store assignment data efficiently.
- **Ktor Client**: Lightweight HTTP client used to call external APIs.
- **Separation of Concerns**:
    - `data.local`: Contains Room entities, DAO, and local DB setup.
    - `data.remote`: Contains API endpoints, models, HTTP client, and repository access for motivation quotes.
    - `AssignmentRepository`: Combines both local and remote data sources in one class.

### Why I structured it this way:

- I wanted to follow a **modular and scalable structure**, separating concerns between local DB and remote API calls.
- Using `repository` patterns makes the app testable and maintainable.
- All state is managed using **`remember`** and **`mutableStateOf`** to keep UI reactive and simple.
- API logic is isolated into a `MotivationRemoteSource` class for clean separation from UI logic.

## 💡 Motivation

This app was created for an academic assignment to demonstrate CRUD operations, modern UI practices, and API consumption in Android development using Kotlin and Jetpack Compose.
The goal was to apply both design and development best practices while solving a real problem: keeping track of academic assignments efficiently.
