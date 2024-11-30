package interface_adapter.results;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.DefaultListModel;

import entity.Game;
import interface_adapter.ViewModel;

public class ResultsViewModel extends ViewModel<ResultsState> implements PropertyChangeListener {
    private ResultsState state = new ResultsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private javax.swing.JList<String> gamesList = new javax.swing.JList<>();

    public ResultsViewModel(ResultsState state) {
        super("ResultsView");
        this.setState(state);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResultsState state = (ResultsState) evt.getNewValue();
        updateGamesList(state);
    }

    private void updateGamesList(ResultsState state) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Game game : state.getGames()) {
            listModel.addElement(game.getTitle());
        }
        gamesList.setModel(listModel);
    }

    public void setState(ResultsState state) {
        ResultsState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state);
    }

    public ResultsState getState() {
        return state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}