package entity;

/**
 * Factory for creating games.
 */
public class GameFactory {
    /**
     * Creates a new Game.
     * @param title title of the game
     * @param normalPrice normal price of game
     * @param salePrice sale price of the game
     * @param isOnSale whether the game is on sale
     * @param savings savings from the current deal
     * @param metacriticScore metacrtic score of the game
     * @param steamRatingText
     * @param steamRatingPercent
     * @param steamRatingCount
     * @param gameID the game ID
     * @return the new game
     */
    public Game create(String title, String salePrice,
                       String normalPrice, String isOnSale, String savings,
                       String metacriticScore, String steamRatingText,
                       String steamRatingPercent, String steamRatingCount,
                       String dealRating, String thumb, String gameID){
        return new Game(title, salePrice, normalPrice, isOnSale, savings, metacriticScore,
                steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, gameID);
    }
}
