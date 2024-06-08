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
    
     // Method to display the graph
    public void displayGraph() {
        Graph graph = new Graph();
        String[] teams = {"Spurs", "Warriors", "Celtics", "Heat", "Lakers", "Suns", "Magic", "Nuggets", "Thunder", "Rockets"};

        for (String team : teams) {
            graph.addVertex(team);
        }

        graph.addEdge("Spurs", "Suns", 500);
        graph.addEdge("Spurs", "Thunder", 678);
        graph.addEdge("Spurs", "Rockets", 983);
        graph.addEdge("Spurs", "Magic", 1137);
        graph.addEdge("Suns", "Lakers", 577);
        graph.addEdge("Suns", "Spurs", 500);
        graph.addEdge("Thunder", "Lakers", 1901);
        graph.addEdge("Thunder", "Warriors", 2214);
        graph.addEdge("Thunder", "Nuggets", 942);
        graph.addEdge("Thunder", "Rockets", 778);
        graph.addEdge("Thunder", "Spurs", 678);
        graph.addEdge("Warriors", "Lakers", 554); 
        graph.addEdge("Warriors", "Nuggets", 1507);
        graph.addEdge("Warriors", "Thunder", 2214);
        graph.addEdge("Magic", "Rockets", 458);
        graph.addEdge("Magic", "Heat", 268);
        graph.addEdge("Magic", "Spurs", 1137);
        graph.addEdge("Celtics", "Nuggets", 2845);
        graph.addEdge("Celtics", "Rockets", 2584);
        graph.addEdge("Celtics", "Heat", 3045);
        graph.addEdge("Heat", "Magic", 268);
        graph.addEdge("Heat", "Celtics", 3045);
        graph.addEdge("Rockets", "Thunder", 778);
        graph.addEdge("Rockets", "Spurs", 983);
        graph.addEdge("Rockets", "Magic", 458);
        graph.addEdge("Rockets", "Celtics", 2584);
        graph.addEdge("Nuggets", "Warriors", 1507);
        graph.addEdge("Nuggets", "Thunder", 942);
        graph.addEdge("Nuggets", "Celtics", 2845);
        graph.addEdge("Lakers", "Warriors", 554);
        graph.addEdge("Lakers", "Thunder", 1901);
        graph.addEdge("Lakers", "Suns", 577);

        // Display the graph
        graph.displayGraph();
        
        // Create and display the GUI
        GraphGUI.createAndShowGUI(graph);
    }

    // Main method to run the program
    public static void main(String[] args) {
        NBA nba = new NBA();
        HandleFirstTeam add = new HandleFirstTeam();
        Injuries injury = new Injuries();
        Contract contract = new Contract();
        PlayerPerformance stats = new PlayerPerformance();
        DynamicSearch search = new DynamicSearch();
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println("1 : Points \n"
                + "2 : assists\n"
                + "3 : add\n"
                + "4 : remove\n"
                + "5 : check\n"
                + "6 : injury\n"
                + "7 : contract\n"
                + "8 : performance\n"
                + "9 : dynamic search\n"
                    + "10 : graph\n"       
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
                        scanner.nextLine();
                        System.out.print("Injury : ");
                        String injuryReason = scanner.nextLine();
                        injury.addToInjuryStack(injuryId, injuryReason);
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
            case 7 :
                System.out.println("1 : add to queue\n"
                        + "2 : extend contract\n"
                        + "3 : queue list");
                int contractChoice = scanner.nextInt();
                switch(contractChoice){
                    case 1:
                        System.out.println("player id");
                        int contractId = scanner.nextInt();
                        contract.ContractExtension(contractId);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("Extending contract...");
                        contract.ExtendContract();
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Queue list :");
                        contract.ContractQueueList();
                        System.out.println();
                        break;
                }
                break;
            case 8 :
                stats.allPlayers();
                System.out.println("1 : forwards\n"
                        + "2 : centers\n"
                        + "3 : guards");
                int performChoice = scanner.nextInt();
                switch(performChoice){
                    case 1:
                        stats.sortForwardsByPerformance();
                        break;
                    case 2:
                        stats.sortCentersByPerformance();
                        break;
                    case 3:
                        stats.sortGuardsByPerformance();
                        break;
                }
                break;
            case 9:
                    System.out.println("Enter search criteria:");
                    System.out.print("Name (leave empty for no filter): ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Team (leave empty for no filter): ");
                    String team = scanner.nextLine().trim();
                    System.out.print("Position (leave empty for no filter): ");
                    String position = scanner.nextLine().trim();
                    System.out.print("Min Points per Game (leave empty for no filter): ");
                    String minPointsStr = scanner.nextLine().trim();
                    Double minPoints = minPointsStr.isEmpty() ? null : Double.valueOf(minPointsStr);
                    System.out.print("Max Points per Game (leave empty for no filter): ");
                    String maxPointsStr = scanner.nextLine().trim();
                    Double maxPoints = maxPointsStr.isEmpty() ? null : Double.valueOf(maxPointsStr);
                    System.out.print("Min Rebounds per Game (leave empty for no filter): ");
                    String minReboundsStr = scanner.nextLine().trim();
                    Double minRebounds = minReboundsStr.isEmpty() ? null : Double.valueOf(minReboundsStr);
                    System.out.print("Max Rebounds per Game (leave empty for no filter): ");
                    String maxReboundsStr = scanner.nextLine().trim();
                    Double maxRebounds = maxReboundsStr.isEmpty() ? null : Double.valueOf(maxReboundsStr);

                    List<NBAPlayer> searchResults = search.searchPlayers(name, team, position, minPoints, maxPoints, minRebounds, maxRebounds);
                    System.out.println("Search Results:");
                    for (NBAPlayer player : searchResults) {
                        System.out.println(player);
                    }
                    break;
            case 10:
                    nba.displayGraph();
                    break;
            case -1 :
                return;
            }
        }
        
    }
    
}

