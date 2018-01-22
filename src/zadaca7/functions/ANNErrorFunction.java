package zadaca7.functions;

import zadaca7.ann.ANN;
import zadaca7.dataset.Dataset;
import zadaca7.genetic_algorithm.Chromosome;
import zadaca7.genetic_algorithm.Population;

public class ANNErrorFunction {
    private ANN ann;
    private Dataset data;

    public ANNErrorFunction(ANN ann, Dataset data) {
        this.ann = ann;
        this.data = data;
    }

    public void calculateChromosomeFitness(Chromosome chromosome) {
        chromosome.setFitness(ann.calculateError(data, chromosome.getGenes()));
    }

    public void evaluatePopulation(Population population) {
        for (Chromosome chromosome : population.getChromosomes()) {
            calculateChromosomeFitness(chromosome);
        }
    }
}
