Z3GION 👁️🤖
Empowering the visually impaired with an autonomous, real-time mobile AI agent for seamless navigation and independent daily living.

📖 About the Project
Z3GION is a multimodal AI system that functions as a proactive, real-time virtual assistant for visually impaired individuals. Moving beyond passive object-detection tools, Z3GION acts as a true digital proxy. It leverages edge computing and the powerful Gemini 2.0 Flash_Exp API to understand complex physical scenes, read documents, provide location-based navigation, and deliver life-saving proximity alerts.

Designed for the "Agentic Economy," Z3GION allows users to interact with their environment hands-free, seamlessly switching between cloud and offline AI models to ensure reliable safety anywhere, anytime.

✨ Core Features
🗣️ Real-Time Virtual Assistant: Multimodal voice-controlled agent that describes surroundings, people, colors, and lighting.

🛑 Close Proximity Alerts: Actively calculates approach velocity to warn users of physical obstacles using auditory and tactile feedback.

🌐 Dynamic Model Switching: Seamlessly toggles between high-powered pre-trained models (Gemini 2.0 Flash), custom-trained models, and on-device offline models when internet connectivity drops.

🗺️ Advanced Navigation: Integrates Google Maps API for real-time, location-based route guidance.

📄 Multilingual OCR: Reads signs, documents, currency, and handwritten notes aloud in multiple languages.

💾 Secure Chat History: Stores navigation logs and prompt history locally for quick recall and user privacy.

🛠️ System Architecture & Tech Stack
Frontend (Mobile Application)
Language: Kotlin

Capabilities: Cross-platform compatibility, real-time camera framing, microphone access, and text-to-speech output.

Backend (Server & API Routing)
Framework: Python / Flask

Database: MongoDB (for user preferences and chat history storage)

Cloud & Edge: Deployed via AWS/GCP, with edge computing support via Raspberry Pi for low-latency processing.

AI & Machine Learning
Primary LLM: Gemini 2.0 Flash_Exp API (for low-latency multimodal reasoning).



🚀 Getting Started
Prerequisites
Android Studio (latest version)

Python 3.9+

API Keys for Google Gemini and Google Maps

Installation
1. Clone the Repository

Bash
git clone https://github.com/yourusername/Z3GION.git
cd Z3GION
2. Backend Setup (Flask)

Bash
cd backend
python -m venv venv
source venv/bin/activate  # On Windows use `venv\Scripts\activate`
pip install -r requirements.txt
3. Environment Variables
Create a .env file in the backend directory and add your API keys:

Code snippet
GEMINI_API_KEY=your_gemini_api_key_here
MAPS_API_KEY=your_google_maps_api_key_here
FLASK_ENV=development
4. Start the Server

Bash
flask run --host=0.0.0.0 --port=5000
5. Frontend Setup (Android)

Open the frontend folder in Android Studio.

Sync the Gradle project.

In strings.xml or your configuration file, update the base URL to point to your running Flask server.

Build and run the app on a physical Android device (camera and hardware sensors are required).

📱 Usage
Launch the App: Open Z3GION on your mobile device.

Voice Activation: Tap the screen or say the wake word to initiate a command.

Ask a Question: "Z3GION, what is written on the sign in front of me?" or "Navigate me to the nearest bus stop."

Offline Mode: If network connection is lost, the app will automatically fall back to the offline TensorFlow model for basic object detection and safety alerts.

🔮 Roadmap / What's Next
Smart City Integration: Connecting Z3GION with third-party city transit agents (Fetch.ai ecosystem) for real-time autonomous transport booking.

Hardware Wearable: Transitioning the camera module from the mobile device back to discrete smart glasses for truly hands-free scanning.

Enhanced Local Processing: Further optimizing the PyTorch mobile models to reduce battery consumption.

Vision & Detection: TensorFlow / PyTorch for custom offline image captioning and object detection.

Audio Processing: Google Speech-to-Text API & gTTS (or MaryTTS).
