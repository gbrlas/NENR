package zadaca4.genetic_algorithm;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Chromosome implements Comparable<Chromosome> {
    private double[] genes;
    private double fitness;

    public Chromosome(int size) {
        genes = new double[size];
    }

    public Chromosome(Chromosome other) {
        this.genes = Arrays.copyOf(other.genes, other.getSize());
        this.fitness = other.fitness;
    }

    public int getSize() {
        return genes.length;
    }

    public double getGene(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Gene for given index does not exist: " + index);
        }
        return genes[index];
    }

    public void setGene(int index, double value) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Gene for given index does not exist: " + index);
        }
        this.genes[index] = value;
    }

    public double[] getGenes() {
        return genes;
    }

    public void setGenes(double[] newGenes) {
        if (newGenes.length != genes.length) {
            throw new IllegalArgumentException("New genes size is not compatible with the old one!");
        }
        this.genes = newGenes;
    }

    public double getFitness() {
        return fitness;
    }

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

        return (builder.toString() + ", error=" + fitness);
    }
}