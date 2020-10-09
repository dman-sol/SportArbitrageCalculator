package org.dman;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

public class SettingsController {
    private static final Image imageOn = new Image(Controller.class.getResourceAsStream("assets/on.png"));
    private static final Image imageOff = new Image(Controller.class.getResourceAsStream("assets/off.png"));

    public static void setGraphic(ToggleButton target, Image image) {
        ImageView imageView = new ImageView(image);
        target.setGraphic(imageView);
    }

    public static void setFormat(MenuButton Valute, int idFormat) {
        switch (idFormat) {
            case -6: {
                Valute.setText("0.000001");
                break;
            }
            case -5: {
                Valute.setText("0.00001");
                break;
            }
            case -4: {
                Valute.setText("0.0001");
                break;
            }
            case -3: {
                Valute.setText("0.001");
                break;
            }
            case -2: {
                Valute.setText("0.01");
                break;
            }
            case -1: {
                Valute.setText("0.1");
                break;
            }
            case 1: {
                Valute.setText("1");
                break;
            }
            case 5: {
                Valute.setText("5");
                break;
            }
            case 10: {
                Valute.setText("10");
                break;
            }
            case 50: {
                Valute.setText("50");
                break;
            }
            case 100: {
                Valute.setText("100");
                break;
            }
            case 500: {
                Valute.setText("500");
                break;
            }
            case 1000: {
                Valute.setText("1000");
                break;
            }
        }
    }

    @FXML
    private Label labelUSD, labelEUR, labelBTC, labelETH, labelXRP;

    @FXML
    private ToggleButton toggleUSD, toggleEUR, toggleBTC, toggleETH, toggleXRP;

    @FXML
    private MenuButton roundingRUB, roundingUSD, roundingEUR, roundingBTC, roundingETH, roundingXRP;

    @FXML
    private MenuItem roundRUB1, roundRUB5, roundRUB10, roundRUB50, roundRUB100, roundRUB500, roundRUB1000,
            roundUSD1, roundUSD5, roundUSD10, roundUSD50, roundUSD100, roundUSD500, roundUSD1000,
            roundEUR1, roundEUR5, roundEUR10, roundEUR50, roundEUR100, roundEUR500, roundEUR1000,
            roundBTC0_000001, roundBTC0_00001, roundBTC0_0001, roundBTC0_001, roundBTC0_01, roundBTC0_1,
            roundETH0_000001, roundETH0_00001, roundETH0_0001, roundETH0_001, roundETH0_01, roundETH0_1, roundETH1,
            roundXRP0_000001, roundXRP0_00001, roundXRP0_0001, roundXRP0_001, roundXRP0_01, roundXRP0_1, roundXRP1,
            roundXRP5, roundXRP10, roundXRP50, roundXRP100, roundXRP500,roundXRP1000, exmo, binance;

    @FXML
    private SplitMenuButton getCurrency;

    @FXML
    private TextField editUSD, editEUR, editBTC, editETH, editXRP;

    @FXML
    void initialize() {
        setGraphic(toggleUSD, imageOn);
        setGraphic(toggleUSD, imageOn);
        setGraphic(toggleEUR, imageOn);
        setGraphic(toggleBTC, imageOn);
        setGraphic(toggleETH, imageOn);
        setGraphic(toggleXRP, imageOn);

        toggleUSD.setSelected(true);
        toggleEUR.setSelected(true);
        toggleBTC.setSelected(true);
        toggleETH.setSelected(true);
        toggleXRP.setSelected(true);

        toggleUSD.setOnAction(event -> {
            if (toggleUSD.isSelected()) {
                setGraphic(toggleUSD, imageOn);
                editUSD.setText(String.format(Locale.ROOT, "%.4f", Controller.getUSD()));
                labelUSD.setText("Получать автоматически");
                editUSD.setDisable(true);
            }
            else {
                editUSD.setDisable(false);
                setGraphic(toggleUSD, imageOff);
                labelUSD.setText("Курс введён вручную");
            }
        });
        toggleEUR.setOnAction(event -> {
            if (toggleEUR.isSelected()) {
                setGraphic(toggleEUR, imageOn);
                editEUR.setText(String.format(Locale.ROOT, "%.4f", Controller.getEUR()));
                labelEUR.setText("Получать автоматически");
                editEUR.setDisable(true);
            }
            else {
                editEUR.setDisable(false);
                setGraphic(toggleEUR, imageOff);
                labelEUR.setText("Курс введён вручную");
            }
        });
        toggleBTC.setOnAction(event -> {
            if (toggleBTC.isSelected()) {
                setGraphic(toggleBTC, imageOn);
                editBTC.setText(String.format(Locale.ROOT, "%.4f", Controller.getBTC()));
                labelBTC.setText("Получать автоматически");
                editBTC.setDisable(true);
            }
            else {
                editBTC.setDisable(false);
                setGraphic(toggleBTC, imageOff);
                labelBTC.setText("Курс введён вручную");
            }
        });
        toggleETH.setOnAction(event -> {
            if (toggleETH.isSelected()) {
                setGraphic(toggleETH, imageOn);
                editETH.setText(String.format(Locale.ROOT, "%.4f", Controller.getETH()));
                labelETH.setText("Получать автоматически");
                editETH.setDisable(true);
            }
            else {
                editETH.setDisable(false);
                setGraphic(toggleETH, imageOff);
                labelETH.setText("Курс введён вручную");
            }
        });
        toggleXRP.setOnAction(event -> {
            if (toggleXRP.isSelected()) {
                setGraphic(toggleXRP, imageOn);
                editXRP.setText(String.format(Locale.ROOT, "%.4f", Controller.getXRP()));
                labelXRP.setText("Получать автоматически");
                editXRP.setDisable(true);
            }
            else {
                editXRP.setDisable(false);
                setGraphic(toggleXRP, imageOff);
                labelXRP.setText("Курс введён вручную");
            }
        });

        editUSD.setText(String.format(Locale.ROOT, "%.4f", Controller.getUSD()));
        editEUR.setText(String.format(Locale.ROOT, "%.4f", Controller.getEUR()));
        editBTC.setText(String.format(Locale.ROOT, "%.4f", Controller.getBTC()));
        editETH.setText(String.format(Locale.ROOT, "%.4f", Controller.getETH()));
        editXRP.setText(String.format(Locale.ROOT, "%.4f", Controller.getXRP()));

        setFormat(roundingRUB, Controller.getFormatRUB());
        setFormat(roundingUSD, Controller.getFormatUSD());
        setFormat(roundingEUR, Controller.getFormatEUR());
        setFormat(roundingBTC, Controller.getFormatBTC());
        setFormat(roundingETH, Controller.getFormatETH());
        setFormat(roundingXRP, Controller.getFormatXRP());

        roundRUB1.setOnAction(event -> {
            roundingRUB.setText(roundRUB1.getText());
            Controller.setFormatRUB(1);
        });
        roundRUB5.setOnAction(event -> {
            roundingRUB.setText(roundRUB5.getText());
            Controller.setFormatRUB(5);
        });
        roundRUB10.setOnAction(event -> {
            roundingRUB.setText(roundRUB10.getText());
            Controller.setFormatRUB(10);
        });
        roundRUB50.setOnAction(event -> {
            roundingRUB.setText(roundRUB50.getText());
            Controller.setFormatRUB(50);
        });
        roundRUB100.setOnAction(event -> {
            roundingRUB.setText(roundRUB100.getText());
            Controller.setFormatRUB(100);
        });
        roundRUB500.setOnAction(event -> {
            roundingRUB.setText(roundRUB500.getText());
            Controller.setFormatRUB(500);
        });
        roundRUB1000.setOnAction(event -> {
            roundingRUB.setText(roundRUB1000.getText());
            Controller.setFormatRUB(1000);
        });

        roundUSD1.setOnAction(event -> {
            roundingUSD.setText(roundUSD1.getText());
            Controller.setFormatUSD(1);
        });
        roundUSD5.setOnAction(event -> {
            roundingUSD.setText(roundUSD5.getText());
            Controller.setFormatUSD(5);
        });
        roundUSD10.setOnAction(event -> {
            roundingUSD.setText(roundUSD10.getText());
            Controller.setFormatUSD(10);
        });
        roundUSD50.setOnAction(event -> {
            roundingUSD.setText(roundUSD50.getText());
            Controller.setFormatUSD(50);
        });
        roundUSD100.setOnAction(event -> {
            roundingUSD.setText(roundUSD100.getText());
            Controller.setFormatUSD(100);
        });
        roundUSD500.setOnAction(event -> {
            roundingUSD.setText(roundUSD500.getText());
            Controller.setFormatUSD(500);
        });
        roundUSD1000.setOnAction(event -> {
            roundingUSD.setText(roundUSD1000.getText());
            Controller.setFormatUSD(1000);
        });

        roundEUR1.setOnAction(event -> {
            roundingEUR.setText(roundEUR1.getText());
            Controller.setFormatEUR(1);
        });
        roundEUR5.setOnAction(event -> {
            roundingEUR.setText(roundEUR5.getText());
            Controller.setFormatEUR(5);
        });
        roundEUR10.setOnAction(event -> {
            roundingEUR.setText(roundEUR10.getText());
            Controller.setFormatEUR(10);
        });
        roundEUR50.setOnAction(event -> {
            roundingEUR.setText(roundEUR50.getText());
            Controller.setFormatEUR(50);
        });
        roundEUR100.setOnAction(event -> {
            roundingEUR.setText(roundEUR100.getText());
            Controller.setFormatEUR(100);
        });
        roundEUR500.setOnAction(event -> {
            roundingEUR.setText(roundEUR500.getText());
            Controller.setFormatEUR(500);
        });
        roundEUR1000.setOnAction(event -> {
            roundingEUR.setText(roundEUR1000.getText());
            Controller.setFormatEUR(1000);
        });

        roundBTC0_000001.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_000001.getText());
            Controller.setFormatBTC(-6);
        });
        roundBTC0_00001.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_00001.getText());
            Controller.setFormatBTC(-5);
        });
        roundBTC0_0001.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_0001.getText());
            Controller.setFormatBTC(-4);
        });
        roundBTC0_001.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_001.getText());
            Controller.setFormatBTC(-3);
        });
        roundBTC0_01.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_01.getText());
            Controller.setFormatBTC(-2);
        });
        roundBTC0_1.setOnAction(event -> {
            roundingBTC.setText(roundBTC0_1.getText());
            Controller.setFormatBTC(-1);
        });

        roundETH0_000001.setOnAction(event -> {
            roundingETH.setText(roundETH0_000001.getText());
            Controller.setFormatETH(-6);
        });
        roundETH0_00001.setOnAction(event -> {
            roundingETH.setText(roundETH0_00001.getText());
            Controller.setFormatETH(-5);
        });
        roundETH0_0001.setOnAction(event -> {
            roundingETH.setText(roundETH0_0001.getText());
            Controller.setFormatETH(-4);
        });
        roundETH0_001.setOnAction(event -> {
            roundingETH.setText(roundETH0_001.getText());
            Controller.setFormatETH(-3);
        });
        roundETH0_01.setOnAction(event -> {
            roundingETH.setText(roundETH0_01.getText());
            Controller.setFormatETH(-2);
        });
        roundETH0_1.setOnAction(event -> {
            roundingETH.setText(roundETH0_1.getText());
            Controller.setFormatETH(-1);
        });

        roundXRP0_000001.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_000001.getText());
            Controller.setFormatXRP(-6);
        });
        roundXRP0_00001.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_00001.getText());
            Controller.setFormatXRP(-5);
        });
        roundXRP0_0001.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_0001.getText());
            Controller.setFormatXRP(-4);
        });
        roundXRP0_001.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_001.getText());
            Controller.setFormatXRP(-3);
        });
        roundXRP0_01.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_01.getText());
            Controller.setFormatXRP(-2);
        });
        roundXRP0_1.setOnAction(event -> {
            roundingXRP.setText(roundXRP0_1.getText());
            Controller.setFormatXRP(-1);
        });
        roundXRP1.setOnAction(event -> {
            roundingXRP.setText(roundXRP1.getText());
            Controller.setFormatXRP(1);
        });
        roundXRP5.setOnAction(event -> {
            roundingXRP.setText(roundXRP5.getText());
            Controller.setFormatXRP(5);
        });
        roundXRP10.setOnAction(event -> {
            roundingXRP.setText(roundXRP10.getText());
            Controller.setFormatXRP(10);
        });
        roundXRP50.setOnAction(event -> {
            roundingXRP.setText(roundXRP50.getText());
            Controller.setFormatXRP(50);
        });
        roundXRP100.setOnAction(event -> {
            roundingXRP.setText(roundXRP100.getText());
            Controller.setFormatXRP(100);
        });
        roundXRP500.setOnAction(event -> {
            roundingXRP.setText(roundXRP500.getText());
            Controller.setFormatXRP(500);
        });
        roundXRP1000.setOnAction(event -> {
            roundingXRP.setText(roundXRP1000.getText());
            Controller.setFormatXRP(1000);
        });

        getCurrency.setOnAction(event -> {
            try {
                Controller.getCurrency("EXMO");
                editUSD.setText(String.format(Locale.ROOT, "%.4f", Controller.getUSD()));
                editEUR.setText(String.format(Locale.ROOT, "%.4f", Controller.getEUR()));
                editBTC.setText(String.format(Locale.ROOT, "%.4f", Controller.getBTC()));
                editETH.setText(String.format(Locale.ROOT, "%.4f", Controller.getETH()));
                editXRP.setText(String.format(Locale.ROOT, "%.4f", Controller.getXRP()));
                Main.message((Stage) getCurrency.getScene().getWindow(), Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                System.out.println("Ошибка при получении курсов валют");
                e.printStackTrace();
            }
        });

        exmo.setOnAction(event -> {
            getCurrency.setText(exmo.getText());
            try {
                Controller.getCurrency("EXMO");
                editUSD.setText(String.format(Locale.ROOT, "%.4f", Controller.getUSD()));
                editEUR.setText(String.format(Locale.ROOT, "%.4f", Controller.getEUR()));
                editBTC.setText(String.format(Locale.ROOT, "%.4f", Controller.getBTC()));
                editETH.setText(String.format(Locale.ROOT, "%.4f", Controller.getETH()));
                editXRP.setText(String.format(Locale.ROOT, "%.4f", Controller.getXRP()));
                Main.message((Stage) getCurrency.getScene().getWindow(), Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                System.out.println("Ошибка при получении курсов валют");
                e.printStackTrace();
            }
        });

        binance.setOnAction(event -> {
            getCurrency.setText(binance.getText());
            try {
                Controller.getCurrency("BINANCE");
                editUSD.setText(String.format(Locale.ROOT, "%.4f", Controller.getUSD()));
                editEUR.setText(String.format(Locale.ROOT, "%.4f", Controller.getEUR()));
                editBTC.setText(String.format(Locale.ROOT, "%.4f", Controller.getBTC()));
                editETH.setText(String.format(Locale.ROOT, "%.4f", Controller.getETH()));
                editXRP.setText(String.format(Locale.ROOT, "%.4f", Controller.getXRP()));
                Main.message((Stage) getCurrency.getScene().getWindow(), Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                System.out.println("Ошибка при получении курсов валют");
                e.printStackTrace();
            }
        });
    }
}