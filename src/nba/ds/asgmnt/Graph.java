/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Al Fatih
 */
public class Graph {
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

    private final  Map<String, LinkedList<Edge>> adjList;

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
            if (!visited.add(current)) continue;

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    int newDist = distances.get(current) + edge.weight;
                    if (newDist < distances.get(edge.destination)) {
                        distances.put(edge.destination, newDist);
                        pq.add(edge.destination);
                    }
                }
            }
        }
        return distances;
    }
   
   public java.util.List<String> bfs(String start) {
        java.util.List<String> visitedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitedOrder.add(current);

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    visited.add(edge.destination);
                    queue.add(edge.destination);
                }
            }
        }
        return visitedOrder;
    }

    public java.util.List<String> dfs(String start) {
        java.util.List<String> visitedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            visitedOrder.add(current);

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    visited.add(edge.destination);
                    stack.push(edge.destination);
                }
            }
        }
        return visitedOrder;
    }

    public java.util.List<String> heuristicTSP(String start) {
        java.util.List<String> visitedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        String current = start;

        visitedOrder.add(current);
        visited.add(current);

        while (visited.size() < adjList.size()) {
            Edge minEdge = null;
            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination) && (minEdge == null || edge.weight < minEdge.weight)) {
                    minEdge = edge;
                }
            }
            if (minEdge == null) break;  // No unvisited adjacent nodes
            current = minEdge.destination;
            visitedOrder.add(current);
            visited.add(current);
        }
        return visitedOrder;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Adding vertices (team names)
        String[] teams = {
            "Spurs", "Warriors", "Celtics", "Heat",
            "Lakers", "Suns", "Magic", "Nuggets",
            "Thunder", "Rockets"
        };

        for (String team : teams) {
            graph.addVertex(team);
        }

        // Adding edges with weights 
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
        graph.addEdge("Warriors", "Nuggets",1507);
        graph.addEdge("Warriors", "Thunder", 2214);
        
        graph.addEdge("Magic", "Rockets", 458);
        graph.addEdge("Magic", "Heat",268);
        graph.addEdge("Magic", "Spurs", 1137);
        
        graph.addEdge("Celtics", "Nuggets",2845);
        graph.addEdge("Celtics", "Rockets",2584);
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
        graph.addEdge("Lakers", "Suns", 577 );
        

        // Display the graph
        graph.displayGraph();
        
        // Run BFS, DFS and heuristic TSP
        Map<String, Integer> dijkstraOrder = graph.dijkstra("Spurs");
        java.util.List<String> bfsOrder = graph.bfs("Spurs");
        java.util.List<String> dfsOrder = graph.dfs("Spurs");
        java.util.List<String> tspOrder = graph.heuristicTSP("Spurs");

        System.out.println("\nDjikstra Order: " + dijkstraOrder);
        System.out.println("BFS Order: " + bfsOrder);
        System.out.println("DFS Order: " + dfsOrder);
        System.out.println("Heuristic TSP Order: " + tspOrder);
        
        // Calculate total distance for BFS
        int dijkstraTotalDistance = calculateTotalDistance(graph, dijkstraOrder);
        System.out.println("\nDijkstra Total Distance: " + dijkstraTotalDistance + " km");

        // Calculate total distance for BFS
        int bfsTotalDistance = calculateTotalDistance(graph, bfsOrder);
        System.out.println("BFS Total Distance: " + bfsTotalDistance + " km");

        // Calculate total distance for DFS
        int dfsTotalDistance = calculateTotalDistance(graph, dfsOrder);
        System.out.println("DFS Total Distance: " + dfsTotalDistance + " km");

        // Calculate total distance for Heuristic TSP
        int tspTotalDistance = calculateTotalDistance(graph, tspOrder);
        System.out.println("Heuristic TSP Total Distance: " + tspTotalDistance + " km");
        
        // Create and display the GUI
        GraphGUI.createAndShowGUI(graph, dijkstraOrder, bfsOrder, dfsOrder, tspOrder);
    }


     private static int calculateTotalDistance(Graph graph, Map<String, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }
     
    private static int calculateTotalDistance(Graph graph, java.util.List<String> order) {
            int totalDistance = 0;
            for (int i = 0; i < order.size() - 1; i++) {
                String current = order.get(i);
                String next = order.get(i + 1);
                for (Edge edge : graph.getAdjList().get(current)) {
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

 class GraphGUI {
    public static void createAndShowGUI(Graph graph, Map<String, Integer> dijkstraOrder,
                                         java.util.List<String> bfsOrder, java.util.List<String> dfsOrder,
                                         java.util.List<String> tspOrder) {
        JFrame frame = new JFrame("NBA Teams Travel Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JPanel dfsPanel = createPanelWithBorder("DFS Order", createTextArea(graph,dfsOrder));
        JPanel tspPanel = createPanelWithBorder("Heuristic TSP Order", createTextArea(graph,tspOrder));

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

