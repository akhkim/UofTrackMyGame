package interface_adapter.results;

import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;
import entity.Game;

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
     * @param gameTitles the list of game titles to process
     */
    public void execute(Game game) {
        final ResultsInputData resultsInputData = new ResultsInputData(game);
        resultsUseCaseInteractor.execute(resultsInputData);
    }
}