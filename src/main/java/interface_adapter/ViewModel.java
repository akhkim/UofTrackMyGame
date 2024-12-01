package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel for our CA implementation.
 * This class delegates work to a PropertyChangeSupport object for
 * managing the property change events.
 *
 * @param <T> The type of state object contained in the model.
 */
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
        System.out.println("Setting state: " + state);
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