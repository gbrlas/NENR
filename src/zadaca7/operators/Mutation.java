package zadaca7.operators;

import zadaca7.genetic_algorithm.Chromosome;

import java.util.Random;

/**
 * Class which represents all the mutation possibilities for the genetic algorithm implementation.
 *
 * @author goran
 * @version 1.0
 */
public class Mutation {
    /**
     * Implementation of the uniform mutation.
     *
     * @param chromosome Chromosome to be mutated.
     * @param random     Random singleton used for random number generation.
     * @param pm         Mutation probability.
     */
    public static void uniformMutation(Chromosome chromosome, Random random, double pm) {
        for (int i = 0; i < chromosome.getSize(); i++) {
            if (random.nextDouble() < pm) {
                double value = chromosome.getGene(i) + random.nextGaussian();
                chromosome.setGene(i, value);
            }
        }
    }

    public static void mutate(Chromosome chromosome, Random random, double pm, double v, double sigma1, double sigma2) {
        if (random.nextDouble() < pm) {
            if (random.nextDouble() < v) {
                mutate1(chromosome, chromosome.getGenes().length, random, sigma1);
            } else {
                mutate2(chromosome, chromosome.getGenes().length, random, sigma2);
            }
        }
    }

    private static void mutate1(Chromosome chromosome, int numberOfGenes, Random random, double sigma1) {
        for (int i = 0; i < numberOfGenes; i++) {
            if (random.nextDouble() < sigma1) {
                double gene = chromosome.getGene(i) + random.nextGaussian() * sigma1;
                chromosome.setGene(i, gene);
            }
        }
    }

    private static void mutate2(Chromosome chromosome, int numberOfGenes, Random random, double sigma2) {
        for (int i = 0; i < numberOfGenes; i++) {
            if (random.nextDouble() < sigma2) {
                double gene = random.nextGaussian() * sigma2;
                chromosome.setGene(i, gene);
            }
        }
    }
}
