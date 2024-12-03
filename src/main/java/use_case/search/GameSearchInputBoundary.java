
package use_case.search;

/**
 * The GameSearchInputBoundary defines the contract for the input use case related to
 * searching for games. This interface provides methods to search for games either
 * by their title or by applying multiple filters.
 */
public interface GameSearchInputBoundary {

    /**
     * Searches for games by their title.
     *
     * @param title The title of the game to search for.
     */
    void searchByTitle(String title);

    /**
     * Searches for games using various filters such as price range, Metacritic score,
     * sale status, sorting criteria, and order.
     *
     * @param upperPrice The upper bound of the price range.
     * @param lowerPrice The lower bound of the price range.
     * @param metacritic The Metacritic score filter.
     * @param onSale A flag indicating whether the game is on sale.
     * @param sortBy The field by which the results should be sorted.
     * @param desc A flag indicating whether the results should be sorted in descending order.
     */
    void searchByFilters(String upperPrice, String lowerPrice, String metacritic, boolean onSale, String sortBy, boolean desc);
} 