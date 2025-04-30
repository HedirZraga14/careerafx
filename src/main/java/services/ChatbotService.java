package services;

public class ChatbotService {

    // A simple method to return responses based on user input
    public String getResponse(String userInput) {
        // Convert user input to lowercase to make it case-insensitive
        userInput = userInput.toLowerCase();

        // Respond to various inputs
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hi there! How can I assist you today?";
        } else if (userInput.contains("help")) {
            return "I can help you with various tasks. Just ask!";
        } else if (userInput.contains("how are you")) {
            return "I'm just a chatbot, but I'm doing great! How about you?";
        } else if (userInput.contains("your name")) {
            return "I'm your friendly chatbot assistant!";
        } else if (userInput.contains("bye") || userInput.contains("goodbye")) {
            return "Goodbye! Have a great day!";
        } else if (userInput.contains("thank you")) {
            return "You're welcome! Let me know if you need anything else.";
        } else if (userInput.contains("what is your purpose")) {
            return "My purpose is to assist you with your questions and tasks.";
        } else if (userInput.contains("joke")) {
            return "Why don't skeletons fight each other? They don't have the guts!";
        } else if (userInput.contains("weather")) {
            return "I can't check the weather right now, but you can check it on your phone or online!";
        } else if (userInput.contains("time")) {
            return "I don't have access to real-time data, but you can check the time on your device!";
        } else if (userInput.contains("information") || userInput.contains("details")) {
            return "I can help with specific information! What do you want to know?";
        } else {
            return "I'm sorry, I didn't understand that. Can you rephrase?";
        }
    }
}
