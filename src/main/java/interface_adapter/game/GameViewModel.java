
package interface_adapter.game;

import interface_adapter.ViewModel;

/**
 * The {@code GameViewModel} class is responsible for managing the game state in the view model.
 * <p>
 * This class extends the {@code ViewModel} class and specializes it for the game-related state management.
 * It initializes the state of the view model to a new instance of {@code GameState}.
 * </p>
 */
public class GameViewModel extends ViewModel<GameState> {

    public GameViewModel() {
        super("GameView");
        this.setState(new GameState());
    }
}
