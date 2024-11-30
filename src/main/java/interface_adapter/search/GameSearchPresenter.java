package interface_adapter.search;

import use_case.search.GameSearchOutputBoundary;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class GameSearchPresenter implements GameSearchOutputBoundary {
    private GameSearchViewModel viewModel;
    private ResultsViewModel resultViewModel;
    private ViewManagerModel viewManagerModel;

    public GameSearchPresenter(GameSearchViewModel viewModel, ResultsViewModel resultViewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
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

            // Update ResultsViewModel with the new games
            ResultsState resultsState = resultViewModel.getState();
            resultsState.setGames(games);  // You'll need to add this setter to ResultsState
            resultViewModel.setState(resultsState);
            resultViewModel.firePropertyChanged();

            // Switch to results view
            viewManagerModel.setState(resultViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
            
        } catch (Exception e) {
            // Handle any JSON parsing errors
            System.err.println("Error parsing game results: " + e.getMessage());
        }
    }
}
