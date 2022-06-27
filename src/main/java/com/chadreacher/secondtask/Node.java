package com.chadreacher.secondtask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Presents node of graph with basic getters and setters
 */
public class Node {

    private String name;
    private List<Node> shortestPath = new LinkedList<>(); // represents shortest path from starting node to it of nodes
    private Integer distance = Integer.MAX_VALUE; // representation of infinity like in basic dijkstra algorithm

    Map<Integer, Integer> adjacentNodes = new HashMap<>(); // map of index of node and it's weight, so basically it's an adjacent nodes

    public void addDestination(Integer destinationNode, int distance) {
        adjacentNodes.put(destinationNode, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
