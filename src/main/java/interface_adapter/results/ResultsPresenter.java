package interface_adapter.results;

import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameState;
import interface_adapter.game.GameViewModel;
import use_case.select_game.ResultsOutputBoundary;
import use_case.select_game.ResultsOutputData;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;
    private final GameViewModel gameViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResultsPresenter(ResultsViewModel resultsViewModel, GameViewModel gameViewModel, ViewManagerModel viewManagerModel) {
        this.resultsViewModel = resultsViewModel;
        this.gameViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ResultsOutputData game) {
        
        final GameState gameState = gameViewModel.getState();
        gameState.setGame(game.getGame());
        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        
        final ResultsState resultState = resultsViewModel.getState();
        resultState.setError(error);
        resultsViewModel.firePropertyChanged();
    }
}