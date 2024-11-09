package use_case.results;

/**
 * Input Boundary for actions which are related to results.
 */
public interface ResultsInputBoundary {

    /**
     * Executes the results use case.
     * @param ResultsInputData the input data
     */
    void execute(ResultsInputData ResultsInputData);
}
