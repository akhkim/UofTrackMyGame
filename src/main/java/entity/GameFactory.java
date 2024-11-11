package entity;

import org.json.JSONObject;

public class GameFactory {
    public Game create(JSONObject jsonGame) {
        return new Game(
            jsonGame.getString("title"),
            jsonGame.getString("salePrice"),
            jsonGame.getString("normalPrice"),
            jsonGame.getString("isOnSale"),
            jsonGame.getString("savings"),
            jsonGame.getString("metacriticScore"),
            jsonGame.getString("steamRatingText"),
            jsonGame.getString("steamRatingPercent"),
            jsonGame.getString("steamRatingCount"),
            jsonGame.getString("dealRating"),
            jsonGame.getString("thumb")
        );
    }
}
