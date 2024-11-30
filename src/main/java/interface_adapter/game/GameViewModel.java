package interface_adapter.game;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Game Window View.
 */
public class GameViewModel extends ViewModel<GameState> {

    public static final String TITLE_LABEL = "Game View";
    public static final String THRESHOLD_PRICE_LABEL = "Enter Price:";
    public static final String EMAIL_LABEL = "Enter email:";

    public GameViewModel() {
        super("game");
        setState(new GameState());
    }

}
