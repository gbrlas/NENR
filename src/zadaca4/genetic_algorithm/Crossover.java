package zadaca4.genetic_algorithm;

import java.util.Arrays;
import java.util.Random;

public class Crossover {
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
