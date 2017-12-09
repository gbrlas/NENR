package zadaca4.records;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class which is used for parsing and storing measured values from provided .txt files.
 *
 * @author goran
 * @version 1.0
 */
public class Records {
    /**
     * Array containing all the records.
     */
    private Record[] records;
    /**
     * Name of the file containing the recorded measurements.
     */
    private String fileName;
    /**
     * Number of records in the file.
     */
    private int size;

    /**
     * Constructor which sets the file name and reads from file.
     *
     * @param fileName File name.
     */
    public Records(String fileName) {
        this.fileName = fileName;
        readFromFile(this.fileName);
        this.size = records.length;
    }

    /**
     * Parses the file and stores all the records in the class array.
     *
     * @param fileName File name.
     */
    private void readFromFile(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            int n = lines.size();

            records = new Record[n];

            for (int i = 0; i < n; i++) {
                String[] line = lines.get(i).split("\t");

                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double value = Double.parseDouble(line[2]);

                records[i] = new Record(x, y, value);
            }

        } catch (IOException e) {
            System.err.println("Could not read from the following file: " + fileName);
        }

    }

    /**
     * Getter for the records array.
     *
     * @return Array containing all the records.
     */
    public Record[] getRecords() {
        return records;
    }

    /**
     * Getter for the individual records.
     *
     * @param index Index of the wanted record.
     * @return Record located at the provided index.
     */
    public Record getRecord(int index) {
        if (index < 0 || index >= records.length) {
            throw new IllegalArgumentException("Record for given index does not exist: " + index);
        }
        return records[index];
    }

    /**
     * Getter for the size of the record array.
     *
     * @return Size of the record array.
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for the records array.
     *
     * @param newRecords sets the records array to the provided one.
     */
    public void setRecords(Record[] newRecords) {
        if (newRecords.length > 0) {
            records = newRecords;
        } else {
            throw new IllegalArgumentException("Invalid records array provided!");
        }
    }

    /**
     * Setter for the individual records.
     *
     * @param index Index of the wanted record.
     * @param x     X value of the record.
     * @param y     Y value of the record.
     * @param value Measurement value of the record.
     */
    public void setRecord(int index, double x, double y, double value) {
        records[index] = new Record(x, y, value);
    }

}
