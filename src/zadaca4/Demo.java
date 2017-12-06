package zadaca4;

import zadaca4.functions.ErrorFunction;
import zadaca4.functions.Function;
import zadaca4.genetic_algorithm.*;
import zadaca4.records.Record;
import zadaca4.records.Records;

import java.util.Random;

public class Demo {
    public static int iterations = 10000;
    public static int populationSize = 30;
    public static double pm = 0.01;
    public static boolean elitism = true;
    private static Random random;
    public static final int lowerBound = -4;
    public static final int upperBound = 4;
    public static final int paramSize = 5;

    public static void main(String[] args) {
        //eliminationAlgorithm();
        generationalAlgorithm();
    }

    public static void generationalAlgorithm() {
        random = new Random();

        Records records = new Records("/Users/goran/eclipse-workspace/nenr_lab/src/zadaca4/zad4-dataset1.txt");
        System.out.println(records.getSize());

        Population initialPopulation = Population.createInitialPopulation(populationSize, paramSize, upperBound, lowerBound, random);
        Selection selection = new Selection();
        Crossover crossover = new Crossover();
        Mutation mutation = new Mutation();
        ErrorFunction errorFunction = new ErrorFunction();


        Population population = Population.createInitialPopulation(populationSize, paramSize, upperBound, lowerBound, random);
        errorFunction.evaluate(population, records);

        int totalIterations = 0;
        Chromosome best = null;
        int counter = 0;
        double best_error = Double.MAX_VALUE;
        while (totalIterations <= iterations) {
            population = generationalReplacement(population, errorFunction, selection, crossover, mutation, records);
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

    public static void eliminationAlgorithm() {
        random = new Random();

        Records records = new Records("/Users/goran/eclipse-workspace/nenr_lab/src/zadaca4/zad4-dataset1.txt");
        System.out.println(records.getSize());

        Population initialPopulation = Population.createInitialPopulation(populationSize, paramSize, upperBound, lowerBound, random);
        Selection selection = new Selection();
        Crossover crossover = new Crossover();
        Mutation mutation = new Mutation();
        ErrorFunction errorFunction = new ErrorFunction();


        Population population = Population.createInitialPopulation(populationSize, paramSize, upperBound, lowerBound, random);
        errorFunction.evaluate(population, records);

        int totalIterations = 0;
        Chromosome best = null;
        int counter = 0;
        double best_error = Double.MAX_VALUE;
        while (totalIterations <= iterations && Math.abs(best_error) <= 1e-6) {
            population = eliminationReplacement(population, errorFunction, selection, crossover, mutation, records);
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

    public static Population generationalReplacement(Population population,
                                                     ErrorFunction evalFunction,
                                                     Selection selection,
                                                     Crossover crossover,
                                                     Mutation mutation,
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

        evaluate(pool, records);

        while (numOfChildren < size) {
            int index = elitism ? numOfChildren - 1 : numOfChildren;
            nextGeneration.setChromosome(numOfChildren, pool.getChromosome(index));
            numOfChildren++;
        }

        return nextGeneration;
    }

    public static void evaluate(Chromosome chromosome, Records records2) {
        double error = 0;
        Record[] records = records2.getRecords();

        for (Record record : records) {
            double measured = record.getValue();
            double predicted = Function.getValue(record.getX(), record.getY(), chromosome.getGenes());
            error += Math.pow(measured - predicted, 2);
        }

        chromosome.setFitness(error / records2.getSize());
    }

    public static Population eliminationReplacement(Population population,
                                                    ErrorFunction evalFunction,
                                                    Selection selection,
                                                    Crossover crossover,
                                                    Mutation mutation,
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
        evaluate(child, records);

        Chromosome worst = tournament[worst_i];
        for (int i = 0, n = population.getSize(); i < n; i++) {
            if (worst.equals(population.getChromosome(i))) {
                population.setChromosome(i, child);
                break;
            }
        }


        return population;
    }


    public static void evaluate(Population population, Records records) {

        for (Chromosome chromosome : population.getChromosomes()) {
            evaluate(chromosome, records);
        }

    }
}
