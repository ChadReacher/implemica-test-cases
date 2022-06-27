package com.chadreacher.secondtask;

import java.util.*;

public class SecondTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // create scanner for reading from input
        int numberOfTest = scanner.nextInt(); // take number of tests
        int numberOfCities = scanner.nextInt(); // take number of cities
        List<Node> allNodes = new ArrayList<>(); // create an empty array list of all nodes
        String sourceCity = "", destinationCity = ""; // initialized empty sourceCity and destinationCity
        List<Integer> results = new ArrayList<>(); // create an arraylist for results
        for (int i = 0; i < numberOfTest; i++) { // for each test
            for (int j = 0; j < numberOfCities; j++) { // for each city
                String cityName = scanner.next(); // take name of city
                Node newNode = new Node(cityName); // create node of the city

                int numberOfNeighbours = scanner.nextInt(); // get number of neighbours
                for (int k = 0; k < numberOfNeighbours; k++) { // for each neighbour
                    int neighbourIndex = Integer.parseInt(scanner.next()) - 1; // get neighbour index
                    int transportationCost = Integer.parseInt(scanner.next()); // get weight to the neighbour
                    newNode.addDestination(neighbourIndex, transportationCost); // add destination to the current neighbour through index and weight
                }
                allNodes.add(newNode); // add new node to list of all nodes
            }

            Graph graph = new Graph(); // for each test create a new graph
            for (Node node : allNodes) { // add all nodes to it
                graph.addNode(node);
            }



            int numberOfPathsToFind = scanner.nextInt(); // get number of paths to find
            for (int m = 0; m < numberOfPathsToFind; m++) { // for each path
                sourceCity = scanner.next(); // get source city
                destinationCity = scanner.next(); // and destination city

                String finalSourceCity = sourceCity; // turn source city variable to effectively final
                graph = calculateShortestPathFromSource(graph, // update all nodes with their distances with current source city
                        allNodes
                                .stream()
                                .filter(n -> n.getName().equals(finalSourceCity))
                                .findFirst().get(),
                        allNodes);


                String finalDestinationCity = destinationCity; // turn destination city variable to effectively final
                int cost = allNodes // get the lowest cost of the source city to the destination city
                        .stream()
                        .filter(n -> n.getName().equals(finalDestinationCity))
                        .findFirst().get().getDistance();
                results.add(cost); // add to result list
                for (Node node : allNodes) {
                    node.setDistance(Integer.MAX_VALUE); // reset distances for all nodes to "infinity"(max value of integer)
                    node.setShortestPath(new LinkedList<>()); // reset shortest path
                }
            }


            scanner.nextLine(); // read empty line
        }
        for (int c : results) { // display results
            System.out.println(c);
        }
    }

    /**
     * Represents standard Dijkstra algorithm in Java
     * @param graph
     * @param startingNode
     * @param allNodes
     * @return
     */
    public static Graph calculateShortestPathFromSource(Graph graph, Node startingNode, List<Node> allNodes) {
        startingNode.setDistance(0); // basic distance from start to start equals 0

        Set<Node> visitedNodes = new HashSet<>(); // set for visited nodes
        Set<Node> unvisitedNodes = new HashSet<>(graph.getNodes()); // set for unvisited nodes


        while (unvisitedNodes.size() != 0) { // while there are elements in unvisited nodes
            Node currentNode = getLowestDistanceNode(unvisitedNodes); // find a node with the lowest distance to it
            Map<Integer, Integer> adjacentNodes = currentNode.adjacentNodes; // find its adjacent nodes, to be exactly map of their indexes and weights
            for (Map.Entry<Integer, Integer> entry : adjacentNodes.entrySet()) { // for each adjacent node do:
                Node currentAdjacentNode = allNodes.get(entry.getKey()); // get real node by current index
                if (unvisitedNodes.contains(currentAdjacentNode)) { // if we haven't visited it yet
                    Integer adjacentNodeWeight = entry.getValue(); // get weight to it node
                    if (currentNode.getDistance() + adjacentNodeWeight < currentAdjacentNode.getDistance()) { // check if distance to the current node + the weight to its node is less than whole distance to this ndoe
                        currentAdjacentNode.setDistance(currentNode.getDistance() + adjacentNodeWeight); // we set new distance of distance to the current node + the weight from current node to its node
                        LinkedList<Node> linkedList = new LinkedList<>(currentNode.getShortestPath()); // create new linked list for attribute shortestPath based on already existing
                        linkedList.add(currentNode); // add to this list current node
                        currentAdjacentNode.setShortestPath(linkedList); // update shortest path for current adjacent node
                    }
                }
            }
            visitedNodes.add(currentNode); // add this node to visited
            unvisitedNodes.remove(currentNode); // and remove it from unvisited
        }

        return graph; // return graph with updated nodes and their shortestPaths and distances
    }

    /**
     * finds node with the lowest distance in the set of unvisited nodes
     * @param unvisitedNodes
     * @return
     */
    private static Node getLowestDistanceNode(Set<Node> unvisitedNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unvisitedNodes) { // for each node
            int nodeDistance = node.getDistance(); // get distance for current node
            if (nodeDistance < lowestDistance) { // if this node's distance is less than current lowest distance then
                lowestDistance = nodeDistance; // update lowest distance
                lowestDistanceNode = node; // update node with lowest distance
            }
        }
        return lowestDistanceNode; // return node with the lowest distance
    }
}


