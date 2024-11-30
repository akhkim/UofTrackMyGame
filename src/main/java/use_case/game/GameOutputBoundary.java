package use_case.game;

/**
 * Output boundary for Game Window and Add to Wishlist Use Cases.
 */
public interface GameOutputBoundary {
    /**
     * Opens Game Window.
     */
    void prepareSuccessView(GameOutputData gameOutputData);

    /**
     * Switches to the Wishlist View.
     */
    void switchToWishlistView();
}
