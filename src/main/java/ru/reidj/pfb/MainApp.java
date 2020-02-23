package ru.reidj.pfb;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Group root = new Group();
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
        for (int i = -1000; i < 1000; i++) {
            root.getChildren().addAll(new Circle(600 + i, 300 - i, 1));
        }
        stage.setResizable(false);
        stage.setHeight(600);
        stage.setWidth(1200);
        stage.setTitle("Проект Поддымова Сергея и Тенькова Алексея");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
