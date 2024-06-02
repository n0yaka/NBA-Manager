/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;
import java.sql.*;

public class HandleFirstTeam {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";

    Connection connect = null;

    public HandleFirstTeam() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void TeamCompChecker(){
        try{
            String checkSql = "select position,salary from firstteam";
            Statement checkStmt = connect.createStatement();
            ResultSet checkRs = checkStmt.executeQuery(checkSql);
            
            int currentPlayers = 0, guards = 0 , centers = 0, forwards = 0;
            double totalSalary = 0;
            
            while(checkRs.next()){
                currentPlayers++;
                String position = checkRs.getString("position");
                double salary = checkRs.getDouble("salary");
                totalSalary += salary;
                
                switch(position){
                    case "Guard" :
                        guards++;
                        break;
                    case "Center" :
                        centers++;
                        break;
                    case "Forward" :
                        forwards++;
                        break;
                }
            }
            checkStmt.close();
            checkRs.close();
            
            System.out.println("Current team composition:");
            System.out.println("Total players: " + currentPlayers);
            System.out.println("Guards: " + guards);
            System.out.println("Centers: " + centers);
            System.out.println("Forwards: " + forwards);
            System.out.println("Total salary: " + totalSalary);
            
            if(currentPlayers>15){
                System.out.println("Exceed team limit");
                return;
            }
            if(guards >= 2) System.out.println("Min 2 guards requirments' success");
            if(guards < 2) System.out.println("Not enough guards");
            if(centers >= 2) System.out.println("Min 2 centers requirments' success");
            if(forwards >= 2) System.out.println("Min 2 forwards requirments' success");
            if(totalSalary > 20000) {
                System.out.println("Min 2 guards requirments' success");
                return;
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void RemovePlayer(int id){
        
        try{
            String SQL = "SELECT * FROM firstteam WHERE id = ?";
            PreparedStatement stmt = connect.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
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
                
                String insertSQL = "INSERT INTO players (name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connect.prepareStatement(insertSQL);

                insertStmt.setString(1, name);
                insertStmt.setString(2, team);
                insertStmt.setString(3, position);
                insertStmt.setDouble(4, height);
                insertStmt.setDouble(5, weight);
                insertStmt.setDouble(6, points);
                insertStmt.setDouble(7, rebounds);
                insertStmt.setDouble(8, assists);
                insertStmt.setDouble(9, blocks);
                insertStmt.setDouble(10, steals);
                insertStmt.setDouble(11, salary);

                insertStmt.executeUpdate();
                System.out.println(name+" removed from first team");
                
                String deleteStmt = "delete from firstteam where id = ?";
                PreparedStatement delete = connect.prepareStatement(deleteStmt);
                delete.setInt(1, id);
                delete.executeUpdate();

                insertStmt.close();
                delete.close();
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void addToFirstTeam(int id) {
        try {
            
            String teamComp = "select position,points_per_game,salary from firstteam";
            Statement teamStmt = connect.createStatement();
            ResultSet teamRs = teamStmt.executeQuery(teamComp);
            
            int currentPlayers = 0, guards = 0 , centers = 0, forwards = 0;
            double totalSalary = 0;
            
            
            String SQL = "SELECT * FROM Players WHERE id = ?";
            PreparedStatement stmt = connect.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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
                
                if (position.equals("Guard") && guards >= 2) {
                    System.out.println("Team already has the required number of Guards.");
                    return;
                }
                if (position.equals("Forward") && forwards >= 2) {
                    System.out.println("Team already has the required number of Forwards.");
                    return;
                }
                if (position.equals("Center") && centers >= 2) {
                    System.out.println("Team already has the required number of Centers.");
                    return;
                }

                // Check salary cap limitations
                if (totalSalary + salary > 20000) {
                    System.out.println("Adding this player would exceed the team's salary cap.");
                    return;
                }

                // Check contractual salary guidelines
                if (points > 20.0 && salary < 3000) {
                    System.out.println("Superstar players must have a minimum salary of $3000.");
                    return;
                } else if (points <= 20.0 && salary < 1000) {
                    System.out.println("Non-superstar players must have a minimum salary of $1000.");
                    return;
                }
                
                

                String insertSQL = "INSERT INTO firstTeam (name, team, position, height_cm, weight_kg, points_per_game, rebounds_per_game, assists_per_game, blocks_per_game, steals_per_game, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connect.prepareStatement(insertSQL);

                insertStmt.setString(1, name);
                insertStmt.setString(2, team);
                insertStmt.setString(3, position);
                insertStmt.setDouble(4, height);
                insertStmt.setDouble(5, weight);
                insertStmt.setDouble(6, points);
                insertStmt.setDouble(7, rebounds);
                insertStmt.setDouble(8, assists);
                insertStmt.setDouble(9, blocks);
                insertStmt.setDouble(10, steals);
                insertStmt.setDouble(11, salary);

                insertStmt.executeUpdate();
                System.out.println(name+" added to first team");
                
                String deleteStmt = "delete from players where id = ?";
                PreparedStatement delete = connect.prepareStatement(deleteStmt);
                delete.setInt(1, id);
                delete.executeUpdate();

                insertStmt.close();
                delete.close();
            } else {
                System.out.println("Player not found");
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
