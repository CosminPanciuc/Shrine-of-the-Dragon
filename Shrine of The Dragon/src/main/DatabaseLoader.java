package main;

import entity.MovableEntity;
import entity.StationaryEntity;

import java.sql.*;
import java.util.Objects;

public class DatabaseLoader {
    MainPanel mp;
    private Connection connection;
    public DatabaseLoader(MainPanel mp, String DB){
        this.mp = mp;
        try {
            Class.forName("org.sqlite.JDBC");
            if(Objects.equals(DB, "saves"))
                connection = DriverManager.getConnection("jdbc:sqlite:saves.db");
            if(Objects.equals(DB, "default"))
                connection = DriverManager.getConnection("jdbc:sqlite:default.db");
            initTables();
        } catch (ClassNotFoundException e) {
            System.out.println("Sql error");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void initTables(){
        try {
            var stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS \"MovableEntitys\" (\n" +
                    "\t\"WorldID\"\tINTEGER,\n" +
                    "\t\"Type\"\tTEXT,\n" +
                    "\t\"CoordX\"\tINTEGER,\n" +
                    "\t\"CoordY\"\tINTEGER,\n" +
                    "\t\"HP\"\tINTEGER,\n" +
                    "\t\"Speed\"\tINTEGER,\n" +
                    "\t\"Damage\"\tINTEGER\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS \"StationaryEntity\" (\n" +
                    "\t\"WorldID\"\tINTEGER,\n" +
                    "\t\"Type\"\tTEXT,\n" +
                    "\t\"Size\"\tTEXT,\n" +
                    "\t\"CoordX\"\tINTEGER,\n" +
                    "\t\"CoordY\"\tINTEGER,\n" +
                    "\t\"HP\"\tINTEGER\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS \"PlayerStats\" (\n" +
                    "\t\"WorldID\"\tINTEGER,\n" +
                    "\t\"CoordX\"\tINTEGER,\n" +
                    "\t\"CoordY\"\tINTEGER,\n" +
                    "\t\"HP\"\tINTEGER,\n" +
                    "\t\"Hunger\"\tINTEGER,\n" +
                    "\t\"ActionWidth\"\tINTEGER,\n" +
                    "\t\"ActionHeight\"\tINTEGER\n" +
                    "\t\"Gold\"\tINTEGER\n" +
                    "\t\"Meat\"\tINTEGER\n" +
                    "\t\"Skin\"\tINTEGER\n" +
                    ");");
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //incarca din database
    public void LoadFromDatabase(){
        try{
            for(Level i: mp.levelManager.levels){
                i.stationaryEntities.clear();
                i.movableEntities.clear();
            }

            var stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MovableEntitys;");
            while(rs.next()){
                int WorldID = rs.getInt("WorldID");
                String Type = rs.getString("Type");
                int CoordX = rs.getInt("CoordX");
                int CoordY = rs.getInt("CoordY");
                int hp = rs.getInt("HP");
                int speed = rs.getInt("Speed");
                int damage = rs.getInt("Damage");
                mp.levelManager.levels.get(WorldID).movableEntities.add(mp.movableEntityFactory.factory(Type,CoordX,CoordY,hp,speed,damage));
            }
            rs = stmt.executeQuery("SELECT * FROM StationaryEntity;");
            while(rs.next()){
                int WorldID = rs.getInt("WorldID");
                String Type = rs.getString("Type");
                String Size = rs.getString("Size");
                int CoordX = rs.getInt("CoordX");
                int CoordY = rs.getInt("CoordY");
                int hp = rs.getInt("HP");
                mp.levelManager.levels.get(WorldID).stationaryEntities.add(mp.staticEntityFactory.factory(Type,Size,CoordX,CoordY,hp));
            }
            rs = stmt.executeQuery("SELECT * FROM PlayerStats;");
                int WorldID = rs.getInt("WorldID");
                int CoordX = rs.getInt("CoordX");
                int CoordY = rs.getInt("CoordY");
                int hp = rs.getInt("HP");
                int hunger = rs.getInt("Hunger");
                int actionWidth = rs.getInt("ActionWidth");
                int actionHeight = rs.getInt("ActionHeight");
                int gold = rs.getInt("Gold");
                int meat = rs.getInt("Meat");
                int skin = rs.getInt("Skin");
                mp.levelManager.currentLevelID = WorldID;
                mp.player.setPlayerStats(CoordX,CoordY,hp,hunger,actionWidth,actionHeight,gold,meat,skin);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //salveaza in database
    public void SaveToDatabase(){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM MovableEntitys;" +
                    "VACUUM;");
            stmt.executeUpdate("DELETE FROM StationaryEntity;" +
                    "VACUUM;");
            stmt.executeUpdate("DELETE FROM PlayerStats;" +
                    "VACUUM;");
            int curentWorld = 0;
            stmt.executeUpdate("INSERT INTO PlayerStats (WorldID,CoordX,CoordY,HP,Hunger,ActionWidth,ActionHeight,Gold,Meat,Skin)" +
                    "VALUES ("+curentWorld + ", " + mp.player.worldX/48 + ", " + mp.player.worldY/48 + ", " + mp.player.healthPool + ", " + mp.player.hunger + ", " + mp.player.actionArea.width + ", " + mp.player.actionArea.height + ", " + mp.player.gold + ", " + mp.player.meat + ", " + mp.player.skin + ");");
            for(Level i: mp.levelManager.levels){
                String entityClass = null;
                for(StationaryEntity stationaryEntity : i.stationaryEntities){
                    entityClass = stationaryEntity.getClass().toString().substring(stationaryEntity.getClass().toString().lastIndexOf(".")+1).trim();
                    stmt.execute("INSERT INTO StationaryEntity (WorldID,Type,Size,CoordX,CoordY,HP) " +
                            "VALUES ("+ curentWorld +", "+ "'" + entityClass + "'" + ", " + "'" + stationaryEntity.size + "'" + ", " + stationaryEntity.worldX/48 + ", " + stationaryEntity.worldY/48 + ", " + stationaryEntity.hitPoints + ");");
                }
                for(MovableEntity movableEntity : i.movableEntities){
                    entityClass = movableEntity.getClass().toString().substring(movableEntity.getClass().toString().lastIndexOf(".")+1).trim();
                    stmt.execute("INSERT INTO MovableEntitys (WorldID,Type,CoordX,CoordY,HP,Speed,Damage) " +
                            "VALUES ("+curentWorld + ", " + "'" + entityClass + "'" + ", " + movableEntity.worldX/48 + ", " + movableEntity.worldY/48 + ", " + movableEntity.healthPool + ", " + movableEntity.speed + ", " + movableEntity.damage + " );");
                }
                curentWorld++;
            }
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void closeDB() {
        try {
            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
