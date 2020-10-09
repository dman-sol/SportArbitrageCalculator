package org.dman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.Unirest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class Controller {
    private static String finalProfit;
    private static int formatRUB = 1 , formatUSD = 1, formatEUR = 1, formatBTC = -6, formatETH = -5,  formatXRP = -3;
    private static double c1 = 1, c2 = 1, c3 = 1, USD = 75, EUR = 88, BTC = 862060, ETH = 31400, XRP = 20.9, amount_1 = 50, amount_2 = 50, amount_3 = 100;
    private static final Image imageLock = new Image(Controller.class.getResourceAsStream("assets/lock.png"));
    private static final Image imageUnlock = new Image(Controller.class.getResourceAsStream("assets/unlock.png"));
    private static final Image imageLeverageSelected = new Image(Controller.class.getResourceAsStream("assets/selectedLeverage.png"));
    private static final Image imageLeverageUnselected = new Image(Controller.class.getResourceAsStream("assets/unselectedLeverage.png"));
    private static final Image imageSettings = new Image(Controller.class.getResourceAsStream("assets/settings.png"));
    private static final Image imageJournal = new Image(Controller.class.getResourceAsStream("assets/journal.png"));
    private static final Image imageAddToJournal = new Image(Controller.class.getResourceAsStream("assets/addToJournal.png"));
    private static final Image imageRUB = new Image(Controller.class.getResourceAsStream("assets/rub.png"));
    private static final Image imageUSD = new Image(Controller.class.getResourceAsStream("assets/usd.png"));
    private static final Image imageEUR = new Image(Controller.class.getResourceAsStream("assets/eur.png"));
    private static final Image imageBTC = new Image(Controller.class.getResourceAsStream("assets/btc.png"));
    private static final Image imageETH = new Image(Controller.class.getResourceAsStream("assets/eth.png"));
    private static final Image imageXRP = new Image(Controller.class.getResourceAsStream("assets/xrp.png"));

    public static double getUSD() {
        return USD;
    }

    public static void setUSD(double USD) {
        Controller.USD = USD;
    }

    public static double getEUR() {
        return EUR;
    }

    public static void setEUR(double EUR) {
        Controller.EUR = EUR;
    }

    public static double getBTC() {
        return BTC;
    }

    public static void setBTC(double BTC) {
        Controller.BTC = BTC;
    }

    public static double getETH() {
        return ETH;
    }

    public static void setETH(double ETH) {
        Controller.ETH = ETH;
    }

    public static double getXRP() {
        return XRP;
    }

    public static void setXRP(double XRP) {
        Controller.XRP = XRP;
    }

    public static int getFormatRUB() {
        return formatRUB;
    }

    public static void setFormatRUB(int formatRUB) {
        Controller.formatRUB = formatRUB;
    }

    public static int getFormatUSD() {
        return formatUSD;
    }

    public static void setFormatUSD(int formatUSD) {
        Controller.formatUSD = formatUSD;
    }

    public static int getFormatEUR() {
        return formatEUR;
    }

    public static void setFormatEUR(int formatEUR) {
        Controller.formatEUR = formatEUR;
    }

    public static int getFormatBTC() {
        return formatBTC;
    }

    public static void setFormatBTC(int formatBTC) {
        Controller.formatBTC = formatBTC;
    }

    public static int getFormatETH() {
        return formatETH;
    }

    public static void setFormatETH(int formatETH) {
        Controller.formatETH = formatETH;
    }

    public static int getFormatXRP() {
        return formatXRP;
    }

    public static void setFormatXRP(int formatXRP) {
        Controller.formatXRP = formatXRP;
    }

    public static void setGraphic(ToggleButton target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void setGraphic(Button target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void setGraphic(MenuButton target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void setGraphic(MenuItem target, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        target.setGraphic(imageView);
    }

    public static void getCurrency(String exchange) {
        System.out.println(exchange);
        setUSD(Double.parseDouble(Unirest.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .asJson()
                .getBody()
                .getObject()
                .getJSONObject("Valute")
                .getJSONObject("USD")
                .getString("Previous")));
        System.out.println("USD/RUB: "+ getUSD());

        setEUR(Double.parseDouble(Unirest.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .asJson()
                .getBody()
                .getObject()
                .getJSONObject("Valute")
                .getJSONObject("EUR")
                .getString("Previous")));
        System.out.println("EUR/RUB: "+ getEUR());

        switch (exchange) {
            case "EXMO": {
                setBTC(Double.parseDouble(Unirest.get("https://api.exmo.com/v1.1/ticker")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONObject("BTC_RUB")
                        .getString("last_trade")));
                System.out.println("BTC/RUB: " + getBTC());

                setETH(Double.parseDouble(Unirest.get("https://api.exmo.com/v1.1/ticker")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONObject("ETH_RUB")
                        .getString("last_trade")));
                System.out.println("ETH/RUB: " + getETH());

                setXRP(Double.parseDouble(Unirest.get("https://api.exmo.com/v1.1/ticker")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONObject("XRP_RUB")
                        .getString("last_trade")));
                System.out.println("XRP/RUB: " + getXRP());
                break;
            }
            case "BINANCE": {
                setBTC(Double.parseDouble(Unirest.get("https://api.binance.com/api/v3/ticker/price?symbol=BTCRUB")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getString("price")));
                System.out.println("BTC/RUB: " + getBTC());

                setETH(Double.parseDouble(Unirest.get("https://api.binance.com/api/v3/ticker/price?symbol=ETHRUB")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getString("price")));
                System.out.println("ETH/RUB: " + getETH());

                setXRP(Double.parseDouble(Unirest.get("https://api.binance.com/api/v3/ticker/price?symbol=XRPRUB")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getString("price")));
                System.out.println("XRP/RUB: " + getXRP());
                break;
            }
        }
    }

    public static void setCurrency(int n_currency, double Valute, MenuButton currency, MenuItem item, Image image, TextField leverage_1, TextField leverage_2, TextField leverage_3, Label firstProfit, Label secondProfit, TextField coefficient_1, TextField coefficient_2) {
        currency.setText(item.getText());
        setGraphic(currency, image);

        if (item.getText().equals("RUB")) {
            switch (n_currency) {
                case 1: {
                    if (c1 != 1) {
                        c1 = 1;
                        print(leverage_1, currency, amount_1);
                    }
                    break;
                }
                case 2: {
                    if (c2 != 1) {
                        c2 = 1;
                        print(leverage_2, currency, amount_2);
                    }
                    break;
                }
                case 3: {
                    if (c3 != 1) {
                        c3 = 1;
                        print(leverage_3, currency, amount_3);
                    }
                    break;
                }
            }
        }
        else {
            switch (n_currency) {
                case 1: {
                    if (c1 != Valute) {
                        c1 = Valute;
                        print(leverage_1, currency, amount_1 / c1);
                    }
                    break;
                }
                case 2: {
                    if (c2 != Valute) {
                        c2 = Valute;
                        print(leverage_2, currency, amount_2 / c2);
                    }
                    break;
                }
                case 3: {
                    if (c3 != Valute) {
                        c3 = Valute;
                        print(leverage_3, currency, amount_3 / c3);
                    }
                    break;
                }
            }
        }

        getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
    }

    public static void print(TextField field, MenuButton currency, double value) {

        switch (currency.getText()) {
            case "RUB": {
                switch (formatRUB) {
                    case 1: {
                        field.setText(String.format("%d", Math.round(value)));
                        break;
                    }
                    case 5: {
                        int intPart = (int) value/5;
                        if ((int) (value - intPart * 5) >= (double) 5/2) field.setText(String.format("%d", (intPart + 1) * 5));
                        else field.setText(String.format("%d", intPart * 5));
                        break;
                    }
                    case 10: {
                        int intPart = (int) value/10;
                        if ((int) (value - intPart * 10) >= (double) 10/2) field.setText(String.format("%d", (intPart + 1) * 10));
                        else field.setText(String.format("%d", intPart * 10));
                        break;
                    }
                    case 50: {
                        int intPart = (int) value/50;
                        if ((int) (value - intPart * 50) >= (double) 50/2) field.setText(String.format("%d", (intPart + 1) * 50));
                        else field.setText(String.format("%d", intPart * 50));
                        break;
                    }
                    case 100: {
                        int intPart = (int) value/100;
                        if ((int) (value - intPart * 100) >= (double) 100/2) field.setText(String.format("%d", (intPart + 1) * 100));
                        else field.setText(String.format("%d", intPart * 100));
                        break;
                    }
                    case 500: {
                        int intPart = (int) value/500;
                        if ((int) (value - intPart * 500) >= (double) 500/2) field.setText(String.format("%d", (intPart + 1) * 500));
                        else field.setText(String.format("%d", intPart * 500));
                        break;
                    }
                    case 1000: {
                        int intPart = (int) value/1000;
                        if ((int) (value - intPart * 1000) >= (double) 1000/2) field.setText(String.format("%d", (intPart + 1) * 1000));
                        else field.setText(String.format("%d", intPart * 1000));
                        break;
                    }
                }
                break;
            }

            case "USD": {
                switch (formatUSD) {
                    case 1: {
                        field.setText(String.format("%d", Math.round(value)));
                        break;
                    }
                    case 5: {
                        int intPart = (int) value/5;
                        if ((int) (value - intPart * 5) >= (double) 5/2) field.setText(String.format("%d", (intPart + 1) * 5));
                        else field.setText(String.format("%d", intPart * 5));
                        break;
                    }
                    case 10: {
                        int intPart = (int) value/10;
                        if ((int) (value - intPart * 10) >= (double) 10/2) field.setText(String.format("%d", (intPart + 1) * 10));
                        else field.setText(String.format("%d", intPart * 10));
                        break;
                    }
                    case 50: {
                        int intPart = (int) value/50;
                        if ((int) (value - intPart * 50) >= (double) 50/2) field.setText(String.format("%d", (intPart + 1) * 50));
                        else field.setText(String.format("%d", intPart * 50));
                        break;
                    }
                    case 100: {
                        int intPart = (int) value/100;
                        if ((int) (value - intPart * 100) >= (double) 100/2) field.setText(String.format("%d", (intPart + 1) * 100));
                        else field.setText(String.format("%d", intPart * 100));
                        break;
                    }
                    case 500: {
                        int intPart = (int) value/500;
                        if ((int) (value - intPart * 500) >= (double) 500/2) field.setText(String.format("%d", (intPart + 1) * 500));
                        else field.setText(String.format("%d", intPart * 500));
                        break;
                    }
                    case 1000: {
                        int intPart = (int) value/1000;
                        if ((int) (value - intPart * 1000) >= (double) 1000/2) field.setText(String.format("%d", (intPart + 1) * 1000));
                        else field.setText(String.format("%d", intPart * 1000));
                        break;
                    }
                }
                break;
            }

            case "EUR": {
                switch (formatEUR) {
                    case 1: {
                        field.setText(String.format("%d", Math.round(value)));
                        break;
                    }
                    case 5: {
                        int intPart = (int) value/5;
                        if ((int) (value - intPart * 5) >= (double) 5/2) field.setText(String.format("%d", (intPart + 1) * 5));
                        else field.setText(String.format("%d", intPart * 5));
                        break;
                    }
                    case 10: {
                        int intPart = (int) value/10;
                        if ((int) (value - intPart * 10) >= (double) 10/2) field.setText(String.format("%d", (intPart + 1) * 10));
                        else field.setText(String.format("%d", intPart * 10));
                        break;
                    }
                    case 50: {
                        int intPart = (int) value/50;
                        if ((int) (value - intPart * 50) >= (double) 50/2) field.setText(String.format("%d", (intPart + 1) * 50));
                        else field.setText(String.format("%d", intPart * 50));
                        break;
                    }
                    case 100: {
                        int intPart = (int) value/100;
                        if ((int) (value - intPart * 100) >= (double) 100/2) field.setText(String.format("%d", (intPart + 1) * 100));
                        else field.setText(String.format("%d", intPart * 100));
                        break;
                    }
                    case 500: {
                        int intPart = (int) value/500;
                        if ((int) (value - intPart * 500) >= (double) 500/2) field.setText(String.format("%d", (intPart + 1) * 500));
                        else field.setText(String.format("%d", intPart * 500));
                        break;
                    }
                    case 1000: {
                        int intPart = (int) value/1000;
                        if ((int) (value - intPart * 1000) >= (double) 1000/2) field.setText(String.format("%d", (intPart + 1) * 1000));
                        else field.setText(String.format("%d", intPart * 1000));
                        break;
                    }
                }
                break;
            }

            case "BTC": {
                switch (formatBTC) {
                    case -6: {
                        field.setText(String.format(Locale.ROOT, "%.6f", value));
                        break;
                    }
                    case -5: {
                        field.setText(String.format(Locale.ROOT, "%.5f", value));
                        break;
                    }
                    case -4: {
                        field.setText(String.format(Locale.ROOT, "%.4f", value));
                        break;
                    }
                    case -3: {
                        field.setText(String.format(Locale.ROOT, "%.3f", value));
                        break;
                    }
                    case -2: {
                        field.setText(String.format(Locale.ROOT, "%.2f", value));
                        break;
                    }
                    case -1: {
                        field.setText(String.format(Locale.ROOT, "%.1f", value));
                        break;
                    }
                }
                break;
            }

            case "ETH": {
                switch (formatETH) {
                    case -6: {
                        field.setText(String.format(Locale.ROOT, "%.6f", value));
                        break;
                    }
                    case -5: {
                        field.setText(String.format(Locale.ROOT, "%.5f", value));
                        break;
                    }
                    case -4: {
                        field.setText(String.format(Locale.ROOT, "%.4f", value));
                        break;
                    }
                    case -3: {
                        field.setText(String.format(Locale.ROOT, "%.3f", value));
                        break;
                    }
                    case -2: {
                        field.setText(String.format(Locale.ROOT, "%.2f", value));
                        break;
                    }
                    case -1: {
                        field.setText(String.format(Locale.ROOT, "%.1f", value));
                        break;
                    }
                    case 1: {
                        field.setText(String.format(Locale.ROOT, "%d", Math.round(value)));
                        break;
                    }
                }
                break;
            }

            case "XRP": {
                switch (formatXRP) {
                    case -6: {
                        field.setText(String.format(Locale.ROOT, "%.6f", value));
                        break;
                    }
                    case -5: {
                        field.setText(String.format(Locale.ROOT, "%.5f", value));
                        break;
                    }
                    case -4: {
                        field.setText(String.format(Locale.ROOT, "%.4f", value));
                        break;
                    }
                    case -3: {
                        field.setText(String.format(Locale.ROOT, "%.3f", value));
                        break;
                    }
                    case -2: {
                        field.setText(String.format(Locale.ROOT, "%.2f", value));
                        break;
                    }
                    case -1: {
                        field.setText(String.format(Locale.ROOT, "%.1f", value));
                        break;
                    }
                    case 1: {
                        field.setText(String.format("%d", Math.round(value)));
                        break;
                    }
                    case 5: {
                        int intPart = (int) value/5;
                        if ((int) (value - intPart * 5) >= (double) 5/2) field.setText(String.format("%d", (intPart + 1) * 5));
                        else field.setText(String.format("%d", intPart * 5));
                        break;
                    }
                    case 10: {
                        int intPart = (int) value/10;
                        if ((int) (value - intPart * 10) >= (double) 10/2) field.setText(String.format("%d", (intPart + 1) * 10));
                        else field.setText(String.format("%d", intPart * 10));
                        break;
                    }
                    case 50: {
                        int intPart = (int) value/50;
                        if ((int) (value - intPart * 50) >= (double) 50/2) field.setText(String.format("%d", (intPart + 1) * 50));
                        else field.setText(String.format("%d", intPart * 50));
                        break;
                    }
                    case 100: {
                        int intPart = (int) value/100;
                        if ((int) (value - intPart * 100) >= (double) 100/2) field.setText(String.format("%d", (intPart + 1) * 100));
                        else field.setText(String.format("%d", intPart * 100));
                        break;
                    }
                    case 500: {
                        int intPart = (int) value/500;
                        if ((int) (value - intPart * 500) >= (double) 500/2) field.setText(String.format("%d", (intPart + 1) * 500));
                        else field.setText(String.format("%d", intPart * 500));
                        break;
                    }
                    case 1000: {
                        int intPart = (int) value/1000;
                        if ((int) (value - intPart * 1000) >= (double) 1000/2) field.setText(String.format("%d", (intPart + 1) * 1000));
                        else field.setText(String.format("%d", intPart * 1000));
                        break;
                    }
                }
                break;
            }
        }
    }

    public static void getProfit(Label firstProfit, Label secondProfit, TextField coefficient_1, TextField coefficient_2) {
        if (amount_1 >= 0 & amount_2 >= 0 & !coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty()) {

            DecimalFormatSymbols symbol = new DecimalFormatSymbols();
            DecimalFormat customFormat = new DecimalFormat(".######");
            symbol.setDecimalSeparator('.');
            customFormat.setDecimalFormatSymbols(symbol);

            firstProfit.setText(customFormat.format((Double.parseDouble(coefficient_1.getText()) * amount_1 - amount_1 - amount_2) / c1));
            secondProfit.setText(customFormat.format((Double.parseDouble(coefficient_2.getText()) * amount_2 - amount_1 - amount_2) / c2));

            setProfitColor(firstProfit, secondProfit);
        }
    }

    public static void setProfitColor(Label firstProfit, Label secondProfit) {
            if (Double.parseDouble(firstProfit.getText()) >= 0) firstProfit.setStyle("-fx-text-fill:darkgreen");
            else firstProfit.setStyle("-fx-text-fill:darkred");
            if (Double.parseDouble(secondProfit.getText()) >= 0) secondProfit.setStyle("-fx-text-fill:darkgreen");
            else secondProfit.setStyle("-fx-text-fill:darkred");
    }

    public static void profitOnFirstLeverage_lock_1_unlock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_2, TextField leverage_3, MenuButton currency_2, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_2 != 0 & !coefficient_2.getText().isEmpty()) {
            amount_2 = amount_1 / (Double.parseDouble(coefficient_2.getText()) - 1);
            amount_3 = amount_1 + amount_2;
            print(leverage_2, currency_2, amount_2 / c2);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnFirstLeverage_unlock_1_lock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_3, MenuButton currency_1, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_2 != 0 & !coefficient_2.getText().isEmpty()) {
            amount_1 = amount_2 * (Double.parseDouble(coefficient_2.getText()) - 1);
            amount_3 = amount_1 + amount_2;
            print(leverage_1, currency_1, amount_1 / c1);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void lock_1_lock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_3, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_2 != 0) {
            amount_3 = amount_1 + amount_2;
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnFirstLeverage_lock_3(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_2, MenuButton currency_1, MenuButton currency_2, Label firstProfit, Label secondProfit) {
        if (amount_2 != 0 & amount_3 != 0 & !coefficient_2.getText().isEmpty()) {
            amount_2 = amount_3 / Double.parseDouble(coefficient_2.getText());
            amount_1 = amount_3 - amount_2;
            print(leverage_2, currency_2, amount_2 / c2);
            print(leverage_1, currency_1, amount_1 / c1);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnSecondLeverage_lock_1_unlock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_2, TextField leverage_3, MenuButton currency_2, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_2 != 0 & !coefficient_1.getText().isEmpty()) {
            amount_2 = amount_1 * (Double.parseDouble(coefficient_1.getText()) - 1);
            amount_3 = amount_1 + amount_2;
            print(leverage_2, currency_2, amount_2 / c2);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnSecondLeverage_unlock_1_lock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_3, MenuButton currency_1, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_2 != 0 & !coefficient_1.getText().isEmpty()) {
            amount_1 = amount_2 / (Double.parseDouble(coefficient_1.getText()) - 1);
            amount_3 = amount_1 + amount_2;
            print(leverage_1, currency_1, amount_1 / c1);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnSecondLeverage_lock_3(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_2, MenuButton currency_1, MenuButton currency_2, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_3 != 0 & !coefficient_1.getText().isEmpty()) {
            amount_1 = amount_3 / Double.parseDouble(coefficient_1.getText());
            amount_2 = amount_3 - amount_1;
            print(leverage_1, currency_1, amount_1 / c1);
            print(leverage_2, currency_2, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnThirdLeverage_lock_1_unlock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_2, TextField leverage_3, MenuButton currency_2, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & !coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty()) {
            amount_2 = Double.parseDouble(coefficient_1.getText()) / Double.parseDouble(coefficient_2.getText()) * amount_1 ;
            amount_3 = amount_1 + Double.parseDouble(coefficient_1.getText()) / Double.parseDouble(coefficient_2.getText()) * amount_1 ;
            print(leverage_2, currency_2, amount_2 / c2);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnThirdLeverage_unlock_1_lock_2(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_3, MenuButton currency_1, MenuButton currency_3, Label firstProfit, Label secondProfit) {
        if (amount_1 != 0 & amount_1 != 0 & !coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty()) {
            amount_1 = Double.parseDouble(coefficient_2.getText()) * amount_2 / Double.parseDouble(coefficient_1.getText());
            amount_3 = amount_1+ amount_2;
            print(leverage_1, currency_1, amount_1 / c1);
            print(leverage_3, currency_3, amount_3 / c3);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    public static void profitOnThirdLeverage_lock_3(TextField coefficient_1, TextField coefficient_2, TextField leverage_1, TextField leverage_2, MenuButton currency_1, MenuButton currency_2, Label firstProfit, Label secondProfit) {
        if (amount_3 != 0 & !coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty()) {
            amount_1 = Double.parseDouble(coefficient_2.getText()) / (Double.parseDouble(coefficient_1.getText()) + Double.parseDouble(coefficient_2.getText())) * amount_3;
            amount_2 = Double.parseDouble(coefficient_1.getText()) / (Double.parseDouble(coefficient_1.getText()) + Double.parseDouble(coefficient_2.getText())) * amount_3;
            print(leverage_1, currency_1, amount_1 / c1);
            print(leverage_2, currency_2, amount_2 / c2);
            getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
        }
    }

    @FXML
    private MenuButton bk_1, bk_2, currency_1, currency_2, currency_3;

    @FXML
    private Button openSettings, addToJournal, openJournal, completeFirstLeverage, completeSecondLeverage;

    @FXML
    private MenuItem stake_1, bet365_1, vulkan_1, lootbet_1, xbet_1, thunderpick_1, parimatch_1, ggbet_1,
            stake_2, bet365_2, vulkan_2, lootbet_2, xbet_2, thunderpick_2, parimatch_2, ggbet_2,
            RUB_1, USD_1, EUR_1, BTC_1, ETH_1, XRP_1,
            RUB_2, USD_2, EUR_2, BTC_2, ETH_2, XRP_2,
            RUB_3, USD_3, EUR_3, BTC_3, ETH_3, XRP_3;

    @FXML
    private TextField coefficient_1, coefficient_2, completeCoefficient, leverage_1, leverage_2, leverage_3;

    @FXML
    private ToggleButton leverageFix_1, leverageFix_2, leverageFix_3, profitOnFirstLeverage, profitOnSecondLeverage, profitOnThirdLeverage;

    @FXML
    private Label profitabilityPercentage, firstProfit, secondProfit, requiredAmount, newFirstProfit, newSecondProfit;
    
    @FXML
    void initialize() {
        getCurrency("EXMO");

        leverageFix_1.setSelected(true);
        profitOnThirdLeverage.setSelected(true);

        setGraphic(leverageFix_1, imageLock);
        setGraphic(leverageFix_2, imageUnlock);
        setGraphic(leverageFix_3, imageUnlock);
        setGraphic(profitOnFirstLeverage, imageLeverageUnselected);
        setGraphic(profitOnSecondLeverage, imageLeverageUnselected);
        setGraphic(profitOnThirdLeverage, imageLeverageSelected);
        setGraphic(openSettings, imageSettings);
        setGraphic(openJournal, imageJournal);
        setGraphic(addToJournal, imageAddToJournal);
        setGraphic(currency_1, imageRUB);
        setGraphic(currency_2, imageRUB);
        setGraphic(currency_3, imageRUB);
        setGraphic(RUB_1, imageRUB);
        setGraphic(RUB_2, imageRUB);
        setGraphic(RUB_3, imageRUB);
        setGraphic(USD_1, imageUSD);
        setGraphic(USD_2, imageUSD);
        setGraphic(USD_3, imageUSD);
        setGraphic(EUR_1, imageEUR);
        setGraphic(EUR_2, imageEUR);
        setGraphic(EUR_3, imageEUR);
        setGraphic(BTC_1, imageBTC);
        setGraphic(BTC_2, imageBTC);
        setGraphic(BTC_3, imageBTC);
        setGraphic(ETH_1, imageETH);
        setGraphic(ETH_2, imageETH);
        setGraphic(ETH_3, imageETH);
        setGraphic(XRP_1, imageXRP);
        setGraphic(XRP_2, imageXRP);
        setGraphic(XRP_3, imageXRP);

        stake_1.setOnAction(event -> bk_1.setText(stake_1.getText()));
        bet365_1.setOnAction(event -> bk_1.setText(bet365_1.getText()));
        vulkan_1.setOnAction(event -> bk_1.setText(vulkan_1.getText()));
        lootbet_1.setOnAction(event -> bk_1.setText(lootbet_1.getText()));
        xbet_1.setOnAction(event -> bk_1.setText(xbet_1.getText()));
        thunderpick_1.setOnAction(event -> bk_1.setText(thunderpick_1.getText()));
        parimatch_1.setOnAction(event -> bk_1.setText(parimatch_1.getText()));
        ggbet_1.setOnAction(event -> bk_1.setText(ggbet_1.getText()));

        stake_2.setOnAction(event -> bk_2.setText(stake_2.getText()));
        bet365_2.setOnAction(event -> bk_2.setText(bet365_2.getText()));
        vulkan_2.setOnAction(event -> bk_2.setText(vulkan_2.getText()));
        lootbet_2.setOnAction(event -> bk_2.setText(lootbet_2.getText()));
        xbet_2.setOnAction(event -> bk_2.setText(xbet_2.getText()));
        thunderpick_2.setOnAction(event -> bk_2.setText(thunderpick_2.getText()));
        parimatch_2.setOnAction(event -> bk_2.setText(parimatch_2.getText()));
        ggbet_2.setOnAction(event -> bk_2.setText(ggbet_2.getText()));

        coefficient_1.textProperty().addListener((obj, oldValue, newValue) -> {
            coefficient_1.setText(coefficient_1.getText().replace(',', '.'));
            coefficient_1.setText(coefficient_1.getText().replaceAll("[^\\d.]", ""));
            if (!coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty())
                profitabilityPercentage.setText(String.format(Locale.ROOT, "%.2f", (Double.parseDouble(coefficient_1.getText()) * Double.parseDouble(coefficient_2.getText()) / (Double.parseDouble(coefficient_1.getText()) + Double.parseDouble(coefficient_2.getText())) - 1) * 100));
            if (profitOnFirstLeverage.isSelected()) profitOnFirstLeverage.getOnAction().handle(new ActionEvent());
            if (profitOnSecondLeverage.isSelected()) profitOnSecondLeverage.getOnAction().handle(new ActionEvent());
            if (profitOnThirdLeverage.isSelected()) profitOnThirdLeverage.getOnAction().handle(new ActionEvent());
        });

        coefficient_2.textProperty().addListener((obj, oldValue, newValue) -> {
            coefficient_2.setText(coefficient_2.getText().replace(',', '.'));
            coefficient_2.setText(coefficient_2.getText().replaceAll("[^\\d.]", ""));
            if (!coefficient_1.getText().isEmpty() & !coefficient_2.getText().isEmpty())
                profitabilityPercentage.setText(String.format(Locale.ROOT, "%.2f", (Double.parseDouble(coefficient_1.getText()) * Double.parseDouble(coefficient_2.getText()) / (Double.parseDouble(coefficient_1.getText()) + Double.parseDouble(coefficient_2.getText())) - 1) * 100));
            if (profitOnFirstLeverage.isSelected()) profitOnFirstLeverage.getOnAction().handle(new ActionEvent());
            if (profitOnSecondLeverage.isSelected()) profitOnSecondLeverage.getOnAction().handle(new ActionEvent());
            if (profitOnThirdLeverage.isSelected()) profitOnThirdLeverage.getOnAction().handle(new ActionEvent());
        });

        completeCoefficient.textProperty().addListener((obj, oldValue, newValue) -> {
            completeCoefficient.setText(completeCoefficient.getText().replace(',', '.'));
            completeCoefficient.setText(completeCoefficient.getText().replaceAll("[^\\d.]", ""));
        });

        leverage_1.textProperty().addListener((obj, oldValue, newValue) -> {
            leverage_1.setText(leverage_1.getText().replace(',', '.'));
            leverage_1.setText(leverage_1.getText().replaceAll("[^\\d.]", ""));

            if (leverage_1.isFocused()) amount_1 = Double.parseDouble(leverage_1.getText()) / c1;

            if (leverageFix_1.isSelected() & !leverageFix_2.isSelected()) {

                if (profitOnFirstLeverage.isSelected()) profitOnFirstLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);

                if (profitOnSecondLeverage.isSelected()) profitOnSecondLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);

                if (profitOnThirdLeverage.isSelected()) profitOnThirdLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);
            }

            if (amount_1 != 0 & amount_2 != 0 & !leverageFix_1.isSelected() & leverageFix_2.isSelected() & !leverage_3.isFocused()) {
                amount_3 = amount_1 + amount_2;
                print(leverage_3, currency_3, amount_3 / c3);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (leverageFix_1.isSelected() & leverageFix_2.isSelected()) {
                lock_1_lock_2(coefficient_1, coefficient_2, leverage_3, currency_3, firstProfit, secondProfit);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (amount_1 != 0 & amount_3 != 0 & leverageFix_3.isSelected() & !leverage_2.isFocused()) {
                amount_2 = amount_3 - amount_1;
                print(leverage_2, currency_2, amount_2 / c2);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }
        });

        leverage_2.textProperty().addListener((obj, oldValue, newValue) -> {
            leverage_2.setText(leverage_2.getText().replace(',', '.'));
            leverage_2.setText(leverage_2.getText().replaceAll("[^\\d.]", ""));

            if (leverage_2.isFocused()) amount_2 = Double.parseDouble(leverage_2.getText()) / c2;

            if (amount_1 != 0 & amount_2 != 0 & leverageFix_1.isSelected() & !leverageFix_2.isSelected() & !leverage_3.isFocused()) {
                amount_3 = amount_1 + amount_2;
                print(leverage_3, currency_3, amount_3 / c3);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (!leverageFix_1.isSelected() & leverageFix_2.isSelected()) {

                if (profitOnFirstLeverage.isSelected()) profitOnFirstLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);

                if (profitOnSecondLeverage.isSelected()) profitOnSecondLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);

                if (profitOnThirdLeverage.isSelected()) profitOnThirdLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);
            }

            if (leverageFix_1.isSelected() & leverageFix_2.isSelected()) {
                lock_1_lock_2(coefficient_1, coefficient_2, leverage_3, currency_3, firstProfit, secondProfit);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (amount_2 != 0 & amount_3 != 0 & leverageFix_3.isSelected() & !leverage_1.isFocused()) {
                amount_1 = amount_3 - amount_2;
                print(leverage_1, currency_1, amount_1 / c1);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

        });

        leverage_3.textProperty().addListener((obj, oldValue, newValue) -> {
            leverage_3.setText(leverage_3.getText().replace(',', '.'));
            leverage_3.setText(leverage_3.getText().replaceAll("[^\\d.]", ""));

            if (leverage_3.isFocused()) amount_3 = Double.parseDouble(leverage_3.getText()) / c3;

            if (amount_1 != 0 & amount_3 != 0 & leverageFix_1.isSelected() & !leverageFix_2.isSelected() & !leverage_2.isFocused()) {
                //if ((amount_3 * c3 - amount_1 * c1) / c2 <= 0) amount_2 = 0;
                //else amount_2 = (amount_3 * c3 - amount_1 * c1) / c2;

                amount_2 = amount_3 - amount_1;
                print(leverage_2, currency_2, amount_2 / c2);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (amount_2 != 0 & amount_3 != 0 & !leverageFix_1.isSelected() & leverageFix_2.isSelected() & !leverage_1.isFocused()) {
                //if ((amount_3 * c3 - amount_2 * c2) / c1 <= 0) amount_1 = 0;
                //else amount_1 = (amount_3 * c3 - amount_2 * c2) / c1;

                amount_1 = amount_3 - amount_2;
                print(leverage_1, currency_1, amount_1 / c1);
                getProfit(firstProfit, secondProfit, coefficient_1, coefficient_2);
            }

            if (leverageFix_3.isSelected()) {
                if (profitOnFirstLeverage.isSelected()) profitOnFirstLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);

                if (profitOnSecondLeverage.isSelected()) profitOnSecondLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);

                if (profitOnThirdLeverage.isSelected()) profitOnThirdLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);
            }
        });

        completeFirstLeverage.setOnAction(event -> {
            if (!completeCoefficient.getText().trim().isEmpty()) {
                requiredAmount.setText(String.format(Locale.ROOT, "%.2f", Math.abs((Double.parseDouble(coefficient_1.getText()) * amount_1 - Double.parseDouble(coefficient_2.getText()) * amount_2) / Double.parseDouble(completeCoefficient.getText()))));
                newFirstProfit.setText(String.format(Locale.ROOT, "%.2f", Double.parseDouble(coefficient_1.getText()) * amount_1 + Double.parseDouble(completeCoefficient.getText()) * Double.parseDouble(requiredAmount.getText()) - amount_1 - amount_2 - Double.parseDouble(requiredAmount.getText())));
                newSecondProfit.setText(String.format(Locale.ROOT, "%.2f", Double.parseDouble(coefficient_2.getText()) * amount_2 - Double.parseDouble(requiredAmount.getText()) - amount_1 - amount_2));
                setProfitColor(newFirstProfit, newSecondProfit);
            }
            else requiredAmount.setText("Введите новый коэфф");
        });

        completeSecondLeverage.setOnAction(event -> {
            if (!completeCoefficient.getText().trim().isEmpty()) {
                requiredAmount.setText(String.format(Locale.ROOT, "%.2f", Math.abs((Double.parseDouble(coefficient_1.getText()) * amount_1 - Double.parseDouble(coefficient_2.getText()) * amount_2) / Double.parseDouble(completeCoefficient.getText()))));
                newFirstProfit.setText(String.format(Locale.ROOT, "%.2f", Double.parseDouble(coefficient_1.getText()) * amount_1 - Double.parseDouble(requiredAmount.getText()) - amount_1 - amount_2));
                newSecondProfit.setText(String.format(Locale.ROOT, "%.2f", Double.parseDouble(coefficient_2.getText()) * amount_2 + Double.parseDouble(completeCoefficient.getText()) * Double.parseDouble(requiredAmount.getText()) - amount_1 - amount_2 - Double.parseDouble(requiredAmount.getText())));
                setProfitColor(newFirstProfit, newSecondProfit);
            }
            else requiredAmount.setText("Введите новый коэфф");
        });

        leverageFix_1.setOnAction(event -> {
            if (leverageFix_1.isSelected()) setGraphic(leverageFix_1, imageLock);
            else setGraphic(leverageFix_1, imageUnlock);

            leverageFix_3.setSelected(false);
            setGraphic(leverageFix_3, imageUnlock);

            if (!leverageFix_1.isSelected() & !leverageFix_2.isSelected()) leverageFix_3.getOnAction().handle(new ActionEvent());
        });

        leverageFix_2.setOnAction(event -> {
            if (leverageFix_2.isSelected()) setGraphic(leverageFix_2, imageLock);
            else setGraphic(leverageFix_2, imageUnlock);

            leverageFix_3.setSelected(false);
            setGraphic(leverageFix_3, imageUnlock);

            if (!leverageFix_1.isSelected() & !leverageFix_2.isSelected()) leverageFix_3.getOnAction().handle(new ActionEvent());
        });

        leverageFix_3.setOnAction(event -> {
            leverageFix_1.setSelected(false);
            setGraphic(leverageFix_1, imageUnlock);

            leverageFix_2.setSelected(false);
            setGraphic(leverageFix_2, imageUnlock);

            if (leverageFix_3.isSelected()) setGraphic(leverageFix_3, imageLock);
            else setGraphic(leverageFix_3, imageUnlock);
        });

        RUB_1.setOnAction(event -> setCurrency(1, 1, currency_1, RUB_1, imageRUB, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        USD_1.setOnAction(event -> setCurrency(1, USD, currency_1, USD_1, imageUSD, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        EUR_1.setOnAction(event -> setCurrency(1, EUR, currency_1, EUR_1, imageEUR, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        BTC_1.setOnAction(event -> setCurrency(1, BTC, currency_1, BTC_1, imageBTC, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        ETH_1.setOnAction(event -> setCurrency(1, ETH, currency_1, ETH_1, imageETH, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        XRP_1.setOnAction(event -> setCurrency(1, XRP, currency_1, XRP_1, imageXRP, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));

        RUB_2.setOnAction(event -> setCurrency(2, 1, currency_2, RUB_2, imageRUB, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        USD_2.setOnAction(event -> setCurrency(2, USD, currency_2, USD_2, imageUSD, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        EUR_2.setOnAction(event -> setCurrency(2, EUR, currency_2, EUR_2, imageEUR, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        BTC_2.setOnAction(event -> setCurrency(2, BTC, currency_2, BTC_2, imageBTC, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        ETH_2.setOnAction(event -> setCurrency(2, ETH, currency_2, ETH_2, imageETH, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        XRP_2.setOnAction(event -> setCurrency(2, XRP, currency_2, XRP_2, imageXRP, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));

        RUB_3.setOnAction(event -> setCurrency(3, 1, currency_3, RUB_3, imageRUB, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        USD_3.setOnAction(event -> setCurrency(3, USD, currency_3, USD_3, imageUSD, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        EUR_3.setOnAction(event -> setCurrency(3, EUR, currency_3, EUR_3, imageEUR, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        BTC_3.setOnAction(event -> setCurrency(3, BTC, currency_3, BTC_3, imageBTC, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        ETH_3.setOnAction(event -> setCurrency(3, ETH, currency_3, ETH_3, imageETH, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));
        XRP_3.setOnAction(event -> setCurrency(3, XRP, currency_3, XRP_3, imageXRP, leverage_1, leverage_2, leverage_3, firstProfit, secondProfit, coefficient_1, coefficient_2));

        profitOnFirstLeverage.setOnAction(event -> {
            profitOnFirstLeverage.setSelected(true);
            setGraphic(profitOnFirstLeverage, imageLeverageSelected);

            profitOnSecondLeverage.setSelected(false);
            setGraphic(profitOnSecondLeverage, imageLeverageUnselected);

            profitOnThirdLeverage.setSelected(false);
            setGraphic(profitOnThirdLeverage, imageLeverageUnselected);

            if (leverageFix_1.isSelected() & !leverageFix_2.isSelected()) profitOnFirstLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);

            if (!leverageFix_1.isSelected() & leverageFix_2.isSelected()) profitOnFirstLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);

            if (leverageFix_1.isSelected() & leverageFix_2.isSelected()) lock_1_lock_2(coefficient_1, coefficient_2, leverage_3, currency_3, firstProfit, secondProfit);

            if (leverageFix_3.isSelected()) profitOnFirstLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);
        });

        profitOnSecondLeverage.setOnAction(event -> {
            profitOnFirstLeverage.setSelected(false);
            setGraphic(profitOnFirstLeverage, imageLeverageUnselected);

            profitOnSecondLeverage.setSelected(true);
            setGraphic(profitOnSecondLeverage, imageLeverageSelected);

            profitOnThirdLeverage.setSelected(false);
            setGraphic(profitOnThirdLeverage, imageLeverageUnselected);

            if (leverageFix_1.isSelected() & !leverageFix_2.isSelected()) profitOnSecondLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);

            if (!leverageFix_1.isSelected() & leverageFix_2.isSelected()) profitOnSecondLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);

            if (leverageFix_1.isSelected() & leverageFix_2.isSelected()) lock_1_lock_2(coefficient_1, coefficient_2, leverage_3, currency_3, firstProfit, secondProfit);

            if (leverageFix_3.isSelected()) profitOnSecondLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);
        });

        profitOnThirdLeverage.setOnAction(event -> {
            profitOnFirstLeverage.setSelected(false);
            setGraphic(profitOnFirstLeverage, imageLeverageUnselected);

            profitOnSecondLeverage.setSelected(false);
            setGraphic(profitOnSecondLeverage, imageLeverageUnselected);

            profitOnThirdLeverage.setSelected(true);
            setGraphic(profitOnThirdLeverage, imageLeverageSelected);

            if (leverageFix_1.isSelected() & !leverageFix_2.isSelected()) profitOnThirdLeverage_lock_1_unlock_2(coefficient_1, coefficient_2, leverage_2, leverage_3, currency_2, currency_3, firstProfit, secondProfit);

            if (!leverageFix_1.isSelected() & leverageFix_2.isSelected()) profitOnThirdLeverage_unlock_1_lock_2(coefficient_1, coefficient_2, leverage_1, leverage_3, currency_1, currency_3, firstProfit, secondProfit);

            if (leverageFix_1.isSelected() & leverageFix_2.isSelected()) lock_1_lock_2(coefficient_1, coefficient_2, leverage_3, currency_3, firstProfit, secondProfit);

            if (leverageFix_3.isSelected()) profitOnThirdLeverage_lock_3(coefficient_1, coefficient_2, leverage_1, leverage_2, currency_1, currency_2, firstProfit, secondProfit);
        });

        addToJournal.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            String dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now()).toString();

            if (Double.parseDouble(firstProfit.getText()) <= Double.parseDouble(secondProfit.getText()))
                finalProfit = firstProfit.getText();
            else finalProfit = secondProfit.getText();

            String addCoefficient = "0";
            if (!completeCoefficient.getText().trim().isEmpty()) addCoefficient = completeCoefficient.getText();
            dbHandler.toDatabase(dateTime, "Event name", profitabilityPercentage.getText(),
                    finalProfit, currency_1.getText(), currency_2.getText(), currency_3.getText(), bk_1.getText(), bk_2.getText(),
                    coefficient_1.getText(), coefficient_2.getText(), addCoefficient, leverage_1.getText(),
                    leverage_2.getText(), requiredAmount.getText(), firstProfit.getText(), secondProfit.getText(),
                    newFirstProfit.getText(), newSecondProfit.getText(), "0", "First outcome", "Second outcome");
        });

        openJournal.setOnAction(event -> {
            try {
                Main.openJournal((Stage) openJournal.getScene().getWindow(), Modality.NONE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        openSettings.setOnAction(event -> {
            try {
                Main.openSettings((Stage) openJournal.getScene().getWindow(), Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}