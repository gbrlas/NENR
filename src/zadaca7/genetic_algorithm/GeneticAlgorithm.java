package zadaca7.genetic_algorithm;

import zadaca7.functions.ANNErrorFunction;

/**
 * Interface for implementing genetic algorithm variations.
 * Contains 2 most important funcions, for running the iterations and for replacing the current population.
 */
public interface GeneticAlgorithm {
    /**
     * Method which runs the genetic algorithm using previously provided parameters.
     */
    double[] run();

    /**
     * Method which replaces the previous population..
     *
     * @param population   Previous population.
     * @param evalFunction Evalueation function.
     * @return New population.
     */
    Population populationReplacement(Population population,
                                     ANNErrorFunction evalFunction);

}
