package zadaca5.data;

import java.util.ArrayList;

/**
 * Class used for generating the dataset.
 *
 * @author goran
 * @version 1.0
 */
public class Dataset {
    /**
     * Method which generates a dataset of 81 examples.
     *
     * @return Array of generated examples.
     */
    public static ArrayList<Example> generateDataset() {
        ArrayList<Example> data = new ArrayList<>();

        for (int i = -4; i < 5; i++) {
            for (int j = -4; j < 5; j++) {
                data.add(new Example(i, j));
            }
        }

        return data;
    }
}
