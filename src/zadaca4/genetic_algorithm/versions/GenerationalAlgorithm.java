package zadaca4.genetic_algorithm.versions;

import zadaca4.functions.ErrorFunction;
import zadaca4.genetic_algorithm.*;
import zadaca4.records.Records;

import java.util.Random;

/**
 * Class which implements eliminational genetic algorithm.
 *
 * @author goran
 * @version 1.0
 */
public class GenerationalAlgorithm {
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
     * Elitism in the algorithm. If true, best chromosome of the previous population is always copied into the new
     * population.
     */
    private static boolean elitism;

    /**
     * Constructor which sets the genetic algorithm parameters.
     *
     * @param populationSize Size of teh population.
     * @param numGenes       Number of genes in a chromosome.
     * @param upperBound     Gene uper bound value.
     * @param lowerBound     Gene lower bound value.
     * @param iterations     Number of iterations.
     * @param elitism        True if algorithm uses elitism.
     * @param fileName       Measurements file name.
     * @param pm             Mutationo probability.
     */
    public GenerationalAlgorithm(int populationSize, int numGenes, int upperBound, int lowerBound, int iterations, boolean elitism, String fileName, Double pm) {
        this.populationSize = populationSize;
        this.numGenes = numGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.random = new Random();
        this.iterations = iterations;
        this.elitism = elitism;
        this.fileName = fileName;
        this.pm = pm;
    }

    /**
     * Method which runs the genetic algorithm using previously provided parameters.
     */
    public static void run() {
        random = new Random();

        Records records = new Records(fileName);
        ErrorFunction errorFunction = new ErrorFunction();


        Population population = Population.createInitialPopulation(populationSize, numGenes, upperBound, lowerBound, random);
        errorFunction.evaluate(population, records);

        int totalIterations = 0;
        Chromosome best = null;
        int counter = 0;
        double best_error = Double.MAX_VALUE;
        while (totalIterations <= iterations) {
            population = generationalReplacement(population, errorFunction, records);
            errorFunction.evaluate(population, records);
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
            System.out.println(totalIterations + ": " + best);
        }

        System.out.println();
        System.out.println("Solution: " + best);
        System.out.println("Number of iterations: " + totalIterations);
    }

    /**
     * Method which replaces the previous population using generational replacement.
     *
     * @param population   Previous population.
     * @param evalFunction Evalueation function.
     * @param records      Measurement records.
     * @return New population.
     */
    public static Population generationalReplacement(Population population,
                                                     ErrorFunction evalFunction,
                                                     Records records) {

        int size = population.getSize();
        Population nextGeneration = new Population(size);
        int numOfChildren = 0;

        if (elitism) {
            nextGeneration.setChromosome(0, population.getBest());
            numOfChildren++;
        }

        int parentsPairs = size - numOfChildren;
        Population pool = new Population(parentsPairs);

        for (int i = 0; i < parentsPairs; i++) {
            Chromosome[] parents = Selection.rouletteWheelSelection(2, population, random);
            Chromosome child = Crossover.arithmeticCrossover(parents[0], parents[1]);
            Mutation.uniformMutation(child, random, pm);
            pool.setChromosome(i, child);
        }

        evalFunction.evaluate(pool, records);

        while (numOfChildren < size) {
            int index = elitism ? numOfChildren - 1 : numOfChildren;
            nextGeneration.setChromosome(numOfChildren, pool.getChromosome(index));
            numOfChildren++;
        }

        return nextGeneration;
    }

}
