
package interface_adapter.search;

import use_case.search.GameSearchInputBoundary;
import view.GameSearchView;

public class GameSearchPresenter {
    private GameSearchView view;
    private GameSearchViewModel viewModel;

    public GameSearchPresenter(GameSearchView view, GameSearchViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void presentSearchResults(String response) {
        // Format the response if needed
        view.displayResponse(response);
    }
}
