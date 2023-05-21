package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseLoader {
    MainPanel mp;
    private Connection connection;
    public DatabaseLoader(MainPanel mp){
        this.mp = mp;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:saves.db");
        } catch (ClassNotFoundException e) {
            System.out.println("Sql error");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void LoadFromDatabase(){

    }
    public void SaveToDatabase(){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.close();
        }catch (SQLException e){
            System.out.println("Invalid statement");
        }
    }
}
