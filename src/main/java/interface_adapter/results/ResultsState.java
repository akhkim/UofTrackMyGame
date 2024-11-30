package interface_adapter.results;

import java.util.List;
import entity.Game;

public class ResultsState {
    private String title = "";
    private String salePrice = "";
    private String normalPrice = "";
    private String isOnSale = "";
    private String savings = "";
    private String metacriticScore = "";
    private String steamRatingText = "";
    private String steamRatingPercent = "";
    private String steamRatingCount = "";
    private String dealRating = "";
    private String thumb = "";
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getTitle() {
        return title;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public String getIsOnSale() {
        return isOnSale;
    }

    public String getSavings() {
        return savings;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public String getSteamRatingText() {
        return steamRatingText;
    }

    public String getSteamRatingPercent() {
        return steamRatingPercent;
    }

    public String getSteamRatingCount() {
        return steamRatingCount;
    }

    public String getDealRating() {
        return dealRating;
    }

    public String getThumb() {
        return thumb;
    }

}
