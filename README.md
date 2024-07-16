# Event Planner Application

This is an Android application for planning and managing events. The application allows users to sign up, log in, create events, add guests, and manage their information. Additionally, an admin user can manage all users, events, and guests.

## Features

- User registration and login
- Admin login with special privileges
- Create and manage events
- Add and manage guests
- Update user credentials
- Delete users, events, and guests
- View lists of users, events, and guests

## Database Structure

The application uses SQLite for local database storage with the following tables:

### Users Table (`users`)

- **email**: TEXT PRIMARY KEY
- **password**: TEXT

### Guests Table (`guests`)

- **email**: TEXT PRIMARY KEY
- **phone**: LONG
- **host**: TEXT

### Events Table (`event`)

- **NAME**: TEXT PRIMARY KEY
- **address**: TEXT
- **hostname**: TEXT
- **password**: INT
- **email**: TEXT
- **guestl**: TEXT

## Getting Started

### Prerequisites

- Android Studio
- A device or emulator running Android

### Installing

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/event-planner-app.git
    ```

2. Open the project in Android Studio.

3. Build and run the project on your device or emulator.

## Usage

### User Registration

1. Open the application.
2. Click on "Sign Up".
3. Enter your email and password.
4. Click "Register".

### User Login

1. Open the application.
2. Click on "Login".
3. Enter your email and password.
4. Click "Login".

### Admin Login

1. Open the application.
2. Click on "Login".
3. Enter "aadarsh" as the email and "mishra" as the password.
4. Click "Login".

### Creating an Event

1. Log in as a user.
2. Click on "Create Event".
3. Enter the event details (name, address, host, ticket price, guest list).
4. Click "Create".

### Managing Guests

1. Log in as a user.
2. Click on "Manage Guests".
3. View the list of guests.
4. Add or delete guests as needed.

### Admin Management

1. Log in as an admin.
2. Manage users, events, and guests through the admin menu.

## Built With

- [Android Studio](https://developer.android.com/studio) - The official IDE for Android development
- [SQLite](https://www.sqlite.org/index.html) - A C-language library that implements a small, fast, self-contained, high-reliability, full-featured, SQL database engine


