/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nba.ds.asgmnt;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.*;

public class NBA {
    PlayerDatabase data = new PlayerDatabase();
    Map<Integer, NBAPlayer> nbaMap = data.getNBAPlayers();

    List<NBAPlayer> nbaPlayers = new ArrayList<>(nbaMap.values());
      
    
    
    // Method to sort NBA players by points per game in descending order and print them
    public void printSorted_Points_NBAPlayers() {
        // Sort NBA players by points per game in descending order
        Collections.sort(nbaPlayers, Comparator.comparingDouble(NBAPlayer::getPoints).reversed());

        // Print sorted NBA players
        for (NBAPlayer nbaPlayer : nbaPlayers) {
            System.out.println(nbaPlayer);
        }
    }
    
    public void printSorted_Assists_NBAPlayers() {
        // Sort NBA players by points per game in descending order
        Collections.sort(nbaPlayers, Comparator.comparingDouble(NBAPlayer::getAssists).reversed());

        // Print sorted NBA players
        for (NBAPlayer nbaPlayer : nbaPlayers) {
            System.out.println(nbaPlayer);
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        NBA nba = new NBA();
        AddToFirstTeam add = new AddToFirstTeam();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1 : Points \n"
                + "2 : assists\n"
                + "3 : add");
        int choice = scanner.nextInt();
    
        switch(choice){
            case 1 :
                nba.printSorted_Points_NBAPlayers();
                break;
            case 2 :
                nba.printSorted_Assists_NBAPlayers();
                break;
            case 3 :
                System.out.println("enter player id");
                int id = scanner.nextInt();
                add.addToFirstTeam(id);
                break;
            case 4 :
                System.out.println("enter player id");
                int removeId = scanner.nextInt();
                add.RemovePlayer(removeId);
        }
    }
    
}

