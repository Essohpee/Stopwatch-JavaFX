package com.essohpee.stopwatch;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StopWatchController implements Initializable {

    Timeline timeline;
    LocalTime time = LocalTime.parse("00:00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private JFXButton startTimerButton;
    @FXML
    private JFXButton pauseTimerButton;
    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void incrementTime() {
        time = time.plusSeconds(1);
        timerLabel.setText(time.format(dtf));
    }

    @FXML
    private void startTimer(ActionEvent event) {
        timeline.play();
        startTimerButton.setDisable(true);
    }

    @FXML
    private void pauseTimer(ActionEvent event) {
        if (timeline.getStatus().equals(Animation.Status.PAUSED)) {
            timeline.play();
            pauseTimerButton.setText("Pause");
        } else if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.pause();
            pauseTimerButton.setText("Continue");
        }
    }

    @FXML
    private void endTimer(ActionEvent event) {
        if (startTimerButton.isDisable()) {
            timeline.stop();
            startTimerButton.setDisable(false);
            time = LocalTime.parse("00:00:00");
            timerLabel.setText(time.format(dtf));
        }
    }
}