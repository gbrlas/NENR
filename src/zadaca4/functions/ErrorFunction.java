package zadaca4.functions;

import zadaca4.genetic_algorithm.Chromosome;
import zadaca4.genetic_algorithm.Population;
import zadaca4.records.Record;
import zadaca4.records.Records;

public class ErrorFunction {
    public void evaluate(Chromosome chromosome, Records records2) {
        double error = 0;
        Record[] records = records2.getRecords();

        for (Record record : records) {
            double measured = record.getValue();
            double predicted = Function.getValue(record.getX(), record.getY(), chromosome.getGenes());

            error += Math.pow(measured - predicted, 2);
        }

        chromosome.setFitness(error / records2.getSize());
    }

    public void evaluate(Population population, Records records2) {

        for (Chromosome chromosome : population.getChromosomes()) {
            evaluate(chromosome, records2);
        }

    }
}
