/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.sql.*;
import java.util.*;

/**
 *
 * @author radin
 */
public class PlayerDatabase {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";
        
    Connection connect = null;
    
    public Map<Integer,NBAPlayer> getNBAPlayers(){
        Map<Integer,NBAPlayer> nbaPlayers = new HashMap<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            
            Statement stmt = connect.createStatement();
            String sql = "SELECT id, name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary FROM Players";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String team = rs.getString("team");
                String position = rs.getString("position");
                double height = rs.getDouble("height_cm");
                double weight = rs.getDouble("weight_kg");
                double points = rs.getDouble("points_per_game");
                double rebounds = rs.getDouble("rebounds_per_game");
                double assists = rs.getDouble("assists_per_game");
                double blocks = rs.getDouble("blocks_per_game");
                double steals = rs.getDouble("steals_per_game");
                double salary = rs.getDouble("salary");
                
                NBAPlayer insertPlayer = new NBAPlayer(id,name,team,position,height,weight,points,rebounds,assists,blocks,steals,salary);
                nbaPlayers.put(id, insertPlayer);
            }
            
            // Close ResultSet and Statement after processing
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nbaPlayers;
    }
}