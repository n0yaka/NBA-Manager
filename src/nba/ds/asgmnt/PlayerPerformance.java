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
public class PlayerPerformance {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";
    List<NBAPlayer> players = new ArrayList<>();
    Connection connect = null;
    
    public PlayerPerformance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<NBAPlayer> allPlayers(){
        players.clear();
        try{
            String sql = "select * from firstteam";
            PreparedStatement performStmt = connect.prepareStatement(sql);
            ResultSet rs = performStmt.executeQuery();
            
            while(rs.next()){
                NBAPlayer forwardPlayer = new NBAPlayer(
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
                
                players.add(forwardPlayer);
            }
            performStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return players;
    }
    
    public void sortGuardsByPerformance() {
        List<NBAPlayer> guards = new ArrayList<>();
        
        for (NBAPlayer player : players) {
            if(player.getPosition().equalsIgnoreCase("guard")) guards.add(player);
        }
        guards.sort(Comparator.comparingDouble(NBAPlayer::getPoints)
                              .thenComparingDouble(NBAPlayer::getAssists)
                              .thenComparingDouble(NBAPlayer::getSteals)
                              .reversed());
        CompositeScoreGuards(guards);
        printPlayers(guards);
    }
    public void sortForwardsByPerformance() {
        List<NBAPlayer> forwards = new ArrayList<>();
        
        for (NBAPlayer player : players) {
            if(player.getPosition().equalsIgnoreCase("forward")) forwards.add(player);
        }
        players.sort(Comparator.comparingDouble(NBAPlayer::getPoints)
                                .thenComparingDouble(NBAPlayer::getRebounds)
                                .thenComparingDouble(NBAPlayer::getSteals)
                                .reversed());
        CompositeScoreForwards(forwards);
        printPlayers(forwards);
    }
    public void sortCentersByPerformance() {
        List<NBAPlayer> centers = new ArrayList<>();
        
        for (NBAPlayer player : players) {
            if(player.getPosition().equalsIgnoreCase("center")) centers.add(player);
        }
        players.sort(Comparator.comparingDouble(NBAPlayer::getHeight)
                               .thenComparingDouble(NBAPlayer::getWeight)
                               .thenComparingDouble(NBAPlayer::getRebounds)
                               .thenComparingDouble(NBAPlayer::getBlocks)
                               .reversed());
        CompositeScoreCenters(centers);
        printPlayers(centers);
    }
    
    public void CompositeScoreForwards(List<NBAPlayer> players){
        for (NBAPlayer player : players) {
            double score = (player.getPoints()*0.30) + (player.getAssists()*0.15) + (player.getRebounds()*0.25) + (player.getSteals()*0.25) + (player.getBlocks()*0.05);
            player.setScore(score);
        }
    }
    
    public void CompositeScoreGuards(List<NBAPlayer> players){
        for (NBAPlayer player : players) {
            double score = (player.getPoints()*0.30) + (player.getAssists()*0.25) + (player.getRebounds()*0.15) + (player.getSteals()*0.25) + (player.getBlocks()*0.05);
            player.setScore(score);
        }
    }
    
    public void CompositeScoreCenters(List<NBAPlayer> players){
        for (NBAPlayer player : players) {
            double score = (player.getPoints()*0.15) + (player.getAssists()*0.15) + (player.getRebounds()*0.30) + (player.getSteals()*0.10) + (player.getBlocks()*0.30);
            player.setScore(score);
        }
    }
    
    public void sortPlayersByCompositeScore(List<NBAPlayer> players) {
        players.sort(Comparator.comparingDouble(NBAPlayer::getScore).reversed());
    }
    
    public void printPlayers(List<NBAPlayer> players) {
        sortPlayersByCompositeScore(players);
        for (NBAPlayer player : players) {
            System.out.printf("%s - Composite Score: %.2f\n", player.getName(), player.getScore());
        }
    }
}
