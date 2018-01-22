package zadaca7;

import zadaca7.ann.ANN;
import zadaca7.dataset.Dataset;
import zadaca7.functions.ANNErrorFunction;
import zadaca7.genetic_algorithm.EliminationAlgorithm;
import zadaca7.genetic_algorithm.GeneticAlgorithm;

public class Demo {
    public static void main(String[] args) {

        int iterations = 200000;
        int populationSize = 50;
        int M = 10;
        int k = 3;
        double pm = 0.4;
        double sigma1 = 0.1;
        double sigma2 = 0.2; //0.2
        double v = 0.5; //0.5
        double mse = 1e-6;

        int[] layers = new int[]{2, 8, 3};
        ANN ann = new ANN(layers);

        String file = "/Users/goran/eclipse-workspace/nenr_lab/src/zadaca7/zad7-dataset.txt";
        Dataset data = new Dataset(file);

        ANNErrorFunction errorFUnction = new ANNErrorFunction(ann, data);

        GeneticAlgorithm ga = new EliminationAlgorithm(
                iterations,
                ann.getNumberOfWeights(),
                populationSize,
                M,
                k,
                pm,
                sigma1,
                sigma2,
                v,
                mse,
                errorFUnction);

        double[] weights = ga.run();
        ann.setWeights(weights);
        int correct = 0;
        for (int i = 0; i < data.size(); i++) {
            String predicted = ann.predict(data.getInput(i));
            if (data.getBinaryOutput(i).equals(predicted)) {
                correct++;
            }

            System.out.println("True: " + data.toString(i) + "   predicted: " + ann.predict(data.getInput(i)));
        }

        System.out.println("Correctly predicted: " + correct + " out of " + data.size());

    }
}
