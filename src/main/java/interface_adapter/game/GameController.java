package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

/**
 * Controller for the Signup Use Case.
 */
public class GameController {

    private final GameInputBoundary userGameUseCaseInteractor;

    public GameController(GameInputBoundary userGameUseCaseInteractor) {
        this.GameUseCaseInteractor = userGameUseCaseInteractor;
    }

    /**
     * Executes the Game Window Use Case.
     * @param title, the title of the game.
     */
    public void execute(String title) {
        final GameInputData gameInputData = new GameInputData(title);

        userGameCaseInteractor.execute(gameInputData);
    }

    /**
     * Executes the "add to wishlist" Use Case.
     */
    public void addToWishlist() {
        userGameUseCaseInteractor.execute();
    }

}
