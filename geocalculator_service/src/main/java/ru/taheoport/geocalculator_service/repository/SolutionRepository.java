package ru.taheoport.geocalculator_service.repository;

import ru.taheoport.geocalculator_service.model.Solution;

/**
 * Defines methods for managing a collection of solutions
 */
public interface SolutionRepository {
    /**
     * Clears repository
     */
    void clearAll();

    /**
     * Gets number of extractions in repository
     * @return int repository size
     */
    int size();

    /**
     * Adds to repository new empty solution
     * @return Solution
     */
    Solution addNewSolution();

    /**
     * Gets element with the specified index from repository
     * @param indexSolution int index
     * @return Solution
     */
    Solution getSolutionById(int indexSolution);

}
