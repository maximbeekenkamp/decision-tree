package sol;

import src.IDataset;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    private List<String> attributes;
    private List<Row> data;

    public Dataset(List<String> attributeList, List<Row> data) {
        this.data = data;
        this.attributes = attributeList;
    }

    /**
     * This method returns the list of attributes or column headers for the dataset
     * @return
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributes;
    }

    /**
     * This method returns the data objects which is a list of the rows in the dataset
     * @return the list of rows
     */
    @Override
    public List<Row> getDataObjects() {
        return this.data;
    }

    /**
     * This method returns the number of rows in the dataset, excluding the row of attributes
     * @return an int representing the size
     */
    @Override
    public int size() {
        return this.data.size();
    }

    /**
     * This method trims a dataset based on an edge leaving a certain node. It is called by the createNode method in the
     * treeGenerator class.
     * @param trimmingAttribute the attribute that is being trimmed based on
     * @param attributeValue the value of that atrribute that we are filtering for
     * @return A new dataset with only rows that have the desired attribute value
     */
    public Dataset trim(String trimmingAttribute, String attributeValue) {
        List<Row> trimmedRows = new ArrayList<>();
        for (Row datum : this.data) {
            if (datum.getAttributeValue(trimmingAttribute).equals(attributeValue)) {
                trimmedRows.add(datum);
            }
        }
        List<String> attributeList = new ArrayList<>(trimmedRows.get(0).getAttributes());
        return new Dataset(attributeList, trimmedRows);
    }
}
