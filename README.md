# ChatterBox

ChatterBox is a real-time chat application built with a React frontend and a Spring Boot backend. It supports creating and joining chat rooms, sending and receiving encrypted messages, and real-time communication using WebSockets.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
  - [Frontend Setup](#frontend-setup)
  - [Backend Setup](#backend-setup)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [WebSocket Configuration](#websocket-configuration)
- [Encryption](#encryption)
- [Docker Support](#docker-support)
- [License](#license)

---

## Features

- Create and join chat rooms.
- Real-time messaging using WebSockets.
- Messages are encrypted before being stored in the database.
- Pagination support for retrieving chat history.
- Dark mode support for the frontend.
- Responsive design for mobile and desktop.

---

## Technologies Used

### Frontend
- **React**: For building the user interface.
- **Vite**: For fast development and build tooling.
- **TailwindCSS**: For styling.
- **React Router**: For navigation.
- **Axios**: For API requests.
- **SockJS & STOMP.js**: For WebSocket communication.

### Backend
- **Spring Boot**: For building the backend.
- **MongoDB**: For storing chat room and message data.
- **WebSocket**: For real-time communication.
- **Lombok**: For reducing boilerplate code.
- **Maven**: For dependency management and building the project.

---

## Project Structure

### Frontend

```

ChatterBox-frontend/
├── src/
│   ├── components/        # Reusable React components
│   ├── config/            # Configuration files (Axios, Routes, etc.)
│   ├── context/           # React Context API for global state management
│   ├── services/          # API service functions
│   ├── App.jsx            # Main App component
│   ├── main.jsx           # Entry point for React/Vite
│   └── index.css          # Global styles
├── public/                # Static assets like favicon, index.html
├── vite.config.js         # Vite configuration file
└── package.json           # Project dependencies and scripts

```

### Backend

```
ChatterBox-backend/
├── src/
│   ├── main/
│   │   ├── java/com/adityarastogi/ChatterBox_backend/
│   │   │   ├── config/             # WebSocket configuration
│   │   │   ├── controllers/        # REST and WebSocket controllers
│   │   │   ├── entities/           # MongoDB entities
│   │   │   ├── repositories/       # MongoDB repositories
│   │   │   ├── utils/              # Utility classes (e.g., encryption)
│   │   │   └── ChatterBoxBackendApplication.java  # Main application
│   │   └── resources/
│   │       └── application.properties  # Backend configuration
│   └── test/                          # Unit tests
├── Dockerfile                         # Docker configuration
├── pom.xml                            # Backend dependencies and build config
└── mvnw                               # Maven wrapper
```

---

## Setup Instructions

### Prerequisites
- Node.js (v18 or higher)
- Java 17
- MongoDB
- Maven
- Docker (optional)

---

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd ChatterBox-frontend

2. Install dependencies:
   ```bash
   npm install

3. Create a .env file in the root of the ChatterBox-frontend directory and add the following:
   ```bash
   VITE_API_URL=http://localhost:8080

4. Start the development server:
   ```bash
   npm run dev

5. Open your browser and navigate to http://localhost:5173.

---

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd ChatterBox-backend

2. Create a .env file in the src/main/resources directory and add the following:
   ```bash
    MONGODB_URI=mongodb://localhost:27017/chatterbox
    MONGODB_DATABASE=chatterbox
    FRONTEND_URL=http://localhost:5173

3. Build and run the backend:
   ```bash
   ./mvnw spring-boot:run

4. The backend will be available at http://localhost:8080.


---

## Environment Variables

### Frontend
- `VITE_API_URL`: The base URL for the backend API.

### Backend
- `MONGODB_URI`: MongoDB connection string.
- `MONGODB_DATABASE`: Name of the MongoDB database.
- `FRONTEND_URL`: URL of the frontend application (used for CORS configuration).

---

## API Endpoints

### Room Management
- `POST /api/v1/rooms` — Create a new chat room.
- `GET /api/v1/rooms/{roomId}` — Join an existing chat room.

### Messages
- `GET /api/v1/rooms/{roomId}/messages` — Retrieve messages for a room with pagination.

---

## WebSocket Configuration

- **Endpoint**: `/chat`
- **Subscribe**: `/topic/room/{roomId}`
- **Send**: `/app/sendMessage/{roomId}`

---

## Encryption

Messages are encrypted using **AES-256** before being stored in the database.  
The encryption key is hardcoded in the backend's `EncryptionUtil` class.  
**Important**: Replace this key with a secure, environment-specific key before deploying to production.

---

## Docker Support

### Build and Run the Backend with Docker

1. Navigate to the backend directory:
   ```bash
   cd ChatterBox-backend
   ```

2. Build the Docker image:
   ```bash
   docker build -t chatterbox-backend .
   ```

3. Run the Docker container:
   ```bash
   docker run -p 8080:8080 chatterbox-backend
   ```

---

## License

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for details.
