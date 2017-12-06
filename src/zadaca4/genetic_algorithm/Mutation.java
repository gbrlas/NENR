package zadaca4.genetic_algorithm;

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
}
