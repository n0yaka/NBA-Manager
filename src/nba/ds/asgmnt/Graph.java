/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;

import java.util.*;

/**
 *
 * @author Al Fatih
 */
public class Graph {
    private class Edge {
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

    private Map<String, LinkedList<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new LinkedList<>());
    }

    public void addEdge(String source, String destination, int weight) {
        adjList.get(source).add(new Edge(destination, weight));
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
    }
}


