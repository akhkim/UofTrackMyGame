package entity;

import java.util.*;

public class Wishlist {
    private ArrayList<Game> games;

    private Wishlist() {
        this.games = new ArrayList<>();
    }

    public static Wishlist create() {
        return new Wishlist();
    }

}
