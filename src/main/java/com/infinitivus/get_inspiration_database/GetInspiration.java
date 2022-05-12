package com.infinitivus.get_inspiration_database;

import java.sql.*;

public class GetInspiration {
    String inspiration = null;
    int size = 0;

    public String getInspiration() {
        try {
            int result = inspirationRandomNumber(getSizeDB());
            ResultSet rs = connection().executeQuery("SELECT text FROM telegram_bot_inspiration.inspiration WHERE id = " + result);
            rs.next();
            inspiration = rs.getString("text");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspiration;
    }

    private int getSizeDB() {
        try {
            ResultSet rs = connection().executeQuery("SELECT COUNT(*) FROM telegram_bot_inspiration.inspiration");
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    private int inspirationRandomNumber(int size) {
        return (int) ((Math.random() * (size) + 1));
    }

    private Statement connection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/telegram_bot_inspiration", "root", "admin");
        return con.createStatement();
    }
}
