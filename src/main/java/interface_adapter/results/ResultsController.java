package interface_adapter.results;

import entity.Game;
import use_case.select_game.ResultsInputBoundary;
import use_case.select_game.ResultsInputData;

/**
 * The controller for the Results Use Case.
 */
public class ResultsController {

    private final ResultsInputBoundary resultsUseCaseInteractor;

    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }

    public void execute(Game game) {
        ResultsInputData inputData = new ResultsInputData(game);
        resultsUseCaseInteractor.execute(inputData);
    }
}
