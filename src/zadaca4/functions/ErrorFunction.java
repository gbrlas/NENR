package zadaca4.functions;

import zadaca4.genetic_algorithm.Chromosome;
import zadaca4.genetic_algorithm.Population;
import zadaca4.records.Record;
import zadaca4.records.Records;

/**
 * Class which implements the error function.
 *
 * @author goran
 * @version 1.0
 */
public class ErrorFunction {
    /**
     * Evaluates the provided chromosome based on the measured records and the chromosome values.
     *
     * @param chromosome Chromosome to be evaluated.
     * @param rec        Measured records.
     */
    public void calculateChromosomeFitness(Chromosome chromosome, Records rec) {
        double error = 0;
        Record[] records = rec.getRecords();

        for (Record record : records) {
            double measured = record.getValue();
            double predicted = Function.getValue(record.getX(), record.getY(), chromosome.getGenes());

            error += Math.pow(measured - predicted, 2);
        }

        chromosome.setFitness(error / rec.getSize());
    }

    /**
     * Evaluates the provided population, one chromosome at a time.
     *
     * @param population Population to be evaluated.
     * @param records    Measured records.
     */
    public void evaluatePopulation(Population population, Records records) {
        for (Chromosome chromosome : population.getChromosomes()) {
            calculateChromosomeFitness(chromosome, records);
        }
    }
}
