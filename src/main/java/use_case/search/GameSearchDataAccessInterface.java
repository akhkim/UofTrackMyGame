package use_case.search;

/**
 * The GameSearchDataAccessInterface defines the contract for data access operations
 * related to searching for games. This interface provides methods to perform searches
 * based on specific filters or a game title.
 */
public interface GameSearchDataAccessInterface {

    /**
     * Searches for games by their title.
     *
     * @param title The title of the game to search for.
     * @return A string containing the search results for games that match the given title.
     */
    String searchByTitle(String title);

    /**
     * Searches for games using specific filters such as price, Metacritic score,
     * sale status, sorting criteria, and order.
     *
     * @param upperPrice The upper bound of the price range.
     * @param lowerPrice The lower bound of the price range.
     * @param metacritic The Metacritic score filter.
     * @param onSale A flag indicating whether the game is on sale.
     * @param sortBy The field by which the results should be sorted.
     * @param desc A flag indicating whether the results should be sorted in descending order.
     * @return A string containing the search results based on the provided filters.
     */
    String searchByFilters(String upperPrice, String lowerPrice, String metacritic,
                         String onSale, String sortBy, String desc);
}
