package interface_adapter;
// import interface_adapter.ViewModel;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel(){
        super("view manager");
        this.setState("");
    }

    // Method to switch views
    public void switchView(String viewName) {
        System.out.println("Switching to view: " + viewName);
        this.setState(viewName);
        this.firePropertyChanged();
    }
}