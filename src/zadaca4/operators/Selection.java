package zadaca4.operators;

import zadaca4.genetic_algorithm.Chromosome;
import zadaca4.genetic_algorithm.Population;

import java.util.Random;

/**
 * Class which represents all the selection possibilities for the genetic algorithm implementation.
 *
 * @author goran
 * @version 1.0
 */
public class Selection {
    /**
     * Implements roulette wheel selection as described here:
     * https://en.wikipedia.org/wiki/Fitness_proportionate_selection
     *
     * @param numOfParents Number of parents to be selected.
     * @param population   Chromosomes population.
     * @param random       Random singleton used for random number generation.
     * @return Array of <code>numOfParents</code> selected chromosomes.
     */
    public static Chromosome[] rouletteWheelSelection(int numOfParents, Population population, Random random) {
        Chromosome[] parents = new Chromosome[numOfParents];

        for (int i = 0; i < numOfParents; i++) {
            parents[i] = selectOneParent(population, random);
        }

        return parents;
    }

    /**
     * Helper function used for selecting one parent in a roulette wheel selection.
     *
     * @param population Chromosomes population.
     * @param random     Random singleton used for random number generation.
     * @return Selected parent.
     */
    private static Chromosome selectOneParent(Population population, Random random) {
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

    /**
     * Implements tournament wheel selection, as described here:
     * https://en.wikipedia.org/wiki/Fitness_proportionate_selection
     *
     * @param numOfParents Number of parents to be selected.
     * @param population   Chromosome population.
     * @param random       Random singleton used for random number generation.
     * @return Array of <code>numOfParents</code> selected chromosomes.
     */
    public static Chromosome[] tournamentWheelSelection(int numOfParents, Population population, Random random) {
        Chromosome[] parents = new Chromosome[numOfParents];

        for (int i = 0; i < numOfParents; i++) {
            int index = random.nextInt(population.getSize());
            parents[i] = population.getChromosome(index);
        }

        return parents;
    }
}
