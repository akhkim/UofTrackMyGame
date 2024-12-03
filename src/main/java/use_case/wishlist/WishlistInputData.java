package use_case.wishlist;

public class WishlistInputData {
    private final String gameID;

    public WishlistInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
