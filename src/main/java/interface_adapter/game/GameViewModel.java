package interface_adapter.game;

import interface_adapter.ViewModel;

public class GameViewModel extends ViewModel<GameState> {

    public GameViewModel() {
        super("GameView");
        this.setState(new GameState());
    }
}
