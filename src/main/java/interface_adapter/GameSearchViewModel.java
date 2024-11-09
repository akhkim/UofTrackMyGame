package interface_adapter;
import entity.GameSearchState;
import use_case.search.DataAccess;

public class GameSearchViewModel {
    private GameSearchState state;
    private DataAccess dataAccess;

    public GameSearchViewModel(GameSearchState state, DataAccess dataAccess) {
        this.state = state;
        this.dataAccess = dataAccess;
    }

    public String searchByTitle() {
        return dataAccess.searchByTitle(state.getTitle());
    }

    public String searchByFilters() {
        return dataAccess.searchByFilters(
            state.getUpperPrice(),
            state.getLowerPrice(),
            state.getMetacritic(),
            state.isOnSale() ? "1" : "0",
            state.getSortBy(),
            state.isDesc() ? "1" : "0"
        );
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
