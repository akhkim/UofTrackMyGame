package entity;

import org.json.JSONObject;

/**
 * Factory for creating games.
 */
public class GameFactory {
    /**
     * Creates a new {@code Game} object from the provided {@code JSONObject}.
     * <p>
     * This method extracts the necessary details from the given {@code JSONObject} to construct a {@code Game} object.
     * It uses the {@code optString()} method to retrieve the values, ensuring that if a key is missing,
     * an empty string is used instead of throwing an exception.
     * </p>
     *
     * @param jsonGame the {@code JSONObject} containing the game details
     * @return a new {@code Game} object constructed from the {@code JSONObject} data
     */
    public Game create(JSONObject jsonGame) {
        return new Game(
            jsonGame.optString("gameID"),
            jsonGame.optString("title"),
            jsonGame.optString("salePrice"),
            jsonGame.optString("normalPrice"),
            jsonGame.optString("isOnSale"),
            jsonGame.optString("savings"),
            jsonGame.optString("metacriticScore"),
            jsonGame.optString("steamRatingText"),
            jsonGame.optString("steamRatingPercent"),
            jsonGame.optString("steamRatingCount"),
            jsonGame.optString("dealRating"),
            jsonGame.optString("thumb"),
            jsonGame.optString("storeName")
        );
    }
}
