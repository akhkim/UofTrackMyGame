package interface_adapter.search;

import use_case.search.GameSearchInputBoundary;
import view.GameSearchView;

public class GameSearchPresenter {
    private GameSearchView view;
    private final GameSearchInputBoundary interactor;

    public GameSearchPresenter(GameSearchView view, GameSearchInputBoundary interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void displayResponse(String response) {
        view.displayResponse(response);
    }
}
