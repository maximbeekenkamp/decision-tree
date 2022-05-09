package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {
    
    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {
        
    }
    
    @Test
    public void testExample() { 
        assertEquals(6, 1 + 2 + 3);
    }

    @Test
    public void testDataSetSize() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/songs/testing.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, dataObjects);
        Assert.assertEquals(newSet.size(), 87);
    }

    @Test
    public void testGenerateTreeOnTrainingData1() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/food/training.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, dataObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(newSet, "foodType");
        Assert.assertEquals(generator.getDecision(newSet.getDataObjects().get(2)), "vegetable");
    }

    @Test
    public void testGenerateTreeOnTestingData() {
        List<Row> trainingObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/food/training.csv");
        List<String> attributeList = new ArrayList<>(trainingObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, trainingObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(newSet, "foodType");
        List<Row> testingObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/food/testing.csv");
        Assert.assertEquals(generator.getDecision(testingObjects.get(0)), "fruit");
    }

    @Test
    public void testGenerateTreeOnTrainingData2() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/food/training.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, dataObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(newSet, "foodType");
        Assert.assertEquals(generator.getDecision(newSet.getDataObjects().get(6)), "fruit");
    }

    @Test
    public void testGetDecisionOnTrainingData() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/applicants/training.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, dataObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(newSet, "outcome");
        Assert.assertEquals(generator.getDecision(dataObjects.get(3)), "reject");
    }

    @Test
    public void testGetDecisionOnTestingData() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/applicants/training.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset newSet = new Dataset(attributeList, dataObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(newSet, "outcome");
        List<Row> testingObjects = DecisionTreeCSVParser.parse("/Users/georgelawton/Desktop/cs200/projects/decision-tree-glawton1-maximbeekenkamp/data/applicants/testing.csv");
        Assert.assertEquals(generator.getDecision(testingObjects.get(0)), "accept");
    }
}
