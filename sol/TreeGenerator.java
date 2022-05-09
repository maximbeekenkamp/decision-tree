package sol;

import src.ITreeGenerator;
import src.Row;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {
    private ITreeNode topNode;

    /**
     * This method generates a decision tree based on a dataset and a target attribute that the decision tree
     * is trying to predict.
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        List<String> unusedAttributes = trainingData.getAttributeList();
        this.topNode = this.createNode(trainingData, targetAttribute, unusedAttributes);
    }

    /**
     * This method traverses the most recently created decision tree to get a classification based
     * on a row that is taken in as a parameter
     * @param datum the datum to lookup a decision for
     * @return the classification of the tree for that row
     */
    @Override
    public String getDecision(Row datum) {
        return this.topNode.getDecision(datum);
    }

    /**
     * This is a helper method that is used to recursively create nodes and fill out the decision tree all the way down
     * until all endpoints are leaves. It picks an attribute to sort on randomly and then creates a node based on that attribute.
     * @param trainingData the dataset that the node is to be created from (trimmed or not)
     * @param targetAttribute the target attribute that the tree is sorting on
     * @param unusedAttributes the attributes that have not yet had nodes created based on them in this line of the
     *                         decision tree
     * @return
     */
    private ITreeNode createNode(Dataset trainingData, String targetAttribute, List<String> unusedAttributes) {
        if (this.checkLeaf(trainingData, targetAttribute)) {
            return new Leaf(trainingData.getDataObjects().get(0).getAttributeValue(targetAttribute));
        }
        List<String> unusedAttributesCopy = new ArrayList<>(unusedAttributes);
        String randomAttribute = this.generateAttribute(unusedAttributesCopy);
        List<String> edgeValues = new ArrayList<>();
        for (Row row : trainingData.getDataObjects()) {
            edgeValues.add(row.getAttributeValue(randomAttribute));
        }
        edgeValues = edgeValues.stream().distinct().collect(Collectors.toList());
        ArrayList<Edge> edges = new ArrayList<>();
        for (String edgeValue : edgeValues) {
            edges.add(new Edge(edgeValue, this.createNode(trainingData.trim(randomAttribute, edgeValue), targetAttribute, unusedAttributesCopy)));
        }
        return new Node(randomAttribute, this.findDefault(trainingData, targetAttribute), edges);
    }

    /**
     * A helper method that determines the default decision of a node based on which value of the target attribute
     * appears most frequently in the dataset at that node.
     * @param theData the dataset at a given node
     * @param targetAttribute the target attribute that the tree is being made on
     * @return the default value of the node being made with this dataset
     */
    private String findDefault(Dataset theData, String targetAttribute) {
        List<String> decisionValues = new ArrayList<>();
        List<Row> theRows = theData.getDataObjects();
        for (Row theRow : theRows) {
            decisionValues.add(theRow.getAttributeValue(targetAttribute));
        }
        decisionValues = decisionValues.stream().distinct().collect(Collectors.toList());
        int maxcount = 0;
        String maxDecision = null;
        for (String decision : decisionValues) {
            int currentcount = 0;
            for (Row theRow : theRows) {
                if (decision.equals(theRow.getAttributeValue(targetAttribute))) {
                    currentcount++;
                }
            }
            if (currentcount > maxcount) {
                maxcount = currentcount;
                maxDecision = decision;
            }
        }
        return maxDecision;
    }

    /**
     * A helper method that determines if a leaf should be made or not by checking if all values of the target
     * attribute are the same
     * @param trainingData the dataset at that point in the tree
     * @param targetAttribute the target attribute that the tree is being created on
     * @return true if a leaf should be made and false if not
     */
    private boolean checkLeaf(Dataset trainingData, String targetAttribute) {
        String theValue = trainingData.getDataObjects().get(0).getAttributeValue(targetAttribute);
        for (int i = 1; i<trainingData.getDataObjects().size(); i++) {
            if(!trainingData.getDataObjects().get(i).getAttributeValue(targetAttribute).equals(theValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A helper method to generate a random attribute to create a node on based on a list of attributes that haven't
     * yet been sorted on in that line of the tree.
     * @param unusedAttributes List of attributes that haven't yet been sorted on
     * @return The randomly determined attribute to sort on
     */
    private String generateAttribute(List<String> unusedAttributes) {
        Random random = new Random();
        int randomNum = random.nextInt(unusedAttributes.size());
        String theAttribute = unusedAttributes.get(randomNum);
        unusedAttributes.remove(theAttribute);
        return theAttribute;
    }
}