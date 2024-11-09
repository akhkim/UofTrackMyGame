package use_case.search;

import entity.GameSearchState;
import data_access.DataAccess;

public class GameSearchInteractor {
    private GameSearchState state;
    private DataAccess dataAccess;

    public GameSearchInteractor(GameSearchState state, DataAccess dataAccess) {
        this.state = state;
        this.dataAccess = dataAccess;
    }

    public String searchByTitle() {
        // Add any business logic or validation here
        return dataAccess.searchByTitle(state.getTitle());
    }

    public String searchByFilters() {
        // Add any business logic or validation here
        return dataAccess.searchByFilters(
            state.getUpperPrice(),
            state.getLowerPrice(),
            state.getMetacritic(),
            state.isOnSale() ? "1" : "0",
            state.getSortBy(),
            state.isDesc() ? "1" : "0"
        );
    }
} 