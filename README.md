# 📌 Firebase-Based Chat Application

A simple mobile chat application UI built using Firebase services. This project demonstrates basic integration of Firebase Authentication and Firestore with a clean and functional chat interface suitable for academic and beginner-level mobile development projects.

---

## 🛠 Project Description

The Firebase-Based Chat Application is a lightweight mobile app that allows users to authenticate and exchange messages in a shared chat room. The focus of this lab is on UI implementation and correct Firebase setup rather than advanced backend logic. It showcases how Firebase Authentication manages users and how Firestore stores and retrieves chat messages in real time.

---

## 📋 Minimum Functional Requirements

✔ Firebase project created and connected to the app
✔ User authentication (Email/Password or Anonymous)
✔ Basic UI for sending and viewing messages

---

## 💡 Optional Features Implemented

* Display of sender name or email
* Message timestamps
* Simple login screen before accessing chat
* Logout functionality

---

## 📱 App Screens

Screens included in this project:

* Login Screen
* Chat Screen
* Firebase Firestore Console (for verification)

Screenshots are available in the `screenshots/` folder.

---

## 📂 Project Structure

```
MobileDev-StudentName/
│
├── app/
│   └── source code
├── screenshots/
│   ├── login_screen.png
│   ├── chat_screen.png
│   └── firestore_console.png
├── README.md
```

---

## 🔐 Firebase Configuration

The app uses the following Firebase services:

* **Firebase Authentication**

  * Email/Password or Anonymous sign-in enabled

* **Cloud Firestore**

  * Used to store chat messages

⚠️ Ensure that Firebase Authentication providers are enabled and Firestore rules allow read/write access for authenticated users.

---

## 🚀 How to Run the Project

1. Clone this repository
2. Open the project in Android Studio
3. Connect the app to Firebase using Firebase Assistant
4. Add the `google-services.json` file to the `app/` directory
5. Sync Gradle files
6. Run the app on an emulator or physical device

---

## ⚠ Common Pitfalls to Avoid

* Forgetting to enable Firebase Authentication providers
* Incorrect Firestore security rules blocking reads/writes
* Hardcoding sensitive Firebase keys in the repository
* Not handling authentication state properly

---

 Firebase-Based Chat Application

---

## 📄 License

This project is for educational purposes only.
