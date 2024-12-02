package interface_adapter.game;

import use_case.game.GameOutputBoundary;
import use_case.game.GameOutputData;

public class GamePresenter implements GameOutputBoundary {
    private final GameViewModel viewModel;

    public GamePresenter(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentGameDetails(GameOutputData outputData) {
        // Update the view model with game details
        viewModel.updateGameDetails(
                outputData.getTitle(),
                outputData.getSalePrice(),
                outputData.getMetacriticScore(),
                outputData.getDealRating()
        );
    }
}