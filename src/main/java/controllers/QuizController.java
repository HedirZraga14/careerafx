package controllers;

import entities.Question;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML private VBox questionsContainer;
    @FXML private Text timerText;
    @FXML private Button submitButton;

    private List<Question> questions;
    private List<ToggleGroup> answerGroups;
    private int score = 0;
    private int timeLeft = 30; // en secondes
    private Timeline timeline;

    @FXML
    public void initialize() {
        questions = loadQuestions();
        answerGroups = new ArrayList<>();
    }

    @FXML private Button startQuizButton;  // Ajoute cette ligne pour référencer le bouton

    @FXML
    private void startQuiz() {
        questionsContainer.setVisible(true);
        submitButton.setDisable(false);

        // Cacher le bouton Démarrer
        startQuizButton.setVisible(false);

        // Démarrer le timer
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        for (Question question : questions) {
            VBox questionBox = new VBox(5);
            Text questionText = new Text(question.getQuestion());
            questionBox.getChildren().add(questionText);

            ToggleGroup group = new ToggleGroup();
            answerGroups.add(group);

            for (String option : question.getOptions()) {
                RadioButton rb = new RadioButton(option);
                rb.setToggleGroup(group);
                questionBox.getChildren().add(rb);
            }

            questionsContainer.getChildren().add(questionBox);
        }
    }

    private void updateTimer() {
        if (timeLeft > 0) {
            timeLeft--;
            timerText.setText("Temps restant: " + timeLeft);
        } else {
            timeline.stop();
            submitQuiz(); // auto-submit
        }
    }

    @FXML
    private void submitQuiz() {
        if (timeline != null) timeline.stop();

        score = 0;

        for (int i = 0; i < questions.size(); i++) {
            ToggleGroup group = answerGroups.get(i);
            Question question = questions.get(i);

            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null && selected.getText().equals(question.getOptions()[question.getCorrectAnswerIndex()])) {
                score++;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Votre score est : " + score + "/" + questions.size());
        alert.showAndWait();
    }

    private List<Question> loadQuestions() {
        List<Question> questionList = new ArrayList<>();

        // Créer des questions d'exemple
        questionList.add(new Question("Quel est le capital de la France ?", new String[]{"Paris", "Londres", "Berlin", "Madrid"}, 0));
        questionList.add(new Question("Quelle est la couleur du ciel ?", new String[]{"Bleu", "Rouge", "Vert", "Jaune"}, 0));
        questionList.add(new Question("Quel est l'élément chimique dont le symbole est 'O' ?", new String[]{"Oxygène", "Or", "Ozone", "Osmium"}, 0));
        questionList.add(new Question("Combien de continents y a-t-il sur Terre ?", new String[]{"5", "6", "7", "8"}, 2));
        questionList.add(new Question("Quelle est la vitesse de la lumière ?", new String[]{"300 000 km/s", "150 000 km/s", "1 000 000 km/s", "500 000 km/s"}, 0));

        return questionList;
    }
}
