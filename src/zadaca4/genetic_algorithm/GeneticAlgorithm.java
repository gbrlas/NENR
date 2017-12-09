package zadaca4.genetic_algorithm;

import zadaca4.functions.ErrorFunction;
import zadaca4.records.Records;

/**
 * Interface for implementing genetic algorithm variations.
 * Contains 2 most important funcions, for running the iterations and for replacing the current population.
 */
public interface GeneticAlgorithm {
    /**
     * Method which runs the genetic algorithm using previously provided parameters.
     */
    void run();

    /**
     * Method which replaces the previous population..
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
