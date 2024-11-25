package use_case.wishlist;

public class WishlistInputData {
    private final String gameTitle;

    public WishlistInputData(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameTitle() {
        return gameTitle;
    }
}
