package sol;

/**
 * A class that represents an edge leaving a node
 */
public class Edge {
    private String edgeAttributeValue;
    private ITreeNode nextNode;

    /**
     * Constructs an edge object
     * @param edgeAttributeValue Takes in the value of the attribute that the edge sorts on
     * @param nextNode Takes in the ITreeNode object that the edge leads to
     */
    public Edge(String edgeAttributeValue, ITreeNode nextNode) {
        this.edgeAttributeValue = edgeAttributeValue;
        this.nextNode = nextNode;
    }

    /**
     *
     * @return Returns the value of the atrribute that the edge designates
     */
    public String getEdgeAttribute() {
        return this.edgeAttributeValue;
    }

    /**
     *
     * @return Returns the next node that the edge leads to
     */
    public ITreeNode getNextNode() {
        return this.nextNode;
    }
}
