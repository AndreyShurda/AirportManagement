package com.andrey.main.dl.dao;

import java.sql.*;

import static com.andrey.main.dl.dao.InitialData.*;

public class DBUtil {
    private static String url = URL + DATABASE+"?verifyServerCertificate=false&useSSL=true";
    private static Connection con;
    private static Statement stmt;


    public static ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = stmt.executeQuery(query);
        return resultSet;
    }

    public static void executeQuery(String query, String... params) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement statement = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.commit();
        }
    }

    public static void createConnection() {
        try {
            con = DriverManager.getConnection(url, DB_USER, DB_PASSWORDS);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
