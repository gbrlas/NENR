package zadaca4.operators;

import zadaca4.genetic_algorithm.Chromosome;

import java.util.Arrays;
import java.util.Random;

/**
 * Class which represents all the crossover possibilities for the genetic algorithm implementation, as described
 * here: https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
 *
 * @author goran
 * @version 1.0
 */
public class Crossover {
    /**
     * Implementation of the n-point crossover.
     *
     * @param n       Number of crossover points.
     * @param parent1 First parent.
     * @param parent2 Second parent.
     * @param random  Random singleton used for random number generation.
     * @return Child chromosome which is the result of the crossover.
     */
    public static Chromosome nPointCrossover(int n, Chromosome parent1, Chromosome parent2, Random random) {
        int size = parent1.getSize();
        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            points[i] = random.nextInt(size);
        }

        Arrays.sort(points);
        Chromosome child = new Chromosome(size);

        // Iterate through breaking points
        for (int i = 0; i < n; i++) {
            int bound = (i == n - 1) ? size : points[i + 1];

            if (i % 2 == 0) {
                for (int j = points[i]; j < bound; j++) {
                    child.setGene(j, parent1.getGene(j));
                }
            } else {
                for (int j = points[i]; j < bound; j++) {
                    child.setGene(j, parent2.getGene(j));
                }
            }

        }

        return child;
    }

    /**
     * Implementation of the n-point crossover.
     *
     * @param parent1 First parent.
     * @param parent2 Second parent.
     * @return Child chromosome which is the result of the crossover.
     */
    public static Chromosome arithmeticCrossover(Chromosome parent1, Chromosome parent2) {

        int size = parent1.getSize();

        Chromosome child = new Chromosome(size);

        for (int i = 0; i < size; i++) {
            double value = (parent1.getGene(i) + parent2.getGene(i)) / 2.;
            child.setGene(i, value);
        }

        return child;

    }
}
