package sol;

import src.Row;

/**
 * A class that represents a leaf in a decision tree.
 */
public class Leaf implements ITreeNode{
    private String theClassification;

    /**
     * Constructs a leaf object
     * @param classification Takes in a classification value for the target attribute that will be returned
     */
    public Leaf(String classification) {
        this.theClassification = classification;
    }

    /**
     * A method that returns the decision of this node. In this case, it just returns the classification.
     * @param forDatum the datum to lookup a decision for
     * @return the classification for the tree traversal
     */
    @Override
    public String getDecision(Row forDatum) {
        return this.theClassification;
    }
}
