package interface_adapter.results;

import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameViewModel;
import interface_adapter.game.GameState;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;
    private final GameViewModel gameViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResultsPresenter(ResultsViewModel resultsViewModel,
                            GameViewModel gameViewModel,
                            ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void prepareSuccessView(ResultsOutputData response) {
        final GameState gameState = gameViewModel.getState();
        gameState.setGame(response.getGame());
        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();


        this.viewManagerModel.setState(gameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        final ResultsState resultsState = resultsViewModel.getState();
        resultsState.setError(error);
        resultsViewModel.firePropertyChanged();
    }
}
