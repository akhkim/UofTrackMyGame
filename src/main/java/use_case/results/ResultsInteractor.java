// FROM ARRAY OF GAMES, OUTPUT A GAME OBJECT ACCORDING TO THE CLICK

package use_case.results;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.Game;

public class ResultsInteractor implements ResultsInputBoundary {
    private final ResultsDataAccessInterface resultsDataAccessObject;
    private final ResultsOutputBoundary resultsPresenter;

    public ResultsInteractor(ResultsDataAccessInterface resultsDataAccessObject,
                           ResultsOutputBoundary resultsPresenter) {
        this.resultsDataAccessObject = resultsDataAccessObject;
        this.resultsPresenter = resultsPresenter;
    }

    @Override
    public void execute(ResultsInputData resultsInputData) {
        try {
            String jsonData = resultsDataAccessObject.getResults(resultsInputData.getGameTitles().get(0));
            
            List<Game> games = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonGame = jsonArray.getJSONObject(i);
                Game game = new Game(
                    jsonGame.getString("title"),
                    jsonGame.getString("salePrice"),
                    jsonGame.getString("normalPrice"),
                    jsonGame.getString("isOnSale"),
                    jsonGame.getString("savings"),
                    jsonGame.getString("metacriticScore"),
                    jsonGame.getString("steamRatingText"),
                    jsonGame.getString("steamRatingPercent"),
                    jsonGame.getString("steamRatingCount"),
                    jsonGame.getString("dealRating"),
                    jsonGame.getString("thumb")
                );
                games.add(game);
            }
            
            ResultsOutputData resultsOutputData = new ResultsOutputData(games);
            resultsPresenter.prepareSuccessView(resultsOutputData);
        } catch (Exception e) {
            resultsPresenter.prepareFailView("Error processing game results: " + e.getMessage());
        }
    }
}