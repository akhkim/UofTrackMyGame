package use_case.search;

import org.json.JSONObject;

public class GameSearchInteractor implements GameSearchInputBoundary {
    private final GameSearchDataAccessInterface dataAccess;

    public GameSearchInteractor(GameSearchDataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public String searchByTitle(String title) {
        String response = dataAccess.searchByTitle(title);
        // return new JSONObject(response).toString();
    }

    @Override
    public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc) {
        String response = dataAccess.searchByFilters(
            upperPrice,
            lowerPrice,
            metacritic,
            onSale ? "1" : "0",
            sortBy,
            desc ? "1" : "0"
        );
        return new JSONObject(response).toString();
    }
} 
