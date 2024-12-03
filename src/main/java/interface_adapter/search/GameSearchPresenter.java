
package interface_adapter.search;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import use_case.search.GameSearchOutputBoundary;

/**
 * The {@code GameSearchPresenter} class is responsible for presenting the results of
 * a game search to the view. It receives search results, parses the data into game entities,
 * updates the {@code ResultsViewModel} with the results, and manages the transition
 * to the results view.
 */
public class GameSearchPresenter implements GameSearchOutputBoundary {
    private ResultsViewModel resultViewModel;
    private ViewManagerModel viewManagerModel;

    public GameSearchPresenter(ResultsViewModel resultViewModel, ViewManagerModel viewManagerModel) {
        this.resultViewModel = resultViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentSearchResults(String response) {
        try {
            // Parse JSON response into array of Game entities
            JSONArray jsonArray = new JSONArray(response);
            ArrayList<Game> games = new ArrayList<>();
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonGame = jsonArray.getJSONObject(i);
                Game game = new Game(
                    jsonGame.getString("gameID"),
                    jsonGame.getString("title"),
                    jsonGame.getString("salePrice"),
                    jsonGame.getString("normalPrice"),
                    jsonGame.getString("isOnSale"),
                    jsonGame.getString("savings"),
                    jsonGame.optString("metacriticScore", "0").equals("0") ? "unavailable" : jsonGame.optString("metacriticScore"),
                    jsonGame.optString("steamRatingText", "0").equals("0") ? "unavailable" : jsonGame.optString("steamRatingText"),
                    jsonGame.optString("steamRatingPercent", "0").equals("0") ? "unavailable" : jsonGame.optString("steamRatingPercent"),
                    jsonGame.optString("steamRatingCount", "0").equals("0") ? "unavailable" : jsonGame.optString("steamRatingCount"),
                    jsonGame.optString("dealRating", "0").equals("0") ? "unavailable" : jsonGame.optString("dealRating"),
                    jsonGame.optString("thumb", "0").equals("0") ? "unavailable" : jsonGame.optString("thumb"),
                    jsonGame.optString("storeName", "0").equals("0") ? "unavailable" : jsonGame.optString("storeName")
                );
                games.add(game);
                System.out.println(game.getTitle());
            }

            // Update ResultsViewModel with the new games
            ResultsState resultsState = resultViewModel.getState();
            resultsState.setGames(games);  
            resultViewModel.setState(resultsState);

            // Switch to results view
            this.viewManagerModel.switchView(this.resultViewModel.getViewName());
            resultViewModel.firePropertyChanged();
            
        }
        catch (Exception exception) {
            // Handle any JSON parsing errors
            System.err.println("Error parsing game results: " + exception.getMessage());
        }
    }
}