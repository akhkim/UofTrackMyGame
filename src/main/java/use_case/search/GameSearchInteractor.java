package use_case.search;

public class GameSearchInteractor implements GameSearchInputBoundary {
    private final GameSearchDataAccessInterface dataAccess;

    public GameSearchInteractor(GameSearchDataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public String searchByTitle(String title) {
        return dataAccess.searchByTitle(title);
    }

    @Override
    public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc) {
        return dataAccess.searchByFilters(
            upperPrice,
            lowerPrice,
            metacritic,
            onSale ? "1" : "0",
            sortBy,
            desc ? "1" : "0"
        );
    }
} 