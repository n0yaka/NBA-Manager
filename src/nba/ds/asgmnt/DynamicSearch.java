package nba.ds.asgmnt;

import java.sql.*;
import java.util.*;

public class DynamicSearch {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";
    Connection connect = null;

    public DynamicSearch() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NBAPlayer> searchPlayers(String name, String team, String position, Double minPoints, Double maxPoints, Double minRebounds, Double maxRebounds) {
        List<NBAPlayer> players = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM players WHERE 1=1");
            if (name != null && !name.isEmpty()) {
                sql.append(" AND name LIKE ?");
            }
            if (team != null && !team.isEmpty()) {
                sql.append(" AND team = ?");
            }
            if (position != null && !position.isEmpty()) {
                sql.append(" AND position = ?");
            }
            if (minPoints != null) {
                sql.append(" AND points_per_game >= ?");
            }
            if (maxPoints != null) {
                sql.append(" AND points_per_game <= ?");
            }
            if (minRebounds != null) {
                sql.append(" AND rebounds_per_game >= ?");
            }
            if (maxRebounds != null) {
                sql.append(" AND rebounds_per_game <= ?");
            }

            PreparedStatement stmt = connect.prepareStatement(sql.toString());

            int index = 1;
            if (name != null && !name.isEmpty()) {
                stmt.setString(index++, "%" + name + "%");
            }
            if (team != null && !team.isEmpty()) {
                stmt.setString(index++, team);
            }
            if (position != null && !position.isEmpty()) {
                stmt.setString(index++, position);
            }
            if (minPoints != null) {
                stmt.setDouble(index++, minPoints);
            }
            if (maxPoints != null) {
                stmt.setDouble(index++, maxPoints);
            }
            if (minRebounds != null) {
                stmt.setDouble(index++, minRebounds);
            }
            if (maxRebounds != null) {
                stmt.setDouble(index++, maxRebounds);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NBAPlayer player = new NBAPlayer(
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
                players.add(player);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
}