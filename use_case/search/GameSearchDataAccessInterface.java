package use_case.search;

public interface GameSearchDataAccessInterface {
    String searchByTitle(String title);
    String searchByFilters(String upperPrice, String lowerPrice, String metacritic, 
                         String onSale, String sortBy, String desc);
}
