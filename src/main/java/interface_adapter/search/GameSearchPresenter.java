package interface_adapter.search;

import view.GameSearchView;

public class GameSearchPresenter {
    private GameSearchView view;

    public GameSearchPresenter(GameSearchView view) {
        this.view = view;
    }

    public void displayResponse(String response) {
        // Logic to update the view with the response
        System.out.println("Response: " + response);
    }
}
