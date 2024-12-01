package use_case.results;

import entity.Game;

public class ResultsInteractor implements ResultsInputBoundary {
    private final ResultsOutputBoundary resultsPresenter;

    public ResultsInteractor(ResultsOutputBoundary resultsPresenter) {
        this.resultsPresenter = resultsPresenter;
    }

    // src/main/java/use_case/results/ResultsInteractor.java
    @Override
    public void execute(ResultsInputData resultsInputData) {
        try {
            Game game = resultsInputData.getGame();
            ResultsOutputData resultsOutputData = new ResultsOutputData(game);
            resultsPresenter.prepareSuccessView(resultsOutputData);
        } catch (Exception e) {
            resultsPresenter.prepareFailView("Error processing game results: " + e.getMessage());
        }
    }   
}