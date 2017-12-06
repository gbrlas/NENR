package zadaca4.genetic_algorithm;

import java.util.Arrays;
import java.util.Random;

public class Population {
    private Chromosome[] chromosomes;

    public Population(int size) {
        chromosomes = new Chromosome[size];
    }

    public int getSize() {
        return chromosomes.length;
    }

    public void setSize(int newSize) {
        this.chromosomes = new Chromosome[newSize];
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(Chromosome[] newChromosomes) {
        if (newChromosomes.length != chromosomes.length) {
            throw new IllegalArgumentException("New chromosomes size is not compatible with the old one!");
        }
        this.chromosomes = newChromosomes;
    }

    public Chromosome getChromosome(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Chromosome for given index does not exist: " + index);
        }
        return this.chromosomes[index];
    }

    public void setChromosome(int index, Chromosome chromosome) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Chromosome for given index does not exist: " + index);
        }
        this.chromosomes[index] = chromosome;
    }

    public Chromosome getBest() {
        Arrays.sort(chromosomes);
        return chromosomes[0];
    }

    public void sortByFitness() {
        Arrays.sort(chromosomes);
    }

    public static Population createInitialPopulation(int populationSize, int parameters, int upperBound, int lowerBound,
                                                     Random random) {
        Population population = new Population(populationSize);

        for (int i = 0; i < populationSize; i++) {
            population.setChromosome(i, generateChromosome(parameters, upperBound, lowerBound, random));
        }

        return population;
    }

    private static Chromosome generateChromosome(int parameters, int upperBound, int lowerBound, Random random) {
        Chromosome chromosome = new Chromosome(parameters);

        for (int i = 0; i < parameters; i++) {
            int ub = upperBound;
            int lb = lowerBound;
            double gene = lb + (ub - lb) * random.nextDouble();
            chromosome.setGene(i, gene);
        }

        return chromosome;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chromosomes.length; i++) {
            builder.append(chromosomes[i] + " | ");
        }

        builder.setLength(builder.length() - 3);
        return builder.toString();
    }

}