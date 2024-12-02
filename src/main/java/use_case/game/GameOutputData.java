package use_case.game;

public class GameOutputData {
    private final String title;
    private final String salePrice;
    private final String metacriticScore;
    private final String dealRating;

    public GameOutputData(String title, String salePrice, String metacriticScore, String dealRating) {
        this.title = title;
        this.salePrice = salePrice;
        this.metacriticScore = metacriticScore;
        this.dealRating = dealRating;
    }

    public String getTitle() {
        return title;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public String getDealRating() {
        return dealRating;
    }
}