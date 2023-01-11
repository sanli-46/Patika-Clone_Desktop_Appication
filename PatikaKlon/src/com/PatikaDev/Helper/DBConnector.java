package com.PatikaDev.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect = null;

    public Connection connectionDB(){
        try {
            this.connect= DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME,Config.DB_PASSWOED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return this.connect;
    }

    public static Connection getInstance(){
        DBConnector db =new DBConnector();
        return db.connectionDB();
    }
}
