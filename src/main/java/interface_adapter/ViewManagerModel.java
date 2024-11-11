package interface_adapter;
// import interface_adapter.ViewModel;

public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel(){
        super("view manager");
        this.setState("");
    }

    // Method to switch views
    public void switchView(String viewName) {
        this.setState(viewName);
        this.firePropertyChanged();
    }
} 