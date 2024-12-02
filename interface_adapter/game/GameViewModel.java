package interface_adapter.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import entity.Game;
import interface_adapter.ViewModel;

public class GameViewModel extends ViewModel<GameState> {

    public GameViewModel() {
        super("GameView");
        this.setState(new GameState());
    }
}
