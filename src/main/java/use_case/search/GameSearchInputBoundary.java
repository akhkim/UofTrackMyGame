package use_case.search;

public interface GameSearchInputBoundary {
    void searchByTitle(String title);
    void searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc);
} 