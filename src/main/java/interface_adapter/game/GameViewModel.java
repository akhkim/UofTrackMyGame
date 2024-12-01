package interface_adapter.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import entity.Game;
import interface_adapter.ViewModel;

public class GameViewModel extends ViewModel<Game> implements PropertyChangeListener {
    private GameState state = new GameState();
    private Game game;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GameViewModel(GameState state) {
        super("GameView");
        this.setState(state);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameState updatedGame = (GameState) evt.getNewValue();
        setGame(updatedGame.getGame());
    }

    public void setState(GameState gameState){
        GameState oldState = this.state;
        this.state = gameState;
        support.firePropertyChange("state", oldState, state);
    }

    public void setGame(Game game) {
        Game oldGame = this.game;
        this.game = game;
        support.firePropertyChange("game", oldGame, game);
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
