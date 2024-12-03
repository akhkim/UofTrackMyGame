package entity;

import java.util.ArrayList;

/**
 * Represents a wishlist containing a collection of {@code Game} objects.
 * <p>
 * This class provides functionality to manage a list of games that a user may wish to purchase or view.
 * The {@code Wishlist} class is initialized with an empty list of games.
 * </p>
 */
public final class Wishlist {
    private ArrayList<Game> games;

    private Wishlist() {
        this.games = new ArrayList<>();
    }

    /**
     * Creates a new instance of {@code Wishlist}.
     * <p>
     * This static method provides a way to create a new empty {@code Wishlist} object.
     * It is the only method available for creating an instance of the class, as the constructor is private.
     * </p>
     *
     * @return a new {@code Wishlist} instance with no games in it
     */
    public static Wishlist create() {
        return new Wishlist();
    }

}
