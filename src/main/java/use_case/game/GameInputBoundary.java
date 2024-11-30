package use_case.game;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface GameInputBoundary {

    /**
     * Open game window use case.
     * @param gameInputData the input data
     */
    void gameWindow(GameInputData gameInputData);

    /**
     * Add to wishlist use case
     * @param gameInputData the input data
     * @param email the user's email
     * @param thresholdPrice the price at which the user wants to be notified
     */
    void addToWishlist(GameInputData gameInputData, String email, String thresholdPrice);
}
