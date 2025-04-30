package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ChatbotService;

public class ChatbotController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private ChatbotService chatbotService;

    public ChatbotController() {
        chatbotService = new ChatbotService(); // Initialize the chatbot service
    }

    @FXML
    private void initialize() {
        sendButton.setOnAction(e -> sendMessage());
    }


    // Method to handle the button click event
    @FXML
    private void sendMessage(ActionEvent event) {
        // Get the user input
        String message = userInput.getText();

        // Append the user message to the chat area
        if (!message.trim().isEmpty()) {
            chatArea.appendText("You: " + message + "\n");

            // Simulate a response from the chatbot (for demonstration purposes)
            chatArea.appendText("Chatbot: " + "Thank you for your message!" + "\n");

            // Clear the input field after sending the message
            userInput.clear();
        }
    }
    private void sendMessage() {
        String userMessage = userInput.getText();
        if (!userMessage.isEmpty()) {
            // Display user message in chat area
            chatArea.appendText("You: " + userMessage + "\n");
            // Get chatbot response and display it
            String botResponse = chatbotService.getResponse(userMessage);
            chatArea.appendText("Bot: " + botResponse + "\n");
            // Clear the input field
            userInput.clear();
        }
    }
}
