package use_case.search;

public class GameSearchInteractor implements GameSearchInputBoundary {
    private final GameSearchDataAccessInterface dataAccess;
    private final GameSearchOutputBoundary presenter;

    public GameSearchInteractor(GameSearchDataAccessInterface dataAccess, GameSearchOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void searchByTitle(String title) {
        String response = dataAccess.searchByTitle(title);
        // System.out.println(response);
        presenter.presentSearchResults(response);
    }

    @Override
    public void searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc) {
        String response = dataAccess.searchByFilters(
            upperPrice,
            lowerPrice,
            metacritic,
            onSale ? "1" : "0",
            sortBy,
            desc ? "1" : "0"
        );
        // System.out.println(response);
        presenter.presentSearchResults(response);
    }
}