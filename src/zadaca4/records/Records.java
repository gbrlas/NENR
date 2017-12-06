package zadaca4.records;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Records {
    private Record[] records;
    private String fileName;

    public Records(String file) {
        this.fileName = file;
        readFromFile(fileName);
    }

    private void readFromFile(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
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
            System.err.println("Could not read the following file: " + file);
        }

    }

    public Record[] getRecords() {
        return records;
    }

    public void setRecords(Record[] newRecords) {
        records = newRecords;
    }

    public Record getRecord(int index) {
        if (index < 0 || index >= records.length) {
            throw new IllegalArgumentException("Record for given index does not exist: " + index);
        }
        return records[index];
    }

    public void setRecord(int index, double x, double y, double value) {
        records[index] = new Record(x, y, value);
    }

    public int getSize() {
        return records.length;
    }
}
