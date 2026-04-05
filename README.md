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
