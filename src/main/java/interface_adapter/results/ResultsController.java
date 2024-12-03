
package interface_adapter.results;

import entity.Game;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;

/**
 * The controller for the Results Use Case.
 */
public class ResultsController {

    private final ResultsInputBoundary resultsUseCaseInteractor;

    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }

    /**
     * Executes the Results Use Case.
     * @param game the list of game titles to process
     */
    public void execute(Game game) {
        final ResultsInputData resultsInputData = new ResultsInputData(game);
        resultsUseCaseInteractor.execute(resultsInputData);
    }
}