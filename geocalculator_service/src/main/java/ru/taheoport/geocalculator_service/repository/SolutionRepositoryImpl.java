package ru.taheoport.geocalculator_service.repository;

import org.springframework.stereotype.Repository;
import ru.taheoport.geocalculator_service.model.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for managing a collection of solutions
 */
@Repository
public class SolutionRepositoryImpl implements SolutionRepository{

    private final List<Solution> solutions;

    public SolutionRepositoryImpl() {
        this.solutions = new ArrayList<>();
    }
    /**
     * Clears repository
     */
    @Override
    public void clearAll() {
        solutions.clear();
    }

    /**
     * Gets number of extractions in repository
     *
     * @return int repository size
     */
    @Override
    public int size() {
        return solutions.size();
    }

    /**
     * Adds to repository new empty solution
     *
     * @return Solution
     */
    @Override
    public Solution addNewSolution() {
        Solution newSolution = new Solution();
        solutions.add(newSolution);
        return newSolution;
    }

    /**
     * Gets element with the specified index from repository
     * @param indexSolution int index
     * @return Solution
     */
    @Override
    public Solution getSolutionById(int indexSolution) {
        if (indexSolution < 0 || indexSolution >= solutions.size()) return null;
        return solutions.get(indexSolution);
    }
}
