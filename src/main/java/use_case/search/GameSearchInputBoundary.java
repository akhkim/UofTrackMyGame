package use_case.search;

public interface GameSearchInputBoundary {
    String searchByTitle(String title);
    String searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc);
} 
