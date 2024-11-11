package interface_adapter.game;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Game Window View.
 */
public class GameViewModel extends ViewModel<GameState> {

    public static final String SET_PRICE_THRESHOLD_LABEL = "Notify when...";

    public static final String ADD_TO_WISHLIST_BUTTON_LABEL = "Add to wishlist";

    public GameViewModel() {
        super("game");
        setState(new GameState());
    }

}
