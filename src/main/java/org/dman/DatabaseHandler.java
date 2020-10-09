package org.dman;

import java.sql.*;

public class DatabaseHandler {
    public Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql:SurebetsDB", "postgres", "326159");
    }

    public void toDatabase(String dateTime, String eventName, String percentage, String profit, String firstValute, String secondValute, String thirdValute,
                           String firstBookmaker, String secondBookmaker, String firstCoefficient, String secondCoefficient, String completeCoefficient,
                           String firstAmount, String secondAmount, String requiredAmount, String firstWin, String secondWin, String newFirstProfit, String newSecondProfit,
                           String firstOrSecond, String firstIssue, String secondIssue) {
        String insert = "INSERT INTO " + Const.FORKS_TABLE + " VALUES" + "('" + dateTime + "', '" + eventName + "', " + percentage + ", " +
                profit + ", '" + firstValute + "', '" + secondValute + "', '" + thirdValute + "', '" +  firstBookmaker + "', '" +  secondBookmaker + "', " +
                firstCoefficient + ", " +  secondCoefficient + ", " + completeCoefficient + ", " + firstAmount + ", " + secondAmount + ", " +
                requiredAmount + ", " + firstWin + ", " +  secondWin + ", " + newFirstProfit + ", " + newSecondProfit + ", " + firstOrSecond + ", '" +
                firstIssue + "', '" +  secondIssue + "')";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ResultSet getSurebets(String fromDate, String toDate) {
        ResultSet resultSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement("SELECT * FROM " + Const.FORKS_TABLE + " WHERE " + "\"" + Const.DATE_TIME + "\" " + " BETWEEN " + "'" + fromDate + "'" + " AND " + "'" + toDate + "'");
            resultSet = prSt.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return resultSet;
    }

    public void deleteSurebet(String dateTime) {
        try {
            PreparedStatement prStDelete = getDbConnection().prepareStatement("DELETE FROM " + Const.FORKS_TABLE + " WHERE " + "\"" + Const.DATE_TIME + "\" " + "= " + "'" + dateTime + "'");
            System.out.println(prStDelete.toString());
            prStDelete.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void editSurebet(String dateTime, String nameEvent, String firstBookmaker, String secondBookmaker, String firstCoefficient, String secondCoefficient, String completeCoefficient,
                         String firstAmount, String secondAmount, String requiredAmount, String firstWin, String secondWin, String newFirstProfit, String newSecondProfit, String firstIssue, String secondIssue) {
        try {
            PreparedStatement prStEdit = getDbConnection().prepareStatement("UPDATE " + Const.FORKS_TABLE + " SET "
                    + "\"" + Const.EVENT_NAME + "\"" + " = " + "'" + nameEvent + "'" + ", "
                    + "\"" + Const.FIRST_BOOKMAKER + "\"" + " = " + "'" + firstBookmaker + "'" + ","
                    + "\"" + Const.SECOND_BOOKMAKER + "\"" + " = " + "'" + secondBookmaker + "'" + ","
                    + "\"" + Const.FIRST_COEFFICIENT + "\"" + " = " + firstCoefficient + ", "
                    + "\"" + Const.SECOND_COEFFICIENT + "\"" + " = " + secondCoefficient + ", "
                    + "\"" + Const.COMPLETE_COEFFICIENT + "\"" + " = " + completeCoefficient + ", "
                    + "\"" + Const.FIRST_AMOUNT + "\"" + " = " + firstAmount + ", "
                    + "\"" + Const.SECOND_AMOUNT + "\"" + " = " + secondAmount + ", "
                    + "\"" + Const.REQUIRED_AMOUNT + "\""+ " = " + requiredAmount + ", "
                    + "\"" + Const.FIRST_WIN + "\"" + " = " + firstWin + ", "
                    + "\"" + Const.SECOND_WIN + "\"" + " = " + secondWin + ", "
                    + "\"" + Const.NEW_FIRST_WIN + "\"" + " = " + newFirstProfit + ", "
                    + "\"" + Const.NEW_SECOND_WIN + "\"" + " = " + newSecondProfit + ", "
                    + "\"" + Const.FIRST_ISSUE + "\"" + " = " + "'" + firstIssue + "'" + ", "
                    + "\"" + Const.SECOND_ISSUE + "\"" + " = " + "'" + secondIssue + "'"
                    + "\n" + "WHERE " + " \"" + Const.DATE_TIME + "\"" + " = " + "'" + dateTime + "'");
            System.out.println(prStEdit.toString());
            prStEdit.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void editWinner(String dateTime, String firstOrSecond, String profit) {
        try {
            PreparedStatement prStEdit = getDbConnection().prepareStatement("UPDATE " + Const.FORKS_TABLE + " SET "
                    + Const.FIRST_OR_SECOND + " = " + firstOrSecond + ", " + Const.PROFIT + " = " + profit
                    + "\n" + "WHERE " + Const.DATE_TIME + " = " + "'" + dateTime + "'");
            prStEdit.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}