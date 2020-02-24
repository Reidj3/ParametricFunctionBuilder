package ru.reidj.pfb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.reidj.pfb.controller.Controller;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Controller.prepair();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setStage(stage);

        stage.setTitle("Проект Поддымова Сергея и Тенькова Алексея");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
