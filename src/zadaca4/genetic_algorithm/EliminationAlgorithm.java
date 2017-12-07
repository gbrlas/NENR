package zadaca4.genetic_algorithm;

import zadaca4.functions.ErrorFunction;
import zadaca4.operators.Crossover;
import zadaca4.operators.Mutation;
import zadaca4.operators.Selection;
import zadaca4.records.Records;

import java.util.Random;

/**
 * Class which implements eliminational genetic algorithm.
 *
 * @author goran
 * @version 1.0
 */
public class EliminationAlgorithm implements GeneticAlgorithm {
    /**
     * Population size.
     */
    private static int populationSize;
    /**
     * Number of genes in a chromosome.
     */
    private static int numGenes;
    /**
     * Gene lower bound value.
     */
    private static int lowerBound;
    /**
     * Gene upper bound value.
     */
    private static int upperBound;
    /**
     * Number of iterations.
     */
    private static int iterations;
    /**
     * Random singleton used for random number generation.
     */
    private static Random random;
    /**
     * Measurements file name.
     */
    private static String fileName;
    /**
     * Mutation probability.
     */
    private static double pm;

    /**
     * Constructor which sets the genetic algorithm parameters.
     *
     * @param populationSize Size of teh population.
     * @param numGenes       Number of genes in a chromosome.
     * @param upperBound     Gene uper bound value.
     * @param lowerBound     Gene lower bound value.
     * @param iterations     Number of iterations.
     * @param fileName       Measurements file name.
     * @param pm             Mutationo probability.
     */
    public EliminationAlgorithm(int populationSize, int numGenes, int upperBound, int lowerBound, int iterations, String fileName, Double pm) {
        this.populationSize = populationSize;
        this.numGenes = numGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.random = new Random();
        this.iterations = iterations;
        this.fileName = fileName;
        this.pm = pm;
    }

    public void run() {
        random = new Random();

        Records records = new Records(fileName);

        // initialization
        ErrorFunction errorFunction = new ErrorFunction();
        Population population = Population.createInitialPopulation(populationSize, numGenes, upperBound, lowerBound, random);
        errorFunction.evaluatePopulation(population, records);

        int totalIterations = 0;
        Chromosome best = null;
        int counter = 0;
        double best_error = Double.MAX_VALUE;
        while (totalIterations <= iterations) {
            population = populationReplacement(population, errorFunction, records);
            errorFunction.evaluatePopulation(population, records);
            best = population.getBest();

            if (best.getFitness() <= best_error) {
                best_error = best.getFitness();
                counter = 0;
            } else {
                counter += 1;
                if (counter >= 1000) {
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
    }

    public Population populationReplacement(Population population,
                                            ErrorFunction evalFunction,
                                            Records records) {

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
        Mutation.uniformMutation(child, random, pm);
        evalFunction.calculateChromosomeFitness(child, records);

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
