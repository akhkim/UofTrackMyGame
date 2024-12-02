package entity;

import org.json.JSONObject;

/**
 * Factory for creating games.
 */
public class GameFactory {
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
