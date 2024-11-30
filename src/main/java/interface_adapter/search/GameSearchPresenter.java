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
        // Format the response if needed
        view.displayResponse(response);
    }
}
