
package entity;

/**
 * The {@code Game} class represents a game with various details such as title, prices, ratings, and store information.
 * This class is used to store and retrieve information about a specific game.
 * <p>
 * It contains fields for the game's title, sale price, normal price, sale status, savings, Metacritic score, Steam
 * rating, deal rating, thumbnail image, and store name, along with corresponding getter methods to access these
 * details.
 * </p>
 */
public class Game {
    private final String title;
    private final String salePrice;
    private final String normalPrice;
    private final String isOnSale;
    private final String savings;
    private final String metacriticScore;
    private final String steamRatingText;
    private final String steamRatingPercent;
    private final String steamRatingCount;
    private final String dealRating;
    private final String thumb;
    private final String gameID;
    private final String storeName;

    /**
     * Constructs a new {@code Game} object with the specified details.
     *
     * @param gameID the unique identifier for the game
     * @param title the title of the game
     * @param salePrice the sale price of the game
     * @param normalPrice the normal price of the game
     * @param isOnSale whether the game is on sale
     * @param savings the savings from the normal price
     * @param metacriticScore the Metacritic score of the game
     * @param steamRatingText the Steam rating text (e.g., "Very Positive")
     * @param steamRatingPercent the percentage of positive Steam ratings
     * @param steamRatingCount the number of Steam ratings for the game
     * @param dealRating the deal rating for the game
     * @param thumb the URL of the game's thumbnail image
     * @param storeName the name of the store selling the game
     */
    public Game(String gameID, String title, String salePrice,
                     String normalPrice, String isOnSale, String savings,
                     String metacriticScore, String steamRatingText, 
                     String steamRatingPercent, String steamRatingCount,
                     String dealRating, String thumb, String storeName) {
        this.title = title;
        this.salePrice = salePrice;
        this.normalPrice = normalPrice;
        this.isOnSale = isOnSale;
        this.savings = savings;
        this.metacriticScore = metacriticScore;
        this.steamRatingText = steamRatingText;
        this.steamRatingPercent = steamRatingPercent;
        this.steamRatingCount = steamRatingCount;
        this.dealRating = dealRating;
        this.thumb = thumb;
        this.gameID = gameID;
        this.storeName = storeName;
    }

    /**
     * Returns the title of the game.
     *
     * @return the title of the game
     */
    public String getTitle() {
        return title; 
    }

    /**
     * Returns the sale price of the game.
     *
     * @return the sale price of the game
     */
    public String getSalePrice() {
        return salePrice;
    }

    /**
     * Returns the normal price of the game.
     *
     * @return the normal price of the game
     */
    public String getNormalPrice() {
        return normalPrice;
    }

    /**
     * Returns whether the game is on sale.
     *
     * @return the sale status of the game
     */
    public String getIsOnSale() {
        return isOnSale;
    }

    /**
     * Returns the savings from the normal price.
     *
     * @return the savings on the game
     */
    public String getSavings() {
        return savings;
    }

    /**
     * Returns the Metacritic score of the game.
     *
     * @return the Metacritic score
     */
    public String getMetacriticScore() {
        return metacriticScore;
    }

    /**
     * Returns the Steam rating text (e.g., "Very Positive").
     *
     * @return the Steam rating text
     */
    public String getSteamRatingText() {
        return steamRatingText;
    }

    /**
     * Returns the percentage of positive Steam ratings for the game.
     *
     * @return the percentage of positive Steam ratings
     */
    public String getSteamRatingPercent() {
        return steamRatingPercent;
    }

    /**
     * Returns the number of Steam ratings for the game.
     *
     * @return the number of Steam ratings
     */
    public String getSteamRatingCount() {
        return steamRatingCount;
    }

    /**
     * Returns the deal rating for the game.
     *
     * @return the deal rating
     */
    public String getDealRating() {
        return dealRating;
    }

    /**
     * Returns the URL of the game's thumbnail image.
     *
     * @return the thumbnail URL
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * Returns the unique identifier of the game.
     *
     * @return the game ID
     */
    public String getGameID() {
        return gameID;
    }

    /**
     * Returns the name of the store selling the game.
     *
     * @return the store name
     */
    public String getStoreName() {
        return storeName;
    }
}