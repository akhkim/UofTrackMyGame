package app;
import entity.GameSearchState;
import interface_adapter.GameSearchController;
import interface_adapter.GameSearchPresenter;
import interface_adapter.GameSearchViewModel;
import use_case.search.DataAccess;
import view.GameSearchView;

public class GameSearchApp {
    public static void main(String[] args) {
        GameSearchState state = new GameSearchState();
        DataAccess dataAccess = new DataAccess();
        GameSearchView view = new GameSearchView();
        GameSearchViewModel viewModel = new GameSearchViewModel(state, dataAccess);
        GameSearchPresenter presenter = new GameSearchPresenter(view);
        GameSearchController controller = new GameSearchController(viewModel, view);

        // Show the UI
        view.show();
    }
} 