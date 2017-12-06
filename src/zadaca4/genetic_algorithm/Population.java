package zadaca4.genetic_algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Class which represents a population of chromosomes.
 */
public class Population {
    /**
     * Array containing all population chromosomes.
     */
    private Chromosome[] chromosomes;
    /**
     * Population size.
     */
    private int populationSize;

    /**
     * Constructor which sets the population size.
     *
     * @param size Population size.
     */
    public Population(int size) {
        this.populationSize = size;
        this.chromosomes = new Chromosome[size];
    }

    /**
     * Getter for the population size.
     *
     * @return Size of the population.
     */
    public int getSize() {
        return populationSize;
    }

    /**
     * Getter for the population chromosomes.
     *
     * @return Chromosomes in this population.
     */
    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    /**
     * Getter for a specific chromosome in this population.
     *
     * @param index Index of the chromosome.
     * @return Specific chromosome located at the provided index.
     */
    public Chromosome getChromosome(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Chromosome for given index does not exist: " + index);
        }
        return this.chromosomes[index];
    }

    /**
     * Returns the best chromosome, sorted by their fitness value.
     * Lower the value, better the chromosome.
     *
     * @return Best chromosome, sorted by fitness.
     */
    public Chromosome getBest() {
        Arrays.sort(chromosomes);
        return chromosomes[0];
    }

    /**
     * Sets th epopulation size to the provided value.
     *
     * @param newSize New population size.
     */
    public void setSize(int newSize) {
        this.chromosomes = new Chromosome[newSize];
        this.populationSize = newSize;
    }

    /**
     * Sets the chromosomes to the provided array of new ones.
     *
     * @param newChromosomes Array of new chromosomes.
     */
    public void setChromosomes(Chromosome[] newChromosomes) {
        if (newChromosomes.length != chromosomes.length) {
            throw new IllegalArgumentException("New chromosomes size is not compatible with the old one!");
        }
        this.chromosomes = newChromosomes;
    }

    /**
     * Setter for a specific chromosome.
     *
     * @param index      Index of the chromosome in the population.
     * @param chromosome Chromosome value to be set at the index location in the population.
     */
    public void setChromosome(int index, Chromosome chromosome) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Chromosome for given index does not exist: " + index);
        }
        this.chromosomes[index] = chromosome;
    }

    /**
     * Sorts the chromosomes of the population by their fitness.
     */
    public void sortByFitness() {
        Arrays.sort(chromosomes);
    }

    /**
     * Static function used for generating initial chromosome populations using provided parameters.
     *
     * @param populationSize Size of the population to be generated.
     * @param numGenes       Number of genes in a chromosome.
     * @param upperBound     Upper bound of the gene value.
     * @param lowerBound     Lower bound of the gene value.
     * @param random         Random singleton used for random number generation.
     * @return Randomly generated chromosome population.
     */
    public static Population createInitialPopulation(int populationSize, int numGenes, int upperBound, int lowerBound,
                                                     Random random) {
        Population population = new Population(populationSize);

        for (int i = 0; i < populationSize; i++) {
            population.setChromosome(i, generateChromosome(numGenes, upperBound, lowerBound, random));
        }

        return population;
    }

    /**
     * Static function used for generating random chromosomes using provided parameters.
     *
     * @param numGenes   Number of genes in a chromosome.
     * @param upperBound Upper bound of the gene value.
     * @param lowerBound Lower bound of the gene value.
     * @param random     Random singleton used for random number generation.
     * @return Randomly generated chromosome.
     */
    private static Chromosome generateChromosome(int numGenes, int upperBound, int lowerBound, Random random) {
        Chromosome chromosome = new Chromosome(numGenes);

        for (int i = 0; i < numGenes; i++) {
            chromosome.setGene(i, lowerBound + (upperBound - lowerBound) * random.nextDouble());
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