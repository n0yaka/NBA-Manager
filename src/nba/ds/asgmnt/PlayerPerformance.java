package nba.ds.asgmnt;

import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author radin
 */
class PlayerPerformance {
    private static final String url = "jdbc:mysql://localhost:3306/nba";
    private static final String user = "root";
    private static final String password = "afiqahnajla21";
    private List<NBAPlayer> players = new ArrayList<>();
    private Connection connect;

    public PlayerPerformance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        fetchAllPlayers();
    }

    private void fetchAllPlayers() {
        players.clear();
        try {
            String sql = "SELECT * FROM firstteam";
            PreparedStatement performStmt = connect.prepareStatement(sql);
            ResultSet rs = performStmt.executeQuery();

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
            performStmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NBAPlayer> sortGuardsByPerformance() {
        List<NBAPlayer> guards = new ArrayList<>();

        for (NBAPlayer player : players) {
            if (player.getPosition().equalsIgnoreCase("guard")) guards.add(player);
        }

        guards.sort(Comparator.comparingDouble(NBAPlayer::getPoints)
                .thenComparingDouble(NBAPlayer::getAssists)
                .thenComparingDouble(NBAPlayer::getSteals)
                .reversed());
        CompositeScoreGuards(guards);
        return guards;
    }

    public List<NBAPlayer> sortForwardsByPerformance() {
        List<NBAPlayer> forwards = new ArrayList<>();

        for (NBAPlayer player : players) {
            if (player.getPosition().equalsIgnoreCase("forward")) forwards.add(player);
        }

        forwards.sort(Comparator.comparingDouble(NBAPlayer::getPoints)
                .thenComparingDouble(NBAPlayer::getRebounds)
                .thenComparingDouble(NBAPlayer::getSteals)
                .reversed());
        CompositeScoreForwards(forwards);
        return forwards;
    }

    public List<NBAPlayer> sortCentersByPerformance() {
        List<NBAPlayer> centers = new ArrayList<>();

        for (NBAPlayer player : players) {
            if (player.getPosition().equalsIgnoreCase("center")) centers.add(player);
        }

        centers.sort(Comparator.comparingDouble(NBAPlayer::getHeight)
                .thenComparingDouble(NBAPlayer::getWeight)
                .thenComparingDouble(NBAPlayer::getRebounds)
                .thenComparingDouble(NBAPlayer::getBlocks)
                .reversed());
        CompositeScoreCenters(centers);
        return centers;
    }

    private void CompositeScoreForwards(List<NBAPlayer> players) {
        for (NBAPlayer player : players) {
            double score = (player.getPoints() * 0.30) + (player.getAssists() * 0.15) + (player.getRebounds() * 0.25) + (player.getSteals() * 0.25) + (player.getBlocks() * 0.05);
            player.setScore(score);
        }
    }

    private void CompositeScoreGuards(List<NBAPlayer> players) {
        for (NBAPlayer player : players) {
            double score = (player.getPoints() * 0.30) + (player.getAssists() * 0.25) + (player.getRebounds() * 0.15) + (player.getSteals() * 0.25) + (player.getBlocks() * 0.05);
            player.setScore(score);
        }
    }

    private void CompositeScoreCenters(List<NBAPlayer> players) {
        for (NBAPlayer player : players) {
            double score = (player.getPoints() * 0.15) + (player.getAssists() * 0.15) + (player.getRebounds() * 0.30) + (player.getSteals() * 0.10) + (player.getBlocks() * 0.30);
            player.setScore(score);
        }
    }
}
