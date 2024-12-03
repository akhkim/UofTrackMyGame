
package interface_adapter.search;

/**
 * The {@code GameSearchState} class represents the state of the game search form,
 * including the search parameters like title, price range, metacritic score,
 * sale status, and sorting preferences.
 * <p>
 * This class holds the values that define the search criteria and can be
 * used to transfer and manipulate the search state within the application.
 * </p>
 */
public class GameSearchState {
    private String title;
    private String upperPrice = "10000"; 
    private String lowerPrice = "0";     
    private String metacritic = "0";     
    private boolean onSale;
    private String sortBy = "DealRating"; 
    private boolean desc = true;

    /**
     * Gets the title to search for.
     *
     * @return the title to search for
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title to search for.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the upper price limit for the search.
     *
     * @return the upper price limit
     */
    public String getUpperPrice() {
        return upperPrice;
    }

    /**
     * Sets the upper price limit for the search.
     *
     * @param upperPrice the upper price limit to set
     */
    public void setUpperPrice(String upperPrice) {
        this.upperPrice = upperPrice;
    }

    /**
     * Gets the lower price limit for the search.
     *
     * @return the lower price limit
     */
    public String getLowerPrice() {
        return lowerPrice;
    }

    /**
     * Sets the lower price limit for the search.
     *
     * @param lowerPrice the lower price limit to set
     */
    public void setLowerPrice(String lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    /**
     * Gets the metacritic score threshold for the search.
     *
     * @return the metacritic score threshold
     */
    public String getMetacritic() {
        return metacritic;
    }

    /**
     * Sets the metacritic score threshold for the search.
     *
     * @param metacritic the metacritic score threshold to set
     */
    public void setMetacritic(String metacritic) {
        this.metacritic = metacritic;
    }

    /**
     * Gets whether the search is filtering by sale status.
     *
     * @return {@code true} if filtering by sale status, {@code false} otherwise
     */
    public boolean isOnSale() {
        return onSale;
    }

    /**
     * Sets whether the search should filter by sale status.
     *
     * @param onSale {@code true} to filter by sale status, {@code false} otherwise
     */
    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    /**
     * Gets the sorting criterion for the search results.
     *
     * @return the sorting criterion (e.g., "DealRating")
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets the sorting criterion for the search results.
     *
     * @param sortBy the sorting criterion to set
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Gets the sort order for the search results.
     *
     * @return {@code true} if sorting in descending order, {@code false} otherwise
     */
    public boolean isDesc() {
        return desc;
    }

    /**
     * Sets the sort order for the search results.
     *
     * @param desc {@code true} to sort in descending order, {@code false} otherwise
     */
    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
