
package use_case.results;

import entity.Game;

/**
 * The ResultsInteractor class implements the ResultsInputBoundary interface and is responsible
 * for processing the results of a game search and passing the results to the output boundary (ResultsOutputBoundary).
 * This class handles the logic for verifying if the game information is complete and valid before presenting the results.
 */
public class ResultsInteractor implements ResultsInputBoundary {
    private final ResultsOutputBoundary resultsPresenter;

    public ResultsInteractor(ResultsOutputBoundary resultsPresenter) { 
        this.resultsPresenter = resultsPresenter; 
    } 

    @Override
    public void execute(ResultsInputData resultsInputData) {
        Game game = resultsInputData.getGame();
        
        if (game.getGameID() == null || game.getGameID().isEmpty() ||
            game.getTitle() == null || game.getTitle().isEmpty() ||
            game.getSalePrice() == null || game.getSalePrice().isEmpty() ||
            game.getNormalPrice() == null || game.getNormalPrice().isEmpty() ||
            game.getIsOnSale() == null || game.getIsOnSale().isEmpty() ||
            game.getSavings() == null || game.getSavings().isEmpty() ||
            game.getDealRating() == null || game.getDealRating().isEmpty() ||
            game.getStoreName() == null || game.getStoreName().isEmpty()) {
            resultsPresenter.prepareFailView("Missing required game parameters");
            return;
        }
        
        ResultsOutputData resultsOutputData = new ResultsOutputData(game);
        resultsPresenter.prepareSuccessView(resultsOutputData);
    }
}
