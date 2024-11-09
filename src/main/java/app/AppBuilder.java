package app;

import entity.GameSearchState;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchViewModel;
import data_access.DataAccess;
import view.GameSearchView;

public class AppBuilder {
    public static GameSearchController buildGameSearchApp() {
        GameSearchState state = new GameSearchState();
        DataAccess dataAccess = new DataAccess();
        GameSearchView view = new GameSearchView();
        GameSearchViewModel viewModel = new GameSearchViewModel(state, dataAccess);
        GameSearchPresenter presenter = new GameSearchPresenter(view);
        return new GameSearchController(viewModel, view);
    }
}
