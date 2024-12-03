
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

    /**
     * Sets the state of the ViewModel.
     * This method updates the internal state and notifies listeners about the state change.
     *
     * @param state The new state to set in the ViewModel.
     */
    public void setState(T state) {
        this.state = state;
        System.out.println("Setting state: " + state);
    }

    /**
     * Gets the name of the view associated with this ViewModel.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Gets the current state of the ViewModel.
     *
     * @return The current state of the ViewModel.
     */
    public T getState() {
        return state;
    }

    /**
     * Adds a listener to the ViewModel to listen for property change events.
     * The listener will be notified whenever the state changes.
     *
     * @param listener The PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener from the ViewModel. The listener will no longer be notified
     * about property change events.
     *
     * @param listener The PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Fires a property change event to notify all registered listeners about a change
     * in the state of the ViewModel.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }
}