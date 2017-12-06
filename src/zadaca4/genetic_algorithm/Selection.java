package zadaca4.genetic_algorithm;

import java.util.Random;

public class Selection {
    public static Chromosome[] rouletteWheelSelection(int numOfParents, Population population, Random random) {
        Chromosome[] parents = new Chromosome[numOfParents];

        for (int i = 0; i < numOfParents; i++) {
            parents[i] = rouletteSingleParent(population, random);
        }

        return parents;
    }

    private static Chromosome rouletteSingleParent(Population population, Random random) {
        double sum = 0;
        double max = 0;

        for (int i = 0; i < population.getSize(); i++) {
            sum += population.getChromosome(i).getFitness();
            if (i == 0 || max < population.getChromosome(i).getFitness()) {
                max = population.getChromosome(i).getFitness();
            }
        }

        population.sortByFitness();
        sum = population.getSize() * max - sum;

        double rand = random.nextDouble() * sum;
        double accumulatedSum = 0;

        for (int i = 0; i < population.getSize(); i++) {
            accumulatedSum += max - population.getChromosome(i).getFitness();

            if (rand < accumulatedSum) {
                return population.getChromosome(i);
            }
        }

        return population.getChromosome(population.getSize() - 1);
    }


    public static Chromosome[] tournamentWheelSelection(int numOfParents, Population population, Random random) {
        Chromosome[] parents = new Chromosome[numOfParents];

        for (int i = 0; i < numOfParents; i++) {
            int index = random.nextInt(population.getSize());
            parents[i] = population.getChromosome(index);
        }

        return parents;
    }
}
