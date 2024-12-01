package interface_adapter.search;

import use_case.search.GameSearchOutputBoundary;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import entity.Game;
import interface_adapter.results.ResultsState;

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
                    jsonGame.optString("thumb", "0").equals("0") ? "unavailable" : jsonGame.optString("thumb")
                );
                games.add(game);
                System.out.println(game.getTitle()); //Outputs all the titles of the games correctly
            }

            // Update ResultsViewModel with the new games
            ResultsState resultsState = resultViewModel.getState();
            resultsState.setGames(games);  
            resultViewModel.setState(resultsState);

            // Switch to results view
            this.viewManagerModel.switchView(this.resultViewModel.getViewName());
            resultViewModel.firePropertyChanged();
            
        } catch (Exception e) {
            // Handle any JSON parsing errors
            System.err.println("Error parsing game results: " + e.getMessage());
        }
    }
}
