package interface_adapter.game;

import interface_adapter.ViewManagerModel;
import use_case.game.GameOutputBoundary;
import use_case.game.GameOutputData;

public class GamePresenter implements GameOutputBoundary {
    private final GameViewModel gameViewModel;
    private final ViewManagerModel viewManagerModel;

    public GamePresenter(GameViewModel gameViewModel,
                         ViewManagerModel viewManagerModel) {
        this.gameViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentGameDetails(GameOutputData outputData) {
        final GameState gameState = gameViewModel.getState();
        gameState.setGame(outputData.getGame());
        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        final GameState gameState = gameViewModel.getState();
        gameState.setError(error);
        gameViewModel.firePropertyChanged();
    }
}
