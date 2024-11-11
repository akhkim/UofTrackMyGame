package interface_adapter.search;

import entity.GameSearchState;
import use_case.search.GameSearchInputBoundary;
import interface_adapter.ViewModel;

public class GameSearchViewModel extends ViewModel<GameSearchState> {
    private final GameSearchInputBoundary inputBoundary;

    public GameSearchViewModel(GameSearchState state, GameSearchInputBoundary inputBoundary) {
        super("GameSearchView");
        this.setState(state);
        this.inputBoundary = inputBoundary;
    }

    public String searchByTitle() {
        String title = getState().getTitle();
        if (title == null || title.trim().isEmpty()) {
            return "Please enter a title to search";
        }
        return inputBoundary.searchByTitle(title);
    }

    public String searchByFilters() {
        GameSearchState state = getState();
        return inputBoundary.searchByFilters(
            state.getUpperPrice(),
            state.getLowerPrice(),
            state.getMetacritic(),
            state.isOnSale(),
            state.getSortBy(),
            state.isDesc()
        );
    }

    public String feelingLucky() {
        // Implement the logic for the "Feeling Lucky" feature
        return "Lucky search result";
    }

    // Additional methods to update state
    public void updateTitle(String title) {
        getState().setTitle(title);
        firePropertyChanged();
    }

    public void updateUpperPrice(String upperPrice) {
        getState().setUpperPrice(upperPrice);
        firePropertyChanged();
    }

    public void updateLowerPrice(String lowerPrice) {
        getState().setLowerPrice(lowerPrice);
        firePropertyChanged();
    }

    public void updateMetacritic(String metacritic) {
        getState().setMetacritic(metacritic);
        firePropertyChanged();
    }

    public void updateOnSale(boolean onSale) {
        getState().setOnSale(onSale);
        firePropertyChanged();
    }

    public void updateSortBy(String sortBy) {
        getState().setSortBy(sortBy);
        firePropertyChanged();
    }

    public void updateDesc(boolean desc) {
        getState().setDesc(desc);
        firePropertyChanged();
    }
}
