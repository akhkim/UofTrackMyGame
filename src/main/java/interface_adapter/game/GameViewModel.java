package interface_adapter.game;

public class GameViewModel {
    private String title;
    private String salePrice;
    private String metacriticScore;
    private String dealRating;

    public void updateGameDetails(String title, String salePrice, String metacriticScore, String dealRating) {
        this.title = title;
        this.salePrice = salePrice;
        this.metacriticScore = metacriticScore;
        this.dealRating = dealRating;

        // Display game details in GameView
        new view.GameView(title, salePrice, metacriticScore, dealRating);
    }
}