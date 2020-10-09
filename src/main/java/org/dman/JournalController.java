package org.dman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JournalController {
    private static Double totalProfit = 0.;
    private static Double sum = 0.;
    private static String fromDateTime, toDateTime;
    private static final DatabaseHandler dbHandler = new DatabaseHandler();
    private static final Image imageDelete = new Image(JournalController.class.getResourceAsStream("assets/delete.png"));
    private static final Image imageEdit = new Image(JournalController.class.getResourceAsStream("assets/edit.png"));
    private static final Image imageWin = new Image(JournalController.class.getResourceAsStream("assets/win.png"));
    private static final Image imageRUB = new Image(JournalController.class.getResourceAsStream("assets/rub.png"));
    private static final Image imageUSD = new Image(JournalController.class.getResourceAsStream("assets/usd.png"));
    private static final Image imageEUR = new Image(JournalController.class.getResourceAsStream("assets/eur.png"));
    private static final Image imageBTC = new Image(JournalController.class.getResourceAsStream("assets/btc.png"));
    private static final Image imageETH = new Image(JournalController.class.getResourceAsStream("assets/eth.png"));
    private static final Image imageXRP = new Image(JournalController.class.getResourceAsStream("assets/xrp.png"));

    public static ImageView setGraphic(Image image, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        return imageView;
    }

    public static void setGraphic(Button target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void setGraphic(ToggleButton target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void setToday(DatePicker fromDate, DatePicker toDate, Button showSurebets) {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());
        showSurebets.getOnAction().handle(new ActionEvent());
    }

    public static void setYesterday(DatePicker fromDate, DatePicker toDate, Button showSurebets) {
        fromDate.setValue(LocalDate.now().minusDays(1));
        toDate.setValue(LocalDate.now().minusDays(1));
        showSurebets.getOnAction().handle(new ActionEvent());
    }

    public static void setWeek(DatePicker fromDate, DatePicker toDate, Button showSurebets) {
        fromDate.setValue(LocalDate.now().minusWeeks(1));
        toDate.setValue(LocalDate.now());
        showSurebets.getOnAction().handle(new ActionEvent());
    }

    public static void setMonth(DatePicker fromDate, DatePicker toDate, Button showSurebets) {
        fromDate.setValue(LocalDate.now().minusMonths(1));
        toDate.setValue(LocalDate.now());
        showSurebets.getOnAction().handle(new ActionEvent());
    }

    public static AnchorPane createItem(String dateTime, String eventName, String percentage, String Profit, String firstValute, String secondValute, String thirdValute,
                                        String firstBookmaker, String secondBookmaker, String firstCoefficient, String secondCoefficient, String completeCoefficient,
                                        String firstAmount, String secondAmount, String requiredAmount, String firstWin, String secondWin, String newFirstProfit, String newSecondProfit,
                                        String firstOrSecond, String firstIssue, String secondIssue, ListView<AnchorPane> panelScroll, Label totalProfitLabel, Label averageSurebetPercentageLabel) {
        Label dateTimeLabel = new Label(dateTime);
        dateTimeLabel.setLayoutX(5);
        dateTimeLabel.setTextFill(Color.WHITE);

        AnchorPane dateTimePane = new AnchorPane();
        dateTimePane.setStyle("-fx-background-color: #6f4f29");
        dateTimePane.setPrefHeight(15);
        dateTimePane.setPrefWidth(120);
        dateTimePane.getChildren().add(dateTimeLabel);

        Label eventNameLabel = new Label(eventName);
        eventNameLabel.setLayoutX(125);
        eventNameLabel.setTextFill(Color.WHITE);

        Label percentageLabel = new Label(percentage);
        percentageLabel.setLayoutX(45);
        percentageLabel.setLayoutY(27);
        sum += Double.parseDouble(percentage);
        if (Double.parseDouble(percentage) >= 0)
            percentageLabel.setTextFill(Color.LIME);
        else
            percentageLabel.setTextFill(Color.RED);

        Label sign = new Label("%");
        sign.setLayoutX(90);
        sign.setLayoutY(27);
        if (Double.parseDouble(percentage) >= 0)
            sign.setTextFill(Color.LIME);
        else
            sign.setTextFill(Color.RED);

        Label ProfitLabel = new Label(Profit);
        totalProfit += Double.parseDouble(Profit);
        ProfitLabel.setLayoutX(45);
        ProfitLabel.setLayoutY(62);
        if (Double.parseDouble(Profit) >= 0)
            ProfitLabel.setTextFill(Color.LIME);
        else
            ProfitLabel.setTextFill(Color.RED);

        ImageView thirdValuteImage = new ImageView();
        switch (thirdValute) {
            case "RUB": {
                thirdValuteImage = setGraphic(imageRUB, 85, 60);
            }
            case "USD": {
                thirdValuteImage = setGraphic(imageUSD, 85, 60);
            }
            case "EUR": {
                thirdValuteImage = setGraphic(imageEUR, 85, 60);
            }
            case "BTC": {
                thirdValuteImage = setGraphic(imageBTC, 85, 60);
            }
            case "ETH": {
                thirdValuteImage = setGraphic(imageETH, 85, 60);
            }
            case "XRP": {
                thirdValuteImage = setGraphic(imageXRP, 85, 60);
            }
        }

        Label firstBookmakerLabel = new Label(firstBookmaker);
        firstBookmakerLabel.setLayoutX(115);
        firstBookmakerLabel.setLayoutY(27);
        firstBookmakerLabel.setTextFill(Color.WHITE);

        Label secondBookmakerLabel = new Label(secondBookmaker);
        secondBookmakerLabel.setLayoutX(115);
        secondBookmakerLabel.setLayoutY(62);
        secondBookmakerLabel.setTextFill(Color.WHITE);

        Label firstCoefficientLabel  = new Label("Коэфф:");
        firstCoefficientLabel.setLayoutX(160);
        firstCoefficientLabel.setLayoutY(27);
        firstCoefficientLabel.setTextFill(Color.WHITE);

        Label SecondCoefficientLabel  = new Label("Коэфф:");
        SecondCoefficientLabel.setLayoutX(160);
        SecondCoefficientLabel.setLayoutY(62);
        SecondCoefficientLabel.setTextFill(Color.WHITE);

        Label firstCoefficientValueLabel = new Label(firstCoefficient);
        firstCoefficientValueLabel.setLayoutX(205);
        firstCoefficientValueLabel.setLayoutY(27);
        firstCoefficientValueLabel.setTextFill(Color.WHITE);

        Label secondCoefficientValueLabel = new Label(secondCoefficient);
        secondCoefficientValueLabel.setLayoutX(205);
        secondCoefficientValueLabel.setLayoutY(62);
        secondCoefficientValueLabel.setTextFill(Color.WHITE);

        Label firstAmountLabel = new Label("Ставка:");
        firstAmountLabel.setLayoutX(240);
        firstAmountLabel.setLayoutY(27);
        firstAmountLabel.setTextFill(Color.WHITE);

        Label secondAmountLabel = new Label("Ставка:");
        secondAmountLabel.setLayoutX(240);
        secondAmountLabel.setLayoutY(62);
        secondAmountLabel.setTextFill(Color.WHITE);

        Label firstAmountValueLabel = new Label(firstAmount);
        firstAmountValueLabel.setLayoutX(285);
        firstAmountValueLabel.setLayoutY(27);
        firstAmountValueLabel.setTextFill(Color.WHITE);

        Label secondAmountValueLabel = new Label(secondAmount);
        secondAmountValueLabel.setLayoutX(285);
        secondAmountValueLabel.setLayoutY(62);
        secondAmountValueLabel.setTextFill(Color.WHITE);

        Label firstWinLabel = new Label("Выигрыш:");
        firstWinLabel.setLayoutX(325);
        firstWinLabel.setLayoutY(27);
        firstWinLabel.setTextFill(Color.WHITE);

        Label secondWinLabel = new Label("Выигрыш:");
        secondWinLabel.setLayoutX(325);
        secondWinLabel.setLayoutY(62);
        secondWinLabel.setTextFill(Color.WHITE);

        Label firstWinValueLabel = new Label(firstWin);
        firstWinValueLabel.setLayoutX(385);
        firstWinValueLabel.setLayoutY(27);
        int index = firstWinValueLabel.getText().indexOf('(');
        if (index != -1) {
            StringBuilder sb = new StringBuilder(firstWinValueLabel.getText());
            sb.delete(0, index + 1);
            sb.delete(sb.length() - 1, sb.length());
            firstWinValueLabel.setText(sb.toString());
        }
        if (Double.parseDouble(firstWinValueLabel.getText()) >= 0)
            firstWinValueLabel.setTextFill(Color.LIME);
        else
            firstWinValueLabel.setTextFill(Color.RED);
        if (firstOrSecond.equals("1")) {
            firstWinLabel.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
            firstWinValueLabel.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        }

        ImageView firstValuteImage = new ImageView();
        switch (firstValute) {
            case "RUB": {
                firstValuteImage = setGraphic(imageRUB, 430, 27);
            }
            case "USD": {
                firstValuteImage = setGraphic(imageUSD, 430, 27);
            }
            case "EUR": {
                firstValuteImage = setGraphic(imageEUR, 430, 27);
            }
            case "BTC": {
                firstValuteImage = setGraphic(imageBTC, 430, 27);
            }
            case "ETH": {
                firstValuteImage = setGraphic(imageETH, 430, 27);
            }
            case "XRP": {
                firstValuteImage = setGraphic(imageXRP, 430, 27);
            }
        }

        Label secondWinValueLabel = new Label(secondWin);
        secondWinValueLabel.setLayoutX(385);
        secondWinValueLabel.setLayoutY(62);
        index = secondWinValueLabel.getText().indexOf('(');
        if (index != -1) {
            StringBuilder sb = new StringBuilder(secondWinValueLabel.getText());
            sb.delete(0, index + 1);
            sb.delete(sb.length() - 1, sb.length());
            secondWinValueLabel.setText(sb.toString());
        }
        if (Double.parseDouble(secondWinValueLabel.getText()) >= 0)
            secondWinValueLabel.setTextFill(Color.LIME);
        else
            secondWinValueLabel.setTextFill(Color.RED);
        if (firstOrSecond.equals("0")) {
            secondWinLabel.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
            secondWinValueLabel.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        }
        ImageView secondValuteImage = new ImageView();
        switch (secondValute) {
            case "RUB": {
                secondValuteImage = setGraphic(imageRUB, 430, 60);
            }
            case "USD": {
                secondValuteImage = setGraphic(imageUSD, 430, 60);
            }
            case "EUR": {
                secondValuteImage = setGraphic(imageEUR, 430, 60);
            }
            case "BTC": {
                secondValuteImage = setGraphic(imageBTC, 430, 60);
            }
            case "ETH": {
                secondValuteImage = setGraphic(imageETH, 430, 60);
            }
            case "XRP": {
                secondValuteImage = setGraphic(imageXRP, 430, 60);
            }
        }

        ToggleButton firstWon = new ToggleButton();
        Tooltip firstWinedEventTooltip = new Tooltip("Выиграло первое плечо");
        firstWon.setLayoutX(455);
        firstWon.setLayoutY(20);
        firstWon.setPrefHeight(30);
        firstWon.setPrefWidth(30);
        firstWon.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        setGraphic(firstWon, imageWin);
        firstWon.setTooltip(firstWinedEventTooltip);
        firstWon.setOnAction(event -> {
            dbHandler.editWinner(dateTime, "0", firstWin);
            update(panelScroll, totalProfitLabel, averageSurebetPercentageLabel);
        });

        ToggleButton secondWon = new ToggleButton();
        Tooltip secondWinedEventTooltip = new Tooltip("Выиграло второе плечо");
        secondWon.setLayoutX(455);
        secondWon.setLayoutY(55);
        secondWon.setPrefHeight(30);
        secondWon.setPrefWidth(30);
        secondWon.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        setGraphic(secondWon, imageWin);
        secondWon.setTooltip(secondWinedEventTooltip);
        secondWon.setOnAction(event -> {
            dbHandler.editWinner(dateTime, "1", secondWin);
            update(panelScroll, totalProfitLabel, averageSurebetPercentageLabel);
        });

        Label firstIssueLabel = new Label(firstIssue);
        firstIssueLabel.setLayoutX(500);
        firstIssueLabel.setLayoutY(27);
        firstIssueLabel.setTextFill(Color.WHITE);

        Label secondIssueLabel = new Label(secondIssue);
        secondIssueLabel.setLayoutX(500);
        secondIssueLabel.setLayoutY(62);
        secondIssueLabel.setTextFill(Color.WHITE);

        Button delete = new Button();
        Tooltip deleteTooltip = new Tooltip("Удалить вилку из журнала");
        delete.setTooltip(deleteTooltip);
        delete.setLayoutX(5);
        delete.setLayoutY(20);
        delete.setPrefHeight(30);
        delete.setPrefWidth(30);
        delete.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        setGraphic(delete, imageDelete);
        delete.setOnAction(event -> {
            dbHandler.deleteSurebet(dateTime);
            update(panelScroll, totalProfitLabel, averageSurebetPercentageLabel);
        });

        Button rename = new Button();
        Tooltip renameTooltip = new Tooltip("изменить вилку");
        rename.setTooltip(renameTooltip);
        rename.setLayoutX(5);
        rename.setLayoutY(55);
        rename.setPrefHeight(30);
        rename.setPrefWidth(30);
        rename.getStylesheets().addAll(JournalController.class.getResource("styles.css").toExternalForm());
        setGraphic(rename, imageEdit);
        rename.setOnAction(event -> editBet(dateTime, eventName, firstBookmaker, secondBookmaker, firstCoefficient, secondCoefficient, completeCoefficient,
                firstAmount, secondAmount, requiredAmount, firstWin, secondWin, newFirstProfit, newSecondProfit, firstIssue, secondIssue, panelScroll, totalProfitLabel, averageSurebetPercentageLabel));

        AnchorPane PaneOfSurebets = new AnchorPane();
        PaneOfSurebets.setStyle("-fx-background-color: #252525");
        PaneOfSurebets.setPrefHeight(90);
        PaneOfSurebets.setPrefWidth(570);
        PaneOfSurebets.getChildren().addAll(dateTimePane, delete, rename, eventNameLabel, percentageLabel, sign, ProfitLabel, thirdValuteImage,
                firstBookmakerLabel, secondBookmakerLabel, firstCoefficientLabel, SecondCoefficientLabel, firstCoefficientValueLabel,
                secondCoefficientValueLabel, firstAmountLabel, secondAmountLabel, firstAmountValueLabel, secondAmountValueLabel, firstWinLabel, secondWinLabel,
                firstWinValueLabel, secondWinValueLabel, firstValuteImage, secondValuteImage, firstIssueLabel, secondIssueLabel, firstWon, secondWon);

        return PaneOfSurebets;
    }

    public static void editBet(String dateTime, String eventName, String firstBookmaker, String secondBookmaker, String firstCoefficient, String secondCoefficient, String completeCoefficient,
                               String firstAmount, String secondAmount, String requiredAmount, String firstWin, String secondWin, String newFirstProfit, String newSecondProfit,
                               String firstIssue, String secondIssue, ListView<AnchorPane> panelScroll, Label totalProfitLabel, Label averageSurebetPercentageLabel) {
        TextField nameEventField = new TextField(eventName);
        nameEventField.setLayoutX(165);
        nameEventField.setLayoutY(15);
        nameEventField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField firstBookmakerField = new TextField(firstBookmaker);
        firstBookmakerField.setLayoutX(165);
        firstBookmakerField.setLayoutY(55);
        firstBookmakerField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField secondBookmakerField = new TextField(secondBookmaker);
        secondBookmakerField.setLayoutX(165);
        secondBookmakerField.setLayoutY(95);
        secondBookmakerField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField firstCoefficientField = new TextField(firstCoefficient);
        firstCoefficientField.setLayoutX(165);
        firstCoefficientField.setLayoutY(135);
        firstCoefficientField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField secondCoefficientField = new TextField(secondCoefficient);
        secondCoefficientField.setLayoutX(165);
        secondCoefficientField.setLayoutY(175);
        secondCoefficientField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField completeCoefficientField = new TextField(completeCoefficient);
        completeCoefficientField.setLayoutX(165);
        completeCoefficientField.setLayoutY(180);
        completeCoefficientField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField firstAmountField = new TextField(firstAmount);
        firstAmountField.setLayoutX(165);
        firstAmountField.setLayoutY(215);
        firstAmountField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField secondAmountField = new TextField(secondAmount);
        secondAmountField.setLayoutX(165);
        secondAmountField.setLayoutY(255);
        secondAmountField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField requiredAmountField = new TextField(requiredAmount);
        requiredAmountField.setLayoutX(165);
        requiredAmountField.setLayoutY(260);
        requiredAmountField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField firstWinField = new TextField(firstWin);
        firstWinField.setLayoutX(165);
        firstWinField.setLayoutY(295);
        firstWinField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField secondWinField = new TextField(secondWin);
        secondWinField.setLayoutX(165);
        secondWinField.setLayoutY(335);
        secondWinField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField newFirstProfitField = new TextField(newFirstProfit);
        newFirstProfitField.setLayoutX(165);
        newFirstProfitField.setLayoutY(300);
        newFirstProfitField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField newSecondProfitField = new TextField(newSecondProfit);
        newSecondProfitField.setLayoutX(165);
        newSecondProfitField.setLayoutY(340);
        newSecondProfitField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField firstIssueField = new TextField(firstIssue);
        firstIssueField.setLayoutX(165);
        firstIssueField.setLayoutY(375);
        firstIssueField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        TextField secondIssueField = new TextField(secondIssue);
        secondIssueField.setLayoutX(165);
        secondIssueField.setLayoutY(415);
        secondIssueField.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE; -fx-text-fill: WHITE;");

        Label nameEventLabel = new Label("Название собьтия:");
        nameEventLabel.setTextFill(Color.WHITE);
        nameEventLabel.setLayoutX(5);
        nameEventLabel.setLayoutY(15);
        nameEventLabel.setStyle("-fx-background-color: #252525");

        Label firstBookmakerLabel = new Label("Первая БК:");
        firstBookmakerLabel.setTextFill(Color.WHITE);
        firstBookmakerLabel.setLayoutX(5);
        firstBookmakerLabel.setLayoutY(55);
        firstBookmakerLabel.setStyle("-fx-background-color: #252525");

        Label secondBookmakerLabel = new Label("Вторая БК:");
        secondBookmakerLabel.setTextFill(Color.WHITE);
        secondBookmakerLabel.setLayoutX(5);
        secondBookmakerLabel.setLayoutY(95);
        secondBookmakerLabel.setStyle("-fx-background-color: #252525");

        Label firstCoefficientLabel = new Label("Первый коэффициент:");
        firstCoefficientLabel.setTextFill(Color.WHITE);
        firstCoefficientLabel.setLayoutX(5);
        firstCoefficientLabel.setLayoutY(135);
        firstCoefficientLabel.setStyle("-fx-background-color: #252525");

        Label secondCoefficientLabel = new Label("Второй коэффициент:");
        secondCoefficientLabel.setTextFill(Color.WHITE);
        secondCoefficientLabel.setLayoutX(5);
        secondCoefficientLabel.setLayoutY(175);
        secondCoefficientLabel.setStyle("-fx-background-color: #252525");

        Label completeCoefficientLabel = new Label("Коээфициента для докрытия:");
        completeCoefficientLabel.setTextFill(Color.WHITE);
        completeCoefficientLabel.setLayoutX(5);
        completeCoefficientLabel.setLayoutY(175);
        completeCoefficientLabel.setStyle("-fx-background-color: #252525");

        Label firstAmountLabel = new Label("Сумма первого плеча:");
        firstAmountLabel.setTextFill(Color.WHITE);
        firstAmountLabel.setLayoutX(5);
        firstAmountLabel.setLayoutY(215);
        firstAmountLabel.setStyle("-fx-background-color: #252525");

        Label secondAmountLabel = new Label("Сумма второго плеча:");
        secondAmountLabel.setTextFill(Color.WHITE);
        secondAmountLabel.setLayoutX(5);
        secondAmountLabel.setLayoutY(255);
        secondAmountLabel.setStyle("-fx-background-color: #252525");

        Label requiredAmountLabel = new Label("Сумма докрытия:");
        requiredAmountLabel.setTextFill(Color.WHITE);
        requiredAmountLabel.setLayoutX(5);
        requiredAmountLabel.setLayoutY(255);
        requiredAmountLabel.setStyle("-fx-background-color: #252525");

        Label firstWinLabel = new Label("Выигрыш с первого плеча:");
        firstWinLabel.setTextFill(Color.WHITE);
        firstWinLabel.setLayoutX(5);
        firstWinLabel.setLayoutY(295);
        firstWinLabel.setStyle("-fx-background-color: #252525");

        Label secondWinLabel = new Label("Выигрыш со второго плеча:");
        secondWinLabel.setTextFill(Color.WHITE);
        secondWinLabel.setLayoutX(5);
        secondWinLabel.setLayoutY(335);
        secondWinLabel.setStyle("-fx-background-color: #252525");

        Label newFirstProfitLabel = new Label("Выигрыш с первого плеча (докрытие):");
        newFirstProfitLabel.setTextFill(Color.WHITE);
        newFirstProfitLabel.setLayoutX(5);
        newFirstProfitLabel.setLayoutY(295);
        newFirstProfitLabel.setStyle("-fx-background-color: #252525");

        Label newSecondProfitLabel = new Label("Выигрыш со второго плеча (докрытие):");
        newSecondProfitLabel.setTextFill(Color.WHITE);
        newSecondProfitLabel.setLayoutX(5);
        newSecondProfitLabel.setLayoutY(335);
        newSecondProfitLabel.setStyle("-fx-background-color: #252525");

        Label firstIssueLabel = new Label("Первый исход:");
        firstIssueLabel.setTextFill(Color.WHITE);
        firstIssueLabel.setLayoutX(5);
        firstIssueLabel.setLayoutY(375);
        firstIssueLabel.setStyle("-fx-background-color: #252525");

        Label secondIssueLabel = new Label("Второй исход:");
        secondIssueLabel.setTextFill(Color.WHITE);
        secondIssueLabel.setLayoutX(5);
        secondIssueLabel.setLayoutY(415);
        secondIssueLabel.setStyle("-fx-background-color: #252525");

        Button save = new Button("Сохранить");
        save.setTextFill(Color.WHITE);
        save.setLayoutX(227);
        save.setLayoutY(450);
        save.setStyle("-fx-background-color: #252525; -fx-border-color: WHITE;");
        save.setOnAction(event -> {
            dbHandler.editSurebet(dateTime, nameEventField.getText(), firstBookmakerField.getText(), secondBookmakerField.getText(), completeCoefficientField.getText(),
                    firstCoefficientField.getText(), secondCoefficientField.getText(), requiredAmountField.getText(), firstAmountField.getText(), secondAmountField.getText(),
                    firstWinField.getText(), secondWinField.getText(), newFirstProfitField.getText(), newSecondProfitField.getText(), firstIssueField.getText(), secondIssueField.getText());
            update(panelScroll, totalProfitLabel, averageSurebetPercentageLabel);
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        });

        AnchorPane EditPane = new AnchorPane();
        EditPane.setStyle("-fx-background-color: #252525");
        EditPane.setPrefHeight(490);
        EditPane.setPrefWidth(530);
        EditPane.getChildren().addAll(nameEventField, firstBookmakerField, secondBookmakerField, firstCoefficientField, secondCoefficientField,
                firstAmountField, secondAmountField, firstWinField, secondWinField, firstIssueField, secondIssueField, nameEventLabel, firstBookmakerLabel,
                secondBookmakerLabel, firstCoefficientLabel, secondCoefficientLabel, firstAmountLabel, secondAmountLabel, firstWinLabel, secondWinLabel, firstIssueLabel, secondIssueLabel, save);

        Stage editWindow = new Stage();
        editWindow.initOwner(panelScroll.getScene().getWindow());
        editWindow.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(EditPane);
            editWindow.setScene(scene);
            editWindow.setTitle("Редактирование вилки");
            editWindow.show();
    }

    public static void update(ListView<AnchorPane> panelScroll, Label totalProfitLabel, Label averageSurebetPercentageLabel) {
        totalProfit = 0.; sum = 0.;
        int counter = 0;
        ObservableList<AnchorPane> Surebets = FXCollections.observableArrayList();
        ResultSet ListOfSurebets = dbHandler.getSurebets(fromDateTime, toDateTime);

        try {
            while(ListOfSurebets.next()){
                counter++;
                Surebets.add(createItem(ListOfSurebets.getString("dateTime"),  ListOfSurebets.getString("eventName"),  ListOfSurebets.getString("percentage"),
                        ListOfSurebets.getString("Profit"), ListOfSurebets.getString("firstValute"),  ListOfSurebets.getString("secondValute"),
                        ListOfSurebets.getString("thirdValute"),  ListOfSurebets.getString("firstBookmaker"), ListOfSurebets.getString("secondBookmaker"),
                        ListOfSurebets.getString("firstCoefficient"),  ListOfSurebets.getString("secondCoefficient"), ListOfSurebets.getString("completeCoefficient"),
                        ListOfSurebets.getString("firstAmount"), ListOfSurebets.getString("secondAmount"), ListOfSurebets.getString("requiredAmount"),
                        ListOfSurebets.getString("firstWin"), ListOfSurebets.getString("secondWin"), ListOfSurebets.getString("newFirstProfit"),
                        ListOfSurebets.getString("newSecondProfit"), ListOfSurebets.getString("firstOrSecond"),  ListOfSurebets.getString("firstIssue"),
                        ListOfSurebets.getString("secondIssue"), panelScroll, totalProfitLabel, averageSurebetPercentageLabel));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        totalProfitLabel.setText(String.format("%.0f", totalProfit));
        if (counter != 0) {
            Double averageSurebetPercentage = sum / counter;
            averageSurebetPercentageLabel.setText(String.format("%.2f", averageSurebetPercentage));
        }
        else averageSurebetPercentageLabel.setText("0");
        panelScroll.setItems(Surebets);
    }

    @FXML
    private Button showSurebets;

    @FXML
    private ListView<AnchorPane> panelScroll;

    @FXML
    private DatePicker fromDate, toDate;

    @FXML
    private Label today, yesterday, week, month, totalProfitLabel, averageSurebetPercentageLabel;

    @FXML
    void initialize() {
        today.setOnMouseClicked(mouseEvent -> setToday(fromDate, toDate, showSurebets));
        yesterday.setOnMouseClicked(mouseEvent -> setYesterday(fromDate, toDate, showSurebets));
        week.setOnMouseClicked(mouseEvent -> setWeek(fromDate, toDate, showSurebets));
        month.setOnMouseClicked(mouseEvent -> setMonth(fromDate, toDate, showSurebets));
        showSurebets.setOnAction(event -> {
            fromDateTime = fromDate.getValue().minusDays(1).toString() + "T23:59:59.999999999";
            toDateTime = toDate.getValue().plusDays(1).toString();
            update(panelScroll, totalProfitLabel, averageSurebetPercentageLabel);
        });
    }
}