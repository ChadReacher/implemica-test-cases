package com.chadreacher.secondtask;

import java.util.HashSet;
import java.util.Set;

/**
 * This class presents graph of nodes
 */
public class Graph {
    private Set<Node> nodes = new HashSet<>();

    /**
     * Add node to the set of nodes
     * @param node
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
