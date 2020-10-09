package org.dman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Arbitration Calculator " + "v0.2.2");
        primaryStage.setScene(new Scene(root, 660, 230));
        primaryStage.show();
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("assets/icon.png")));
    }

    public static void openJournal(Stage parent, Modality modality) throws IOException {
        Stage journalWindow = new Stage();
        journalWindow.initModality(modality);
        journalWindow.initOwner(parent);
        Parent root = FXMLLoader.load(Main.class.getResource("journal.fxml"));
        journalWindow.setScene(new Scene(root, 950, 280));
        journalWindow.setTitle("Статистика проставленных вилок");
        journalWindow.show();
        journalWindow.getIcons().add(new Image(Main.class.getResourceAsStream("assets/journalBlack.png")));
    }

    public static void openSettings(Stage parent, Modality modality) throws IOException {
        Stage settingsWindow = new Stage();
        settingsWindow.initModality(modality);
        settingsWindow.initOwner(parent);
        Parent root = FXMLLoader.load(Main.class.getResource("settings.fxml"));
        settingsWindow.setScene(new Scene(root, 750, 250));
        settingsWindow.setTitle("Настройки");
        settingsWindow.show();
        settingsWindow.getIcons().add(new Image(Main.class.getResourceAsStream("assets/settingsBlack.png")));
    }

    public static void message(Stage parent, Modality modality) throws IOException {
        Stage messageWindow = new Stage();
        messageWindow.initModality(modality);
        messageWindow.initOwner(parent);
        Parent root = FXMLLoader.load(Main.class.getResource("message.fxml"));
        messageWindow.setScene(new Scene(root, 400, 230));
        messageWindow.setTitle("Курсы успешно обновлены!");
        messageWindow.show();
        messageWindow.getIcons().add(new Image(Main.class.getResourceAsStream("assets/ok.png")));
    }
}

