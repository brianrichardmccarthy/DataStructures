
/**
 * Stores a string character, Its frequency and it's Huffman code
 * 
 * @author Brian McCarthy, 20063914
 */

import java.util.Comparator;

public class DataItem {

    /**
     * Compares to data items by their Integer Occurences
     */
    public static final Comparator<DataItem> compareToNumber = new Comparator<DataItem>() {

        /**
         * Compares first item to the second item by natural order of Integer
         * 
         * @param DataItem
         *            first item
         * @param DataItem
         *            second item
         */
        @Override
        public int compare(DataItem dataItemItem1, DataItem dataItemItem2) {

            return dataItemItem1.occurence.compareTo(dataItemItem2.occurence);
        }
    };

    /**
     * Compares to data items by their string data
     */
    public static final Comparator<DataItem> compareToString = new Comparator<DataItem>() {

        /**
         * Compares first item to the second item by natural order of String
         * 
         * @param DataItem
         *            first item
         * @param DataItem
         *            second item
         */
        @Override
        public int compare(DataItem dataItemItem1, DataItem dataItemItem2) {

            return dataItemItem1.data.compareTo(dataItemItem2.data);
        }
    };

    private String data;
    private String binary;
    private Integer occurence;

    /**
     * Constructor. Sets the data to the argument passed in. Sets occurence to one.
     * 
     * @param data
     *            String (Single character if leaf node, or combination of children nodes)
     */
    public DataItem(String data) {
        this.data = data;
        binary = "";
        occurence = 1;
    }

    /**
     * Accessor for data variable
     * 
     * @return (String)
     */
    public String getData() {

        return data;
    }

    /**
     * Accessor for binary representation of character
     * 
     * @return (String)
     */
    public String getBinary() {

        return binary;
    }

    /**
     * Accessor for occurence
     * 
     * @return (Integer)
     */
    public Integer getOccurence() {

        return occurence;
    }

    /**
     * Mutator for binary value. Concatinates the two strings.
     * 
     * @param binary
     *            (String)
     */
    public void setBinary(String binary) {

        this.binary += binary;
    }

    /**
     * Mutator for occurence. Increases it by one
     */
    public void setOccurence() {

        this.occurence++;
    }

    /**
     * Mutator for occurence. Sets occurence to value passed in.
     * 
     * @param occurence
     *            (int)
     */
    public void setOccurence(int occurence) {

        this.occurence = occurence;
    }

    /**
     * Mutator for data. Concatenates the two strings.
     * 
     * @param data
     *            (String)
     */
    public void setData(String data) {

        this.data += data;
    }

    /**
     * Returns a string of this object
     * 
     * @return (String)
     */
    @Override
    public String toString() {

        return "Data <" + data + "> Occurence <" + occurence + "> binary string <" + binary + ">";
    }

}
