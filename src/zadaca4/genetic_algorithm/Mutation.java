package zadaca4.genetic_algorithm;

import java.util.Random;

public class Mutation {
    public static void uniformMutation(Chromosome chromosome, Random random, double pm) {

        for (int i = 0; i < chromosome.getSize(); i++) {
            if (random.nextDouble() < pm) {
                double value = chromosome.getGene(i) + random.nextGaussian();
                chromosome.setGene(i, value);
            }
        }

    }
}
