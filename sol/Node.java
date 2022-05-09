package sol;

import src.Row;

import java.util.ArrayList;

/**
 * A class that represents a node in a decision tree.
 */
public class Node implements ITreeNode{
    private String attribute;
    private String theDefault;
    private ArrayList<Edge> edges;

    /**
     * Creates an instance of a Node object.
     * @param theAttribute the attribute that the node is sorting on
     * @param nodeDefault the default classification that the node returns if no edges match a given row's value for the
     *                    attribute
     * @param theEdges an ArrayList of edges that lead from this node to another
     */
    public Node (String theAttribute, String nodeDefault, ArrayList<Edge> theEdges) {
        this.attribute = theAttribute;
        this.theDefault = nodeDefault;
        this.edges = theEdges;
    }

    /**
     * This method traverses the decision tree starting at this node to find a classification. It will return the
     * default if there is no edge to follow.
     * @param forDatum the datum to lookup a decision for
     * @return The value of getDecision for the nextNode in the tree traversal, or the default if there is no node
     * to go to next.
     */
    public String getDecision(Row forDatum) {
        for (Edge edge : this.edges) {
            if (edge.getEdgeAttribute().equals(forDatum.getAttributeValue(this.attribute))) {
                return edge.getNextNode().getDecision(forDatum);
            }
        }
        return this.theDefault;
    }
}
