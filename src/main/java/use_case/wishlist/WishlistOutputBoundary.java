package use_case.wishlist;

import entity.Game;

import java.util.ArrayList;

public interface WishlistOutputBoundary {
    void presentSuccess(ArrayList<Game> games);
    WishlistOutputData presentError(String message);
}
