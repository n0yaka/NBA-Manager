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
    Stack<NBAPlayer> injured = new Stack<>();
    Stack<String> reason = new Stack<>();
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
    
    public void addToInjuryStack(int id){
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
                
                System.out.print("Injury : ");
                String injury = scanner.nextLine();
                injured.push(injuredPlayer);
                reason.push(injury);
                System.out.println(injuredPlayer.getName() + " has been added to injury reserves");
                
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
        if (!injured.isEmpty()) {
            NBAPlayer recovered = injured.pop();
            String injury = reason.pop();
            
            try{
                String recoverySql = "INSERT INTO firstteam (name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement recoveryStmt = connect.prepareStatement(recoverySql);
                
                recoveryStmt.setString(1, recovered.getName());
                recoveryStmt.setString(2, recovered.getTeam());
                recoveryStmt.setString(3, recovered.getPosition());
                recoveryStmt.setDouble(4, recovered.getHeight());
                recoveryStmt.setDouble(5, recovered.getWeight());
                recoveryStmt.setDouble(6, recovered.getPoints());
                recoveryStmt.setDouble(7, recovered.getRebounds());
                recoveryStmt.setDouble(8, recovered.getAssists());
                recoveryStmt.setDouble(9, recovered.getBlocks());
                recoveryStmt.setDouble(10, recovered.getSteals());
                recoveryStmt.setDouble(11, recovered.getSalary());
                
                recoveryStmt.executeUpdate();
                recoveryStmt.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public void injuryList(){
        Stack<NBAPlayer> tempInjured = (Stack<NBAPlayer>) injured.clone();
        Stack<String> tempInjury = (Stack<String>) reason.clone();
        while(!tempInjured.isEmpty()){
            NBAPlayer player = tempInjured.pop();
            System.out.println("Name : "+player.getName());
            System.out.println("Injury : "+tempInjury.pop());
            System.out.println();
        }
        
    }
    
}
