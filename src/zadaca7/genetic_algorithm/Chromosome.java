package zadaca7.genetic_algorithm;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Class which represents a single chromosome used in the genetic algorithm implementation.
 *
 * @author goran
 * @version 1.0
 */
public class Chromosome implements Comparable<Chromosome> {
    /**
     * Array containing the genes using double number representation.
     */
    private double[] genes;
    /**
     * Chromosome fitness value.
     */
    private double fitness;

    /**
     * Constructor which defines the chromosome size.
     *
     * @param size Chromosome size.
     */
    public Chromosome(int size) {
        genes = new double[size];
    }

    /**
     * Constructor which copies another chromosome into this one.
     *
     * @param second Chromosome to be copied
     */
    public Chromosome(Chromosome second) {
        this.genes = Arrays.copyOf(second.genes, second.getSize());
        this.fitness = second.fitness;
    }

    /**
     * Getter for the chromosome size.
     *
     * @return Size of the chromosome.
     */
    public int getSize() {
        return genes.length;
    }

    /**
     * Getter for the specific chromosome gene value.
     *
     * @param index Index of the chromosome gene.
     * @return Chromosome gene value.
     */
    public double getGene(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Gene for given index does not exist: " + index);
        }
        return genes[index];
    }

    /**
     * Getter for all the gene values.
     *
     * @return Chromosome gene values.
     */
    public double[] getGenes() {
        return genes;
    }

    /**
     * Getter for the fitness value.
     *
     * @return Fitness value for this chromosome.
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Setter for the specific chromosome gene value.
     *
     * @param index Index of the chromosome gene.
     * @param value Value to set.
     */
    public void setGene(int index, double value) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Gene for given index does not exist: " + index);
        }
        this.genes[index] = value;
    }

    /**
     * Setter for all the gene values.
     *
     * @param newGenes Array to be set as the new values.
     */
    public void setGenes(double[] newGenes) {
        if (newGenes.length != genes.length) {
            throw new IllegalArgumentException("New genes size is not compatible with the old one!");
        }
        this.genes = newGenes;
    }

    /**
     * Setter for the fitness value.
     *
     * @param fitness New fitness value to be set.
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Chromosome other) {
        if (this.fitness < other.fitness) {
            return -1;
        } else if (this.fitness > other.fitness) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Chromosome)) {
            return false;
        }

        Chromosome other = (Chromosome) obj;

        if (this.genes.equals(other.genes) && this.fitness == other.fitness) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.genes.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#.###");

        for (int i = 0; i < genes.length; i++) {
            builder.append(format.format(genes[i]) + " ");
        }

        return ("Error: " + fitness + "  " + builder.toString());
    }
}