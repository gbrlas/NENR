package zadaca4;

import zadaca4.genetic_algorithm.versions.EliminationAlgorithm;
import zadaca4.genetic_algorithm.versions.GenerationalAlgorithm;

/**
 * Class used to demo the genetic algorithm implementations.
 *
 * @author goran
 * @version 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int iterations = 10000;
        int populationSize = 30;
        double pm = 0.01;
        boolean elitism = true;
        int lowerBound = -4;
        int upperBound = 4;
        int paramSize = 5;
        String fileName = "/Users/goran/eclipse-workspace/nenr_lab/src/zadaca4/zad4-dataset1.txt";


        GenerationalAlgorithm gaGenerational = new GenerationalAlgorithm(populationSize, paramSize, upperBound, lowerBound, iterations, elitism, fileName, pm);
        gaGenerational.run();

        EliminationAlgorithm gaElimination = new EliminationAlgorithm(populationSize, paramSize, upperBound, lowerBound, iterations, fileName, pm);
        gaElimination.run();
    }


}


