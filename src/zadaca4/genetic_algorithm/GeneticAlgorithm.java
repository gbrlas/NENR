package zadaca4.genetic_algorithm;

import zadaca4.functions.ErrorFunction;
import zadaca4.records.Records;

public interface GeneticAlgorithm {
    /**
     * Method which runs the genetic algorithm using previously provided parameters.
     */
    void run();

    /**
     * Method which replaces the previous population using elimination.
     *
     * @param population   Previous population.
     * @param evalFunction Evalueation function.
     * @param records      Measurement records.
     * @return New population.
     */
    Population populationReplacement(Population population,
                                     ErrorFunction evalFunction,
                                     Records records);

}
