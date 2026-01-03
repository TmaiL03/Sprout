package com.tomatohat.db;

import java.sql.*;

public class DatabaseManager {

    private static DatabaseManager instance;
    private final String CONNECTION_STRING;

    private DatabaseManager() {
        CONNECTION_STRING = "jdbc:sqlite:src/main/java/com/tomatohat/db/sprout.db";

        this.initConnection(CONNECTION_STRING);
    }

    public static DatabaseManager getDatabaseManager() {

        if(instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    private void initConnection(String connectionString) {
        try(Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement()) {

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS project (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    description TEXT,
                    dateCreated TEXT DEFAULT CURRENT_TIMESTAMP,
                    dateModified TEXT DEFAULT CURRENT_TIMESTAMP
                    )
                    """);

//            stmt.execute("INSERT INTO project(name, description) VALUES('My first project', 'This is a description of a test project.')");
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM project");
//
//            while(rs.next()) {
//                System.out.println(rs.getInt("id") + " "
//                        + rs.getString("name") + " "
//                        + rs.getString("description") + " "
//                        + rs.getString("dateCreated") + " "
//                        + rs.getString("dateModified"));
//            }

        } catch(SQLException e) {

            e.printStackTrace();
            System.out.println("Could not connect to database.");

        }
    }

}
