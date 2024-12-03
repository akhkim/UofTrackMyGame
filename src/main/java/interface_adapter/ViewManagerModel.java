
package interface_adapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }

    /**
     * Switches to the specified view and updates the state of the view manager.
     * This method changes the current view to the given `viewName` and notifies any listeners
     * of the state change to reflect the new view.
     *
     * @param viewName The name of the view to switch to.
     */
    public void switchView(String viewName) {
        System.out.println("Switching to view: " + viewName);
        this.setState(viewName);
        this.firePropertyChanged();
    }
}