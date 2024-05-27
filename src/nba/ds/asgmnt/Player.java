/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private static final String URL = "jdbc:mysql://localhost:3306/nbaplayers";
    private static final String USER = "root";
    private static final String PASSWORD = "afiqahnajla21";

    public Map<Integer, NBAPlayer> getNBAPlayers() {
        Map<Integer, NBAPlayer> nbaPlayers = new HashMap<>();

        try {
            // Ensure the driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String query = "SELECT id, name, team, position, salary, points_per_game, rebounds_per_game, assists_per_game, steals_per_game, blocks_per_game FROM freeAgents";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String team = rs.getString("team");
                    String position = rs.getString("position");
                    int salary = rs.getInt("salary");
                    double pointsPerGame = rs.getDouble("points_per_game");
                    double reboundsPerGame = rs.getDouble("rebounds_per_game");
                    double assistsPerGame = rs.getDouble("assists_per_game");
                    double stealsPerGame = rs.getDouble("steals_per_game");
                    double blocksPerGame = rs.getDouble("blocks_per_game");

                    NBAPlayer nbaPlayer = new NBAPlayer(id, name, team, position, salary, pointsPerGame, reboundsPerGame, assistsPerGame, stealsPerGame, blocksPerGame);
                    nbaPlayers.put(id, nbaPlayer);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nbaPlayers;
    }
}
