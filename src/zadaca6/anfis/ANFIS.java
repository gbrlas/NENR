package zadaca6.anfis;

import zadaca6.data.Example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class which implements a nauro-fuzzy ANFIS system.
 */
public class ANFIS {
    /**
     * Number of rules.
     */
    private int numRules;
    /**
     * List containing all the rules.
     */
    private ArrayList<Rule> rules;

    /**
     * Stopping criteria.
     */
    private static final double EPSILON = 1e-6;
    /**
     * Number of iterations.
     */
    private static final int ITERATIONS = 400000;

    /**
     * Constructor which sets the parameters to the provided ones.
     *
     * @param numRules Number of rules.
     */
    public ANFIS(int numRules) {
        this.numRules = numRules;
        this.rules = new ArrayList<>();
    }

    /**
     * Forward pass through the neural net.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return Output value.
     */
    public double forward_pass(double x, double y) {
        // Layer 1
        double w_sum = 0;
        for (Rule rule : rules) {
            rule.alpha = sigmoid(x, rule.A1, rule.A2);
            rule.beta = sigmoid(y, rule.B1, rule.B2);

            // Layer 2
            rule.w = rule.alpha * rule.beta;
            w_sum += rule.w;
        }

        // Layer 3
        double output = 0;
        for (Rule rule : rules) {
            // normalization
            rule.w_norm = rule.w / w_sum;

            // Layer 4
            rule.z = rule.p * x + rule.q * y + rule.r;

            // Layer 5
            output += rule.w_norm * rule.z;
        }

        return output;
    }

    /**
     * Trains the neural net on the provided data.
     *
     * @param data         List of examples.
     * @param learningRate Neural net learning rate.
     * @param batch        Boolean representing whether batch or online learning is used.
     * @return epoch_error Epoch error.
     */
    public double train(ArrayList<Example> data, double learningRate, boolean batch) {
        for (int i = 0; i < numRules; i++) {
            rules.add(new Rule());
        }

        int current_epoch = 0;
        double epoch_error = Double.MAX_VALUE;


        FileWriter writer = null;
        try {
            writer = new FileWriter("errors.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (current_epoch <= ITERATIONS && epoch_error > EPSILON) {
            epoch_error = 0;

            for (Example example : data) {
                double output = forward_pass(example.getX(), example.getY());
                double exampleError = example.getValue() - output;

                epoch_error += Math.pow(exampleError, 2);
                updateDeltas(example, exampleError);

                if (!batch) {
                    updateParameters(learningRate);
                }
            }

            if (batch) {
                updateParameters(learningRate);
            }

            epoch_error *= 1. / (2 * data.size());

            if (current_epoch % 1000 == 0) {
                System.out.format("Epoch = %d  error = %.6f%n", current_epoch, epoch_error);
            }

            try {
                writer.write(String.valueOf(epoch_error) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


            current_epoch++;

        }

        System.out.println();
        System.out.println("Finished!");
        System.out.format("Epoch = %6d  error = %f%n", current_epoch - 1, epoch_error);
        System.out.println();

        return epoch_error;
    }

    /**
     * Method which updates the derivation values.
     *
     * @param example Example used for updating.
     * @param error   Neural net error on the provided example.
     */
    private void updateDeltas(Example example, double error) {
        for (Rule rule : rules) {
            double nazivnik = 0;
            double brojnik = 0;

            for (Rule secondRule : rules) {
                nazivnik += secondRule.w;
                brojnik += secondRule.w * (rule.z - secondRule.z);
            }

            double razlomak = brojnik / Math.pow(nazivnik, 2);

            rule.updateDerivations(error, razlomak, example);
        }
    }

    /**
     * @param learningRate Neural network learning rate.
     */
    private void updateParameters(double learningRate) {
        for (Rule rule : rules) {
            rule.updateParameters(learningRate);
            rule.resetDerivations();
        }
    }

    /**
     * Method used for calculating sigmoid values.
     *
     * @param x
     * @param a
     * @param b
     * @return Sigmoid value.
     */
    public double sigmoid(double x, double a, double b) {
        return 1. / (1 + Math.exp(b * (x - a)));
    }


    public ArrayList<Rule> getRules() {
        return rules;
    }
}
