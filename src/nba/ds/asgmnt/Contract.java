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
public class Contract {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";
    Queue<String> contractQ = new LinkedList<>();
    
    Connection connect = null;
    
    public Contract() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public void ContractExtension(int id){
        try{
            String contractSql = "select * from firstteam where id = ?";
            PreparedStatement contractStmt = connect.prepareStatement(contractSql);
            contractStmt.setInt(1, id);
            ResultSet rs = contractStmt.executeQuery();
            
            if(rs.next()){
                NBAPlayer contractPlayer = new NBAPlayer(
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
                
                String insertSQL = "INSERT INTO contract(name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertContractSQL = connect.prepareStatement(insertSQL);
                
                insertContractSQL.setString(1, contractPlayer.getName());
                insertContractSQL.setString(2, contractPlayer.getTeam());
                insertContractSQL.setString(3, contractPlayer.getPosition());
                insertContractSQL.setDouble(4, contractPlayer.getHeight());
                insertContractSQL.setDouble(5, contractPlayer.getWeight());
                insertContractSQL.setDouble(6, contractPlayer.getPoints());
                insertContractSQL.setDouble(7, contractPlayer.getRebounds());
                insertContractSQL.setDouble(8, contractPlayer.getAssists());
                insertContractSQL.setDouble(9, contractPlayer.getBlocks());
                insertContractSQL.setDouble(10, contractPlayer.getSteals());
                insertContractSQL.setDouble(11, contractPlayer.getSalary());
                
                insertContractSQL.executeUpdate();
                insertContractSQL.close();
                
                String deleteSql = "DELETE FROM firstteam WHERE id = ?";
                PreparedStatement deleteStmt = connect.prepareStatement(deleteSql);
                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }
            
            rs.close();
            contractStmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void ExtendContract(){
        try{
            String recoverySQL = "select * from contract order by id asc limit 1";
            PreparedStatement contractStmt = connect.prepareStatement(recoverySQL);
            ResultSet rs = contractStmt.executeQuery();
            
            if(rs.next()){
                NBAPlayer contractPlayer = new NBAPlayer(
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
                PreparedStatement extendedStmt = connect.prepareStatement(insertSQL);
                
                extendedStmt.setString(1, contractPlayer.getName());
                extendedStmt.setString(2, contractPlayer.getTeam());
                extendedStmt.setString(3, contractPlayer.getPosition());
                extendedStmt.setDouble(4, contractPlayer.getHeight());
                extendedStmt.setDouble(5, contractPlayer.getWeight());
                extendedStmt.setDouble(6, contractPlayer.getPoints());
                extendedStmt.setDouble(7, contractPlayer.getRebounds());
                extendedStmt.setDouble(8, contractPlayer.getAssists());
                extendedStmt.setDouble(9, contractPlayer.getBlocks());
                extendedStmt.setDouble(10, contractPlayer.getSteals());
                extendedStmt.setDouble(11, contractPlayer.getSalary());
                
                extendedStmt.executeUpdate();
                extendedStmt.close();
                
                String deleteSql = "DELETE FROM contract WHERE id = ?";
                PreparedStatement deleteStmt = connect.prepareStatement(deleteSql);
                deleteStmt.setInt(1, rs.getInt("id"));
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }
            contractStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void ContractQueueList(){
        try{
            String viewSQL = "select * from contract order by id asc";
            PreparedStatement viewStmt = connect.prepareStatement(viewSQL);
            ResultSet rs = viewStmt.executeQuery();
            
            while(rs.next()){
                System.out.println("Contract extended");
                System.out.println("Name : "+rs.getString("name"));
            }
            viewStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
