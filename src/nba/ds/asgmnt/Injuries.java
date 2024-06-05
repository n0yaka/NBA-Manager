/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;

import java.sql.*;
import java.util.*;

/**
 *
 * @author radin
 */
public class Injuries {
    
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";

    Scanner scanner = new Scanner(System.in);
    
    Connection connect = null;
    
    public Injuries() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addToInjuryStack(int id,String injury){
        try{
            String injurySql = "select * from firstteam where id = ?";
            PreparedStatement injuryStmt = connect.prepareStatement(injurySql);
            injuryStmt.setInt(1, id);
            ResultSet rs = injuryStmt.executeQuery();
            
            if(rs.next()){
                NBAPlayer injuredPlayer = new NBAPlayer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("team"),
                    rs.getString("position"),
                    rs.getDouble("height_cm"),
                    rs.getDouble("weight_kg"),
                    rs.getDouble("points_per_game"),
                    rs.getDouble("rebounds_per_game"),
                    rs.getDouble("assists_per_game"),
                    rs.getDouble("blocks_per_game"),
                    rs.getDouble("steals_per_game"),
                    rs.getDouble("salary")
                );
                
                String insertSQL = "INSERT INTO injury (name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary, injury) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertInjurySQL = connect.prepareStatement(insertSQL);
                
                insertInjurySQL.setString(1, injuredPlayer.getName());
                insertInjurySQL.setString(2, injuredPlayer.getTeam());
                insertInjurySQL.setString(3, injuredPlayer.getPosition());
                insertInjurySQL.setDouble(4, injuredPlayer.getHeight());
                insertInjurySQL.setDouble(5, injuredPlayer.getWeight());
                insertInjurySQL.setDouble(6, injuredPlayer.getPoints());
                insertInjurySQL.setDouble(7, injuredPlayer.getRebounds());
                insertInjurySQL.setDouble(8, injuredPlayer.getAssists());
                insertInjurySQL.setDouble(9, injuredPlayer.getBlocks());
                insertInjurySQL.setDouble(10, injuredPlayer.getSteals());
                insertInjurySQL.setDouble(11, injuredPlayer.getSalary());
                insertInjurySQL.setString(12, injury);
                
                insertInjurySQL.executeUpdate();
                insertInjurySQL.close();
                
                String deleteSql = "DELETE FROM firstteam WHERE id = ?";
                PreparedStatement deleteStmt = connect.prepareStatement(deleteSql);
                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }
            
            rs.close();
            injuryStmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void removeFromInjuryStack(){
        try{
            String recoverySQL = "select * from injury order by id desc limit 1";
            PreparedStatement recoverStmt = connect.prepareStatement(recoverySQL);
            ResultSet rs = recoverStmt.executeQuery();
            
            if(rs.next()){
                NBAPlayer recoveredPlayer = new NBAPlayer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("team"),
                    rs.getString("position"),
                    rs.getDouble("height_cm"),
                    rs.getDouble("weight_kg"),
                    rs.getDouble("points_per_game"),
                    rs.getDouble("rebounds_per_game"),
                    rs.getDouble("assists_per_game"),
                    rs.getDouble("blocks_per_game"),
                    rs.getDouble("steals_per_game"),
                    rs.getDouble("salary")
                );
                
                String insertSQL = "INSERT INTO firstTeam (name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement recoveryStmt = connect.prepareStatement(insertSQL);
                
                recoveryStmt.setString(1, recoveredPlayer.getName());
                recoveryStmt.setString(2, recoveredPlayer.getTeam());
                recoveryStmt.setString(3, recoveredPlayer.getPosition());
                recoveryStmt.setDouble(4, recoveredPlayer.getHeight());
                recoveryStmt.setDouble(5, recoveredPlayer.getWeight());
                recoveryStmt.setDouble(6, recoveredPlayer.getPoints());
                recoveryStmt.setDouble(7, recoveredPlayer.getRebounds());
                recoveryStmt.setDouble(8, recoveredPlayer.getAssists());
                recoveryStmt.setDouble(9, recoveredPlayer.getBlocks());
                recoveryStmt.setDouble(10, recoveredPlayer.getSteals());
                recoveryStmt.setDouble(11, recoveredPlayer.getSalary());
                
                recoveryStmt.executeUpdate();
                recoveryStmt.close();
                
                String deleteSql = "DELETE FROM injury WHERE id = ?";
                PreparedStatement deleteStmt = connect.prepareStatement(deleteSql);
                deleteStmt.setInt(1, rs.getInt("id"));
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }
            recoverStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void injuryList(){
        try{
            String viewSQL = "select * from injury order by id desc";
            PreparedStatement viewStmt = connect.prepareStatement(viewSQL);
            ResultSet rs = viewStmt.executeQuery();
            
            while(rs.next()){
                System.out.println("Name : "+rs.getString("name"));
                System.out.println("Injury : "+rs.getString("injury"));
            }
            viewStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
