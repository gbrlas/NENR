package zadaca7.genetic_algorithm;

import zadaca7.functions.ANNErrorFunction;
import zadaca7.operators.Crossover;
import zadaca7.operators.Mutation;
import zadaca7.operators.Selection;

import java.util.Random;

/**
 * Class which implements eliminational genetic algorithm.
 *
 * @author goran
 * @version 1.0
 */
public class EliminationAlgorithm implements GeneticAlgorithm {
    private int iterations;

    private int numberOfGenes;

    private int populationSize;

    private int M;

    private int k;

    private double mutation;

    private double sigma1;

    private double sigma2;

    private double v;

    private double mse;

    private ANNErrorFunction errorFunction;

    private Random random;

    public EliminationAlgorithm(int iterations,
                                int numberOfGenes,
                                int populationSize,
                                int M,
                                int k,
                                double mutation,
                                double sigma1,
                                double sigma2,
                                double v,
                                double mse,
                                ANNErrorFunction errorFunction) {
        this.iterations = iterations * (populationSize / M);
        this.numberOfGenes = numberOfGenes;
        this.populationSize = populationSize;
        this.M = M;
        this.k = k;
        this.mutation = mutation;
        this.sigma1 = sigma1;
        this.sigma2 = sigma2;
        this.v = v;
        this.mse = mse;
        this.errorFunction = errorFunction;
    }

    public double[] run() {
        random = new Random();
        Population population = zadaca7.genetic_algorithm.Population.createInitialPopulation(populationSize, numberOfGenes, random);
        errorFunction.evaluatePopulation(population);


        int totalIterations = 0;
        Chromosome best = null;
        int counter = 0;
        double best_error = Double.MAX_VALUE;

        while (totalIterations <= iterations) {
            population = populationReplacement(population, errorFunction);
            errorFunction.evaluatePopulation(population);
            best = population.getBest();

            if (best.getFitness() <= best_error) {
                best_error = best.getFitness();
                counter = 0;
            } else {
                counter += 1;
                if (counter >= 10000) {
                    System.out.println("1000 iterations without improvement, stopping the algorithm.");
                    break;
                }
            }

            if (best_error <= 1e-6) {
                break;
            }

            totalIterations++;
            if (totalIterations % 100 == 0) {
                System.out.println(totalIterations + ": " + best);
            }
        }

        System.out.println();
        System.out.println("Solution: " + best);
        System.out.println("Number of iterations: " + totalIterations);

        return best.getGenes();
    }

    public Population populationReplacement(Population population,
                                            ANNErrorFunction evalFunction) {

        Chromosome[] tournament = Selection.rouletteWheelSelection(3, population, random);

        int worst_i = 0;
        for (int i = 1; i < 3; i++) {
            if (tournament[i].getFitness() > tournament[worst_i].getFitness()) {
                worst_i = i;
            }
        }

        Chromosome[] bestParents = new Chromosome[2];
        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (i != worst_i) {
                bestParents[j++] = tournament[i];
            }
        }

        Chromosome child = Crossover.arithmeticCrossover(bestParents[0], bestParents[1]);
        Mutation.mutate(child, random, mutation, v, sigma1, sigma2);
        evalFunction.calculateChromosomeFitness(child);

        Chromosome worst = tournament[worst_i];
        for (int i = 0, n = population.getSize(); i < n; i++) {
            if (worst.equals(population.getChromosome(i))) {
                population.setChromosome(i, child);
                break;
            }
        }


        return population;
    }
}
