package interface_adapter.game;

import interface_adapter.ViewManagerModel;
import use_case.game.GameInputData;
import use_case.game.GameOutputBoundary;
import use_case.game.GameOutputData;

/**
 * The Presenter for the Game Window Use Case.
 */
public class GamePresenter implements GameOutputBoundary {

    private final GameViewModel gameViewModel;
    private final ViewManagerModel viewManagerModel;

    public GamePresenter(ViewManagerModel viewManagerModel,
                           GameViewModel gameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void prepareSuccessView(GameOutputData gameOutputData) {

    }

    @Override
    public void switchToWishlistView(){
        //TODO: fix this
        viewManagerModel.setState();
    }



}
