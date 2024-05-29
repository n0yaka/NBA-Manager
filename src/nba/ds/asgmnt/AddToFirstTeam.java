/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;
import java.sql.*;

public class AddToFirstTeam {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";

    Connection connect = null;

    public AddToFirstTeam() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToFirstTeam(int id) {
        try {
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
                System.out.println(name+"added to first team");
                
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
