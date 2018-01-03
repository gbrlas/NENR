package zadaca5;

import zadaca5.anfis.ANFIS;
import zadaca5.data.Dataset;
import zadaca5.data.Example;

import java.util.ArrayList;

/**
 * Class used to demo the program.
 *
 * @author goran
 * @version 1.0
 */
public class Demo {
    /**
     * Main method which starts the program.
     *
     * @param args none needed.
     */
    public static void main(String[] args) {

        int numRules = 7;

        ArrayList<Example> data = Dataset.generateDataset();

        ANFIS anfis = new ANFIS(numRules);
        anfis.train(data, 1e-4, true);

        for (Example example : data) {
            System.out.println("" + example.getY() + "   " + example.getY() + "  Error: " +
                    (example.getValue() - anfis.forward_pass(example.getX(), example.getY())));
        }

    }
}
