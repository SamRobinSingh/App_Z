# Z3GION 👁️🤖

> **Empowering the visually impaired with an autonomous, real-time mobile AI agent for seamless navigation and independent daily living.**

---

## 📖 About the Project

**Z3GION** is a multimodal AI system that functions as a proactive, real-time virtual assistant for visually impaired individuals. Moving beyond passive object-detection tools, Z3GION acts as a true digital proxy. It leverages edge computing and the powerful **Gemini 2.0 Flash_Exp API** to understand complex physical scenes, read documents, provide location-based navigation, and deliver life-saving proximity alerts. 

Designed for the "Agentic Economy," Z3GION allows users to interact with their environment hands-free, seamlessly switching between cloud and offline AI models to ensure reliable safety anywhere, anytime.

---

## ✨ Core Features

* 🗣️ **Real-Time Virtual Assistant:** Multimodal voice-controlled agent that describes surroundings, people, colors, and lighting.
* 🛑 **Close Proximity Alerts:** Actively calculates approach velocity to warn users of physical obstacles using auditory and tactile feedback.
* 🌐 **Dynamic Model Switching:** Seamlessly toggles between high-powered pre-trained models (Gemini 2.0 Flash), custom-trained models, and on-device offline models when internet connectivity drops.
* 🗺️ **Advanced Navigation:** Integrates Google Maps API for real-time, location-based route guidance.
* 📄 **Multilingual OCR:** Reads signs, documents, currency, and handwritten notes aloud in multiple languages.
* 💾 **Secure Chat History:** Stores navigation logs and prompt history locally for quick recall and user privacy.

---

## 🛠️ System Architecture & Tech Stack

### Frontend (Mobile Application)
* **Language:** Kotlin 
* **Capabilities:** Cross-platform compatibility, real-time camera framing, microphone access, and text-to-speech output.

### Backend (Server & API Routing)
* **Framework:** Python / Flask
* **Database:** MongoDB (for user preferences and chat history storage)
* **Cloud & Edge:** Deployed via AWS/GCP, with edge computing support via Raspberry Pi for low-latency processing.

### AI & Machine Learning
* **Primary LLM:** Gemini 2.0 Flash_Exp API (for low-latency multimodal reasoning).
* **Vision & Detection:** TensorFlow / PyTorch for custom offline image captioning and object detection.
* **Audio Processing:** Google Speech-to-Text API & gTTS (or MaryTTS).

### Proximity Alert Logic
Z3GION utilizes a Time-To-Collision (TTC) heuristic to trigger safety alerts. For an object at distance $d$ approaching with relative velocity $v_{rel}$, an alert triggers if TTC falls below the safety threshold $\tau_{safety}$:

$$TTC = \frac{d}{v_{rel}} < \tau_{safety}$$

---

## 🚀 Getting Started

### Prerequisites
* Android Studio (latest version)
* Python 3.9+
* API Keys for **Google Gemini** and **Google Maps**

### Installation

**1. Clone the Repository**
```bash
git clone [https://github.com/yourusername/Z3GION.git](https://github.com/yourusername/Z3GION.git)
cd Z3GION
# 🚀 Z3GION Project Setup Guide

## 🔧 Backend Setup (Flask)

```bash
cd backend
python -m venv venv

# Activate virtual environment
# On Linux / Mac:
source venv/bin/activate

# On Windows:
venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt
```

---

## 🔑 Environment Variables

Create a `.env` file inside the `backend` directory and add the following:

```env
GEMINI_API_KEY=your_gemini_api_key_here
MAPS_API_KEY=your_google_maps_api_key_here
FLASK_ENV=development
```

---

## ▶️ Start the Server

```bash
flask run --host=0.0.0.0 --port=5000
```

---

## 📱 Frontend Setup (Android)

1. Open the `frontend` folder in **Android Studio**
2. Sync the Gradle project
3. Navigate to `strings.xml` (or your config file)
4. Update the **base URL** to your Flask server:

```
http://<your-ip-address>:5000
```

5. Build and run the app on a **physical Android device**  
   *(Camera and hardware sensors are required)*

---

## 📲 Usage

### 🚀 Launch the App
Open **Z3GION** on your mobile device.

### 🎤 Voice Activation
- Tap the screen  
**or**
- Say the wake word

### ❓ Ask Questions
Examples:
- *"Z3GION, what is written on the sign in front of me?"*
- *"Navigate me to the nearest bus stop."*

### 📡 Offline Mode
- Automatically activates when there is no internet connection  
- Uses **TensorFlow model** for:
  - Basic object detection  
  - Safety alerts  

---

## 🔮 Roadmap / What's Next

### 🌆 Smart City Integration
- Connect Z3GION with third-party city transit agents  
- Enable real-time autonomous transport booking *(Fetch.ai ecosystem)*

### 👓 Hardware Wearable
- Transition from mobile camera to **smart glasses**
- Enable fully hands-free scanning

### ⚡ Enhanced Local Processing
- Optimize PyTorch mobile models
- Reduce battery consumption
- Improve performance on-device

---

