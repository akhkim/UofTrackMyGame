package interface_adapter.search;
import entity.GameSearchState;
import data_access.DataAccess;
import use_case.search.GameSearchInteractor;

public class GameSearchViewModel {
    private GameSearchState state;
    private GameSearchInteractor interactor;

    public GameSearchViewModel(GameSearchState state, DataAccess dataAccess) {
        this.state = state;
        this.interactor = new GameSearchInteractor(state, dataAccess);
    }

    public String searchByTitle() {
        return interactor.searchByTitle();
    }

    public String searchByFilters() {
        return interactor.searchByFilters();
    }

    // Additional methods to update state
    public void updateTitle(String title) {
        state.setTitle(title);
    }

    public void updateUpperPrice(String upperPrice) {
        state.setUpperPrice(upperPrice);
    }

    public void updateLowerPrice(String lowerPrice) {
        state.setLowerPrice(lowerPrice);
    }

    public void updateMetacritic(String metacritic) {
        state.setMetacritic(metacritic);
    }

    public void updateOnSale(boolean onSale) {
        state.setOnSale(onSale);
    }

    public void updateSortBy(String sortBy) {
        state.setSortBy(sortBy);
    }

    public void updateDesc(boolean desc) {
        state.setDesc(desc);
    }
}
