package nba.ds.asgmnt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.table.TableColumnModel;

public class App extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/nba";
    private static final String USER = "root";
    private static final String PASSWORD = "afiqahnajla21";
    
    private JTextArea textArea;
    private JButton viewButton;
    private JButton injuryButton;
    private JButton AddPlayer;
    private JButton removeButton;
    private JButton checkerButton;
    private JButton contractButton;
    private JButton extendContractButton;
    private JButton ExtendPlayerContract;
    private JButton Performance;
    private JButton nbaCity;
    private JButton freeAgentButton;
    private JButton searchButton;
    
    HandleFirstTeam handle = new HandleFirstTeam();
    Injuries injury = new Injuries();
    Contract contract = new Contract();
    PlayerPerformance score = new PlayerPerformance();
    
    private Graph nbaGraph; 
    
    public App() {
        setTitle("NBA Management App");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        viewButton = new JButton("First Team");
        AddPlayer = new JButton("Add Player");
        removeButton = new JButton("Remove Player");
        injuryButton = new JButton("Injuries");
        checkerButton = new JButton("Composition check");
        contractButton = new JButton("Contracts");
        extendContractButton = new JButton("Add Contract Extension List");
        ExtendPlayerContract = new JButton("Initiliase Contract Extension");
        Performance = new JButton("Player Performance");
        nbaCity = new JButton("City");
        freeAgentButton = new JButton("Free Agents");
        searchButton = new JButton("Search Players");
        
        searchButton.addActionListener(e -> showSearchDialog());
        
        nbaCity.addActionListener(e -> displayNbaCityGraph());

        freeAgentButton.addActionListener(e -> showFreeAgent());
        
        Performance.addActionListener(e -> handlePerformanceButton());
        
        checkerButton.addActionListener(e -> TeamCompChecker());
        
        ExtendPlayerContract.addActionListener(e -> contract.ExtendContract());
        
        contractButton.addActionListener(e -> ContractList());
        
        extendContractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerIdInput = JOptionPane.showInputDialog("Enter Player ID:");
                if (playerIdInput != null && !playerIdInput.isEmpty()) {
                    int playerId = Integer.parseInt(playerIdInput);
                    contract.ContractExtension(playerId);
                }
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFirstTeam();
            }
        });

        injuryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageInjuries();
            }
        });
        
        AddPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerIdInput = JOptionPane.showInputDialog("Enter Player ID:");
                if (playerIdInput != null && !playerIdInput.isEmpty()) {
                    int playerId = Integer.parseInt(playerIdInput);
                    addToFirstTeam(playerId);
                }
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerIdInput = JOptionPane.showInputDialog("Enter Player ID:");
                if (playerIdInput != null && !playerIdInput.isEmpty()) {
                    int playerId = Integer.parseInt(playerIdInput);
                    removeFromFirstTeam(playerId);
                }
            }

            private void removeFromFirstTeam(int playerId) {
                handle.RemovePlayer(playerId);
            }
        });
        
        nbaCity = new JButton("City");
        nbaCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNbaCityGraph();
            }
        });


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        buttonPanel.add(injuryButton);
        buttonPanel.add(contractButton);
        buttonPanel.add(nbaCity);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }
    
     private void displayNbaCityGraph() {
        nbaGraph = new Graph();

        // Adding vertices (team names)
        String[] teams = {
            "Spurs", "Warriors", "Celtics", "Heat",
            "Lakers", "Suns", "Magic", "Nuggets",
            "Thunder", "Rockets"
        };

        for (String team : teams) {
            nbaGraph.addVertex(team);
        }

        // Adding edges with weights 
        nbaGraph.addEdge("Spurs", "Suns", 500);
        nbaGraph.addEdge("Spurs", "Thunder", 678);
        nbaGraph.addEdge("Spurs", "Rockets", 983);
        nbaGraph.addEdge("Spurs", "Magic", 1137);

        nbaGraph.addEdge("Suns", "Lakers", 577);
        nbaGraph.addEdge("Suns", "Spurs", 500);

        nbaGraph.addEdge("Thunder", "Lakers", 1901);
        nbaGraph.addEdge("Thunder", "Warriors", 2214);
        nbaGraph.addEdge("Thunder", "Nuggets", 942);
        nbaGraph.addEdge("Thunder", "Rockets", 778);
        nbaGraph.addEdge("Thunder", "Spurs", 678);

        nbaGraph.addEdge("Warriors", "Lakers", 554); 
        nbaGraph.addEdge("Warriors", "Nuggets", 1507);
        nbaGraph.addEdge("Warriors", "Thunder", 2214);

        nbaGraph.addEdge("Magic", "Rockets", 458);
        nbaGraph.addEdge("Magic", "Heat", 268);
        nbaGraph.addEdge("Magic", "Spurs", 1137);

        nbaGraph.addEdge("Celtics", "Nuggets", 2845);
        nbaGraph.addEdge("Celtics", "Rockets", 2584);
        nbaGraph.addEdge("Celtics", "Heat", 3045);

        nbaGraph.addEdge("Heat", "Magic", 268);
        nbaGraph.addEdge("Heat", "Celtics", 3045);

        nbaGraph.addEdge("Rockets", "Thunder", 778);
        nbaGraph.addEdge("Rockets", "Spurs", 983);
        nbaGraph.addEdge("Rockets", "Magic", 458);
        nbaGraph.addEdge("Rockets", "Celtics", 2584);

        nbaGraph.addEdge("Nuggets", "Warriors", 1507);
        nbaGraph.addEdge("Nuggets", "Thunder", 942);
        nbaGraph.addEdge("Nuggets", "Celtics", 2845);

        nbaGraph.addEdge("Lakers", "Warriors", 554);
        nbaGraph.addEdge("Lakers", "Thunder", 1901);
        nbaGraph.addEdge("Lakers", "Suns", 577);
        
        nbaGraph.displayGraph();

        // Run BFS, DFS and heuristic TSP
        Map<String, Integer> dijkstraOrder = nbaGraph.dijkstra("Spurs");
        List<String> bfsOrder = nbaGraph.bfs("Spurs");
        List<String> dfsOrder = nbaGraph.dfs("Spurs");
        List<String> tspOrder = nbaGraph.heuristicTSP("Spurs");

        GraphGUI.createAndShowGUI(nbaGraph, dijkstraOrder, bfsOrder, dfsOrder, tspOrder);
    }
   
    private void viewFirstTeam() {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM firstteam")) {

        Object[][] data = new Object[100][12]; 
        int rowCount = 0;
        String[] columnNames = {"ID", "Name", "Team", "Position", "Height", "Weight", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Salary"};
        while (rs.next()) {
            int id = rs.getInt("id");
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

            data[rowCount++] = new Object[]{id, name, team, position, height, weight, points, rebounds, assists, blocks, steals, salary};
        }
        Object[][] truncatedData = new Object[rowCount][12];
        System.arraycopy(data, 0, truncatedData, 0, rowCount);
        JTable table = new JTable(truncatedData, columnNames);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); 
        columnModel.getColumn(1).setPreferredWidth(150); 
        columnModel.getColumn(2).setPreferredWidth(100); 
        
        JButton addPlayerButton = new JButton("Injured Player");
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerIdInput = JOptionPane.showInputDialog("Enter Player ID:");
                if (playerIdInput != null && !playerIdInput.isEmpty()) {
                    int playerId = Integer.parseInt(playerIdInput);
                    String reason = JOptionPane.showInputDialog("Enter Injury Reason:");
                    if (reason != null && !reason.isEmpty()) {
                        injury.addToInjuryStack(playerId, reason);
                        JOptionPane.showMessageDialog(null, "Injury added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid injury reason.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid player ID.");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        textArea.setText("");
        
        JPanel topButtonPanel = new JPanel(); 
        topButtonPanel.add(freeAgentButton);
        topButtonPanel.add(viewButton);
        topButtonPanel.add(injuryButton); 
        topButtonPanel.add(contractButton);
        topButtonPanel.add(nbaCity);

        JPanel bottomButtonPanel = new JPanel(); 
        bottomButtonPanel.add(AddPlayer);
        bottomButtonPanel.add(removeButton);
        bottomButtonPanel.add(checkerButton);
        bottomButtonPanel.add(addPlayerButton);
        bottomButtonPanel.add(extendContractButton);
        bottomButtonPanel.add(Performance);
        bottomButtonPanel.add(searchButton);

        getContentPane().removeAll(); 
        getContentPane().add(topButtonPanel, BorderLayout.NORTH); // Add the top button panel
        getContentPane().add(scrollPane, BorderLayout.CENTER); 
        getContentPane().add(bottomButtonPanel, BorderLayout.SOUTH); // Add the bottom button panel
        revalidate();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    public void addToFirstTeam(int id) {
        handle.addToFirstTeam(id);
    }

    private void manageInjuries() {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM injury")) {
        Object[][] data = new Object[100][4]; // Assuming a maximum of 100 injuries
        int rowCount = 0;
        String[] columnNames = {"Name", "Team", "Position", "Injury"};
        while (rs.next()) {
            String name = rs.getString("name");
            String team = rs.getString("team");
            String position = rs.getString("position");
            String reason = rs.getString("injury");
            data[rowCount++] = new Object[]{name, team, position, reason};
        }
        Object[][] truncatedData = new Object[rowCount][4];
        System.arraycopy(data, 0, truncatedData, 0, rowCount);
        JTable table = new JTable(truncatedData, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(table);
        
        JButton homeButton = new JButton("First Team");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFirstTeam(); // Go back to the main screen
            }
        });
        
        JButton injuryButton = new JButton("Injuries");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageInjuries();
            }
        });

        

        JButton removePlayerButton = new JButton("Recover Player");
        removePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                injury.removeFromInjuryStack();
            }
        });

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(freeAgentButton);
        topButtonPanel.add(homeButton);
        topButtonPanel.add(injuryButton);
        topButtonPanel.add(contractButton);
        topButtonPanel.add(nbaCity);
        
        JPanel bottomButtonPanel = new JPanel(); // Panel for bottom buttons
        bottomButtonPanel.add(removePlayerButton);

        getContentPane().removeAll(); 
        getContentPane().add(topButtonPanel, BorderLayout.NORTH); 
        getContentPane().add(scrollPane, BorderLayout.CENTER); 
        getContentPane().add(bottomButtonPanel, BorderLayout.SOUTH); 
        revalidate();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    public void TeamCompChecker() {
    JFrame compositionFrame = new JFrame("Team Composition");
    compositionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    compositionFrame.setSize(400, 300);
    compositionFrame.setLocationRelativeTo(null);
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement checkStmt = conn.createStatement();
         ResultSet checkRs = checkStmt.executeQuery("SELECT position, salary FROM firstteam")){

        int currentPlayers = 0, guards = 0, centers = 0, forwards = 0;
        double totalSalary = 0;

        while (checkRs.next()) {
            currentPlayers++;
            String position = checkRs.getString("position");
            double salary = checkRs.getDouble("salary");
            totalSalary += salary;

            switch (position) {
                case "Guard":
                    guards++;
                    break;
                case "Center":
                    centers++;
                    break;
                case "Forward":
                    forwards++;
                    break;
            }
        }
        checkStmt.close();
        checkRs.close();

        JTextArea compositionTextArea = new JTextArea();
        compositionTextArea.append("Current team composition:\n");
        compositionTextArea.append("Total players: " + currentPlayers + "\n");
        compositionTextArea.append("Guards: " + guards + "\n");
        compositionTextArea.append("Centers: " + centers + "\n");
        compositionTextArea.append("Forwards: " + forwards + "\n");
        compositionTextArea.append("Total salary: " + totalSalary + "\n");

        if (currentPlayers > 15) {
            compositionTextArea.append("Exceed team limit\n");
        }
        if (guards >= 2) {
            compositionTextArea.append("Min 2 guards requirements' success\n");
        }
        if (guards < 2) {
            compositionTextArea.append("Not enough guards\n");
        }
        if (centers >= 2) {
            compositionTextArea.append("Min 2 centers requirements' success\n");
        }
        if (forwards >= 2) {
            compositionTextArea.append("Min 2 forwards requirements' success\n");
        }
        if (totalSalary > 20000) {
            compositionTextArea.append("Min 2 guards requirements' success\n");
        }

        JScrollPane scrollPane = new JScrollPane(compositionTextArea);
        compositionFrame.add(scrollPane);
        compositionFrame.setVisible(true);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void ContractList() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM contract")) {

        Object[][] data = new Object[100][12]; 
        int rowCount = 0;
        String[] columnNames = {"ID", "Name", "Team", "Position", "Height", "Weight", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Salary"};
        while (rs.next()) {
            int id = rs.getInt("id");
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

            data[rowCount++] = new Object[]{id, name, team, position, height, weight, points, rebounds, assists, blocks, steals, salary};
        }
        Object[][] truncatedData = new Object[rowCount][12];
        System.arraycopy(data, 0, truncatedData, 0, rowCount);
        JTable table = new JTable(truncatedData, columnNames);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); 
        columnModel.getColumn(1).setPreferredWidth(150); 
        columnModel.getColumn(2).setPreferredWidth(100); 
        

        JScrollPane scrollPane = new JScrollPane(table);
        textArea.setText("");
        
        JPanel topButtonPanel = new JPanel(); 
        topButtonPanel.add(freeAgentButton);
        topButtonPanel.add(viewButton);
        topButtonPanel.add(injuryButton); 
        topButtonPanel.add(contractButton);
        topButtonPanel.add(nbaCity);

        JPanel bottomButtonPanel = new JPanel(); 
        bottomButtonPanel.add(ExtendPlayerContract);
        

        getContentPane().removeAll(); 
        getContentPane().add(topButtonPanel, BorderLayout.NORTH); // Add the top button panel
        getContentPane().add(scrollPane, BorderLayout.CENTER); 
        getContentPane().add(bottomButtonPanel, BorderLayout.SOUTH); // Add the bottom button panel
        revalidate();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    private void handlePerformanceButton() {
    JFrame performanceFrame = new JFrame("Player Performance");
    performanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    performanceFrame.setSize(300, 200);
    performanceFrame.setLocationRelativeTo(null);

    JButton forwardButton = new JButton("Forwards");
    JButton centerButton = new JButton("Centers");
    JButton guardButton = new JButton("Guards");

    forwardButton.addActionListener(e -> showPerformance("forward"));
    centerButton.addActionListener(e -> showPerformance("center"));
    guardButton.addActionListener(e -> showPerformance("guard"));

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(forwardButton);
    buttonPanel.add(centerButton);
    buttonPanel.add(guardButton);

    performanceFrame.add(buttonPanel);
    performanceFrame.setVisible(true);
}
    

private void showPerformance(String position) {
        JFrame performanceFrame = new JFrame("Player Performance - " + position.toUpperCase());
        performanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        performanceFrame.setSize(400, 300);
        performanceFrame.setLocationRelativeTo(null);

        JTextArea performanceTextArea = new JTextArea();
        performanceTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(performanceTextArea);
        performanceFrame.add(scrollPane);

        PlayerPerformance playerPerformance = new PlayerPerformance();
        List<NBAPlayer> players;

        switch (position) {
            case "forward":
                players = playerPerformance.sortForwardsByPerformance();
                break;
            case "center":
                players = playerPerformance.sortCentersByPerformance();
                break;
            case "guard":
                players = playerPerformance.sortGuardsByPerformance();
                break;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
        
        players.sort(Comparator.comparingDouble(NBAPlayer::getScore).reversed());
        performanceTextArea.append("Player Performance - " + position.toUpperCase() + ":\n\n");
        performanceTextArea.append("Name\t\t\tScore\n");
        performanceTextArea.append("\n");
        for (NBAPlayer player : players) {
            performanceTextArea.append(player.getName() + "\t\t\t" + String.format("%.2f", player.getScore()) + "\n");
        }

        performanceFrame.setVisible(true);
    }
    
    private void showFreeAgent() {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM players")){
            
            Object[][] data = new Object[100][12]; 
        int rowCount = 0;
        String[] columnNames = {"ID", "Name", "Team", "Position", "Height", "Weight", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Salary"};
        while (rs.next()) {
            int id = rs.getInt("id");
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

            data[rowCount++] = new Object[]{id, name, team, position, height, weight, points, rebounds, assists, blocks, steals, salary};
        }
        Object[][] truncatedData = new Object[rowCount][12];
        System.arraycopy(data, 0, truncatedData, 0, rowCount);
        JTable table = new JTable(truncatedData, columnNames);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); 
        columnModel.getColumn(1).setPreferredWidth(150); 
        columnModel.getColumn(2).setPreferredWidth(100); 
        

        JScrollPane scrollPane = new JScrollPane(table);
        textArea.setText("");
        
        JPanel topButtonPanel = new JPanel(); 
        topButtonPanel.add(freeAgentButton);
        topButtonPanel.add(viewButton);
        topButtonPanel.add(injuryButton); 
        topButtonPanel.add(contractButton);
        topButtonPanel.add(nbaCity);

        getContentPane().removeAll(); 
        getContentPane().add(topButtonPanel, BorderLayout.NORTH); // Add the top button panel
        getContentPane().add(scrollPane, BorderLayout.CENTER); 
        revalidate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void showSearchDialog() {
        JDialog searchDialog = new JDialog(this, "Search Players", true);
        searchDialog.setSize(400, 400);
        searchDialog.setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel teamLabel = new JLabel("Team:");
        JTextField teamField = new JTextField();

        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField();

        JLabel minPointsLabel = new JLabel("Min Points:");
        JTextField minPointsField = new JTextField();

        JLabel maxPointsLabel = new JLabel("Max Points:");
        JTextField maxPointsField = new JTextField();

        JLabel minReboundsLabel = new JLabel("Min Rebounds:");
        JTextField minReboundsField = new JTextField();

        JLabel maxReboundsLabel = new JLabel("Max Rebounds:");
        JTextField maxReboundsField = new JTextField();

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String team = teamField.getText();
                String position = positionField.getText();
                Double minPoints = minPointsField.getText().isEmpty() ? null : Double.parseDouble(minPointsField.getText());
                Double maxPoints = maxPointsField.getText().isEmpty() ? null : Double.parseDouble(maxPointsField.getText());
                Double minRebounds = minReboundsField.getText().isEmpty() ? null : Double.parseDouble(minReboundsField.getText());
                Double maxRebounds = maxReboundsField.getText().isEmpty() ? null : Double.parseDouble(maxReboundsField.getText());

                DynamicSearch dynamicSearch = new DynamicSearch();
                List<NBAPlayer> players = dynamicSearch.searchPlayers(name, team, position, minPoints, maxPoints, minRebounds, maxRebounds);

                displaySearchResults(players);
                searchDialog.dispose();
            }
        });


        searchDialog.add(nameLabel);
        searchDialog.add(nameField);
        searchDialog.add(teamLabel);
        searchDialog.add(teamField);
        searchDialog.add(positionLabel);
        searchDialog.add(positionField);
        searchDialog.add(minPointsLabel);
        searchDialog.add(minPointsField);
        searchDialog.add(maxPointsLabel);
        searchDialog.add(maxPointsField);
        searchDialog.add(minReboundsLabel);
        searchDialog.add(minReboundsField);
        searchDialog.add(maxReboundsLabel);
        searchDialog.add(maxReboundsField);
        searchDialog.add(new JLabel());
        searchDialog.add(searchButton);

        searchDialog.setVisible(true);
    }
 

    
    private void displaySearchResults(List<NBAPlayer> players) {
        String[] columnNames = {"ID", "Name", "Team", "Position", "Height", "Weight", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Salary"};
        Object[][] data = new Object[players.size()][12];

        int rowCount = 0;
        for (NBAPlayer player : players) {
            data[rowCount++] = new Object[]{player.getId(), player.getName(), player.getTeam(), player.getPosition(), player.getHeight(), player.getWeight(), player.getPoints(), player.getRebounds(), player.getAssists(), player.getBlocks(), player.getSteals(), player.getSalary()};
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame resultsFrame = new JFrame("Search Results");
        resultsFrame.setSize(800, 600);
        resultsFrame.add(scrollPane);
        resultsFrame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}

class Graph {
    public class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + destination + ", " + weight + ")";
        }
    }

    private final Map<String, LinkedList<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new LinkedList<>());
    }

    public void addEdge(String source, String destination, int weight) {
        adjList.get(source).add(new Edge(destination, weight));
    }

    public Map<String, LinkedList<Edge>> getAdjList() {
        return adjList;
    }

    public void displayGraph() {
        for (String vertex : adjList.keySet()) {
            System.out.print("Team " + vertex + " is connected to: ");
            for (Edge edge : adjList.get(vertex)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    public Map<String, Integer> dijkstra(String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<String> visited = new HashSet<>();

        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            if (!visited.add(current)) {
                continue;
            }

            for (Edge edge : adjList.get(current)) {
                if (visited.contains(edge.destination)) {
                    continue;
                }

                int newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    pq.add(edge.destination);
                }
            }
        }

        return distances;
    }

    public List<String> bfs(String start) {
        List<String> order = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    queue.add(edge.destination);
                    visited.add(edge.destination);
                }
            }
        }

        return order;
    }

    public List<String> dfs(String start) {
        List<String> order = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (!visited.add(current)) {
                continue;
            }

            order.add(current);

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    stack.push(edge.destination);
                }
            }
        }

        return order;
    }

    public List<String> heuristicTSP(String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        String current = start;
        order.add(current);
        visited.add(current);

        while (visited.size() < adjList.size()) {
            Edge closest = null;
            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination) && (closest == null || edge.weight < closest.weight)) {
                    closest = edge;
                }
            }

            if (closest == null) {
                break; // No more reachable vertices
            }

            current = closest.destination;
            order.add(current);
            visited.add(current);
        }

        return order;
    }
}

class GraphGUI {
    public static void createAndShowGUI(Graph graph, Map<String, Integer> dijkstraOrder,
                                        java.util.List<String> bfsOrder, java.util.List<String> dfsOrder,
                                        java.util.List<String> tspOrder) {
        JFrame frame = new JFrame("NBA Teams Travel Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        GraphPanel graphPanel = new GraphPanel(graph);
        JPanel infoPanel = createInfoPanel(graph, dijkstraOrder, bfsOrder, dfsOrder, tspOrder);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphPanel, infoPanel);
        splitPane.setResizeWeight(0.7);
        
        frame.add(splitPane);
        frame.setVisible(true);
    }

    private static JPanel createInfoPanel(Graph graph, Map<String, Integer> dijkstraOrder,
                                          java.util.List<String> bfsOrder, java.util.List<String> dfsOrder,
                                          java.util.List<String> tspOrder) {
        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        
        JPanel dijkstraPanel = createPanelWithBorder("Dijkstra Order", createTextArea(dijkstraOrder));
        JPanel bfsPanel = createPanelWithBorder("BFS Order", createTextArea(graph, bfsOrder));
        JPanel dfsPanel = createPanelWithBorder("DFS Order", createTextArea(graph, dfsOrder));
        JPanel tspPanel = createPanelWithBorder("Heuristic TSP Order", createTextArea(graph, tspOrder));

        infoPanel.add(dijkstraPanel);
        infoPanel.add(bfsPanel);
        infoPanel.add(dfsPanel);
        infoPanel.add(tspPanel);

        return infoPanel;
    }
    
    private static JPanel createPanelWithBorder(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(new JScrollPane(textArea));
        return panel;
    }
    
    private static JTextArea createTextArea(Map<String, Integer> order) {
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        textArea.append("Total Distance: " + calculateTotalDistance(order) + " km\n\n");
        order.forEach((team, distance) -> textArea.append(team + ": " + distance + " km\n"));
        return textArea;
    }

    private static JTextArea createTextArea(Graph graph, java.util.List<String> order) {
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        textArea.append("Total Distance: " + calculateTotalDistance(graph, order) + " km\n\n");
        order.forEach(team -> textArea.append(team + "\n"));
        return textArea;
    }

    private static int calculateTotalDistance(Map<String, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static int calculateTotalDistance(Graph graph, java.util.List<String> order) {
        int totalDistance = 0;
        
        for (int i = 0; i < order.size() - 1; i++) {
            String current = order.get(i);
            String next = order.get(i + 1);
            for (Graph.Edge edge : graph.getAdjList().get(current)) {
                if (edge.destination.equals(next)) {
                    totalDistance += edge.weight;
                    break;
                }
            }
        }
        return totalDistance;
    }
}

class GraphPanel extends JPanel {
     private Graph graph;
    private Map<String, Point> nodePositions;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        nodePositions = new HashMap<>();
        setPreferredSize(new Dimension(800, 600));
        initializeNodePositions();
    }

    private void initializeNodePositions() {
        // Manually setting positions for simplicity
        nodePositions.put("Spurs", new Point(400, 500));
        nodePositions.put("Warriors", new Point(100, 100));
        nodePositions.put("Celtics", new Point(700, 100));
        nodePositions.put("Heat", new Point(700, 500));
        nodePositions.put("Lakers", new Point(80, 200));
        nodePositions.put("Suns", new Point(300, 400));
        nodePositions.put("Magic", new Point(600, 400));
        nodePositions.put("Nuggets", new Point(400, 100));
        nodePositions.put("Thunder", new Point(400, 300));
        nodePositions.put("Rockets", new Point(500, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        Map<String, LinkedList<Graph.Edge>> adjList = graph.getAdjList();
        g.setColor(Color.BLACK);

        // Draw edges
        for (String vertex : adjList.keySet()) {
            Point src = nodePositions.get(vertex);
            for (Graph.Edge edge : adjList.get(vertex)) {
                Point dest = nodePositions.get(edge.destination);
                g.drawLine(src.x, src.y, dest.x, dest.y);
                // Draw weight
                int midX = (src.x + dest.x) / 2;
                int midY = (src.y + dest.y) / 2;
                g.drawString(edge.weight + "km", midX, midY);
            }
        }

        // Draw vertices
        for (String vertex : nodePositions.keySet()) {
            Point pos = nodePositions.get(vertex);
            g.setColor(Color.RED);
            g.fillOval(pos.x - 15, pos.y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(vertex, pos.x - 15, pos.y - 20);
        }
    }
}


