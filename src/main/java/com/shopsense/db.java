package com.shopsense;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {
	final private String DB_NAME = "shop_sense";
    final private String DB_USER = "root";
    final private String DB_PASS = "nh123456";
    
    private static db dbInstance = null;
    private Connection con;

    private db() {
        connect();
    }

    public static Connection get() {
        if (dbInstance == null) {
            dbInstance = new db();
        }
        return dbInstance.con;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_USER, DB_PASS);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
