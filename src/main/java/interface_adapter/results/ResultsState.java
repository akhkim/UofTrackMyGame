package interface_adapter.results;

import entity.Game;
import java.util.ArrayList;

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
    private ArrayList<Game> games = new ArrayList<>();

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

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
