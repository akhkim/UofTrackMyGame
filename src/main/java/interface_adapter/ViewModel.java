package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel<T> {
    private T state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public void setState(T state) {
        this.state = state;
        // support.firePropertyChange("state", oldState, state);
    }

    public String getViewName() {
        return this.viewName;
    }

    public T getState() {
        return state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }
} 