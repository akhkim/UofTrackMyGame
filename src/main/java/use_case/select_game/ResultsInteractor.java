package use_case.select_game;

import entity.Game;

public class ResultsInteractor implements ResultsInputBoundary {
    private final ResultsOutputBoundary resultsPresenter;

    public ResultsInteractor(ResultsOutputBoundary resultsPresenter) {
        this.resultsPresenter = resultsPresenter;
    }

    @Override
    public void execute(ResultsInputData resultsInputData) {
        final Game game = resultsInputData.getGame();

        if (game != null) {
            final ResultsOutputData resultsOutputData = new ResultsOutputData(game);
            resultsPresenter.prepareSuccessView(resultsOutputData);
        } 
        else {
            resultsPresenter.prepareFailView("No information was found.");
        }
    }
}
