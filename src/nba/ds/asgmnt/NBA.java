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
        HandleFirstTeam add = new HandleFirstTeam();
        Injuries injury = new Injuries();
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println("1 : Points \n"
                + "2 : assists\n"
                + "3 : add\n"
                + "4 : remove\n"
                + "5 : check\n"
                + "6 : injury\n"
                + "-1 : exit");
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
                break;
            case 5 :
                add.TeamCompChecker();
                break;
            case 6 :
                System.out.println("1 : Add injured player\n"
                        + "2 : remove recovered player\n"
                        + "3 : injury list");
                int choiceInjury = scanner.nextInt();
                switch(choiceInjury){
                    case 1 :
                        System.out.println("enter player id");
                        int injuryId = scanner.nextInt();
                        injury.addToInjuryStack(injuryId);
                        break;
                    case 2 :
                        injury.removeFromInjuryStack();
                        break;
                    case 3 :
                        System.out.println("\nInjury list\n");
                        injury.injuryList();
                        break;
                }
                break;
            case -1 :
                return;
            }
        }
        
    }
    
}

