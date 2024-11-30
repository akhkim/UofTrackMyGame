package use_case.game;

import entity.Game;

/**
 * DAO for the Game Window and Add to Wishlist Use Cases.
 */
public interface GameDataAccessInterface {
    /**
     * Saves the game.
     * @param game the user to save
     */
    void save(Game game);
}
