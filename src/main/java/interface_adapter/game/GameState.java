package interface_adapter.game;

/**
 * The state for the Game View Model.
 */
public class GameState {
    private String title;
    private String salePrice;
    private String normalPrice;
    private String isOnSale;
    private String savings;
    private String metacriticScore;
    private String steamRatingText;
    private String steamRatingPercent;
    private String steamRatingCount;
    private String dealRating;
    private String thumb;
    private String gameID;
    private String priceThreshold;

    public String getTitle() {
        return title;
    }

    public String getSalePrice() {return salePrice;}

    public String getNormalPrice() {return normalPrice;}

    public String getIsOnSale() {return isOnSale;}

    public String getSavings() {return savings;}

    public String getMetacriticScore() {return metacriticScore;}

    public String getSteamRatingText() {return steamRatingText;}

    public String getSteamRatingPercent() {return steamRatingPercent;}

    public String getSteamRatingCount() {return steamRatingCount;}

    public String getDealRating() {return dealRating;}

    public String getThumb() {return thumb;}

    public String getGameID() {return gameID;}

    public String getPriceThreshold() {return priceThreshold;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalePrice(String salePrice) {this.salePrice = salePrice;}

    public void setNormalPrice(String normalPrice) {this.normalPrice = normalPrice;}

    public void setIsOnSale(String isOnSale) {this.isOnSale = isOnSale;}

    public void setSavings(String savings) {this.savings = savings;}

    public void setMetacriticScore(String metacriticScore) {this.metacriticScore = metacriticScore;}

    public void setSteamRatingText(String steamRatingText) {this.steamRatingText = steamRatingText;}

    public void setSteamRatingPercent(String steamRatingPercent) {this.steamRatingPercent = steamRatingPercent;}

    public void setSteamRatingCount(String steamRatingCount) {this.steamRatingCount = steamRatingCount;}

    public void setDealRating(String dealRating) {this.dealRating = dealRating;}

    public void setThumb(String thumb) {this.thumb = thumb;}

    public void setGameID(String gameID) {this.gameID = gameID;}

    public void setPriceThreshold(String priceThreshold) {this.priceThreshold = priceThreshold;}

    @Override
    public String toString() {
        return "GameState{"
                + "title='" + title + '\''
                + ", priceThreshold='" + priceThreshold + '\''
                + '}';
    }
}
