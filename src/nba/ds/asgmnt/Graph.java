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

    private  Map<String, LinkedList<Edge>> adjList;

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

        // Adding edges with weights (example weights, you can modify them)
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
        
        // Create and display the GUI
        GraphGUI.createAndShowGUI(graph);
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
        nodePositions.put("Spurs", new Point(100, 100));
        nodePositions.put("Warriors", new Point(300, 100));
        nodePositions.put("Celtics", new Point(500, 100));
        nodePositions.put("Heat", new Point(700, 100));
        nodePositions.put("Lakers", new Point(300, 300));
        nodePositions.put("Suns", new Point(100, 500));
        nodePositions.put("Magic", new Point(500, 500));
        nodePositions.put("Nuggets", new Point(700, 300));
        nodePositions.put("Thunder", new Point(100, 300));
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
                g.drawString(String.valueOf(edge.weight), midX, midY);
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
    public static void createAndShowGUI(Graph graph) {
        JFrame frame = new JFrame("NBA Team Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphPanel(graph));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

