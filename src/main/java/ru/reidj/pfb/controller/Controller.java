package ru.reidj.pfb.controller;

import groovy.lang.GroovyShell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputX;

    @FXML
    private TextField inputY;

    @FXML
    private TextField startT;

    @FXML
    private TextField endT;

    @FXML
    private Button build;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static final Group root = new Group();

    public static void prepair(){
        root.addEventFilter(KeyEvent.ANY, e -> {
            if (e.getCode().equals(KeyCode.W)) {
                root.setTranslateY(root.getTranslateY() + 100);
            }
            if (e.getCode() == KeyCode.S) {
                root.setTranslateY(root.getTranslateY() - 100);
            }
            if (e.getCode() == KeyCode.A) {
                root.setTranslateX(root.getTranslateX() + 100);
            }
            if (e.getCode() == KeyCode.D) {
                root.setTranslateX(root.getTranslateX() - 100);
            }
        });
        Font font = new Font(30);
        for (int i = -100; i < 100; i++) {
            TextArea textAreaX = new TextArea("" + i);
            textAreaX.setFont(font);
            textAreaX.setTranslateX(i * 100 + 600);
            textAreaX.setTranslateY(300);
            textAreaX.setEditable(false);
            textAreaX.setMaxHeight(40);
            textAreaX.setMinHeight(40);
            textAreaX.setMinWidth(100);
            textAreaX.setMaxWidth(100);

            TextArea textAreaY = new TextArea("" + -i);
            textAreaY.setFont(font);
            textAreaY.setTranslateX(600);
            textAreaY.setTranslateY(i * 100 + 300);
            textAreaY.setEditable(false);
            textAreaY.setMinHeight(100);
            textAreaX.setMaxHeight(100);
            textAreaY.setMaxWidth(40);
            root.getChildren().addAll(textAreaX, textAreaY);
        }
        root.getChildren().addAll(new Line(600, 600, 600, 0), new Line(0, 300, 1200, 300));
    }

    @FXML
    void initialize() {
        build.setOnAction(event -> {
            if (inputX.getText().isEmpty() || inputY.getText().isEmpty() || startT.getText().isEmpty() || endT.getText().isEmpty())
                return;
            Platform.runLater(() -> {
                GroovyShell shell = new GroovyShell();

                shell.evaluate("" +
                        "cos = {double t -> Math.cos(Math.toRadians(t * 100))}\n" +
                        "sin = {double t -> Math.sin(Math.toRadians(t * 100))}\n" +
                        "exp = {double t -> Math.exp(Math.toRadians(t))}\n" +
                        "pi = Math.PI\n"
                );
                boolean first = true;
                double previousX = 0;
                double previousY = 0;
                for (double i = Double.parseDouble(startT.getText()) * 10; i < Double.parseDouble(endT.getText()) * 10; i++) {
                    shell.setVariable("t", i / 10);
                    if(first) {
                        previousX = ((double) shell.evaluate(inputX.getText()) +6) * 100;
                        previousY = - ((double) shell.evaluate(inputY.getText()) -3) * 100;
                        first = false;
                        continue;
                    }
                    double nowX = ((double) shell.evaluate(inputX.getText()) +6) * 100;
                    double nowY =  - ((double) shell.evaluate(inputY.getText()) -3) * 100;
                    root.getChildren().addAll(new Line(previousX, previousY, nowX, nowY));
                    previousX = nowX;
                    previousY = nowY;
                }
                stage.setResizable(false);
                stage.setHeight(600);
                stage.setWidth(1200);
                stage.setTitle("Проект Поддымова Сергея и Тенькова Алексея");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            });
        });
    }
}

