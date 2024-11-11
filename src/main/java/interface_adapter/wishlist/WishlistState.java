package interface_adapter.wishlist;

import java.util.ArrayList;

public class WishlistState {
    private ArrayList<String> gameTitles;
    private ArrayList<String> notifyCriteria;

    public WishlistState() {
        this.gameTitles = new ArrayList<>();
        this.notifyCriteria = new ArrayList<>();
    }

    public ArrayList<String> getGameTitles() {
        return gameTitles;
    }

    public ArrayList<String> getNotifyCriteria() {
        return notifyCriteria;
    }

    public void addGame(String gameName, String criteria) {
        gameTitles.add(gameName);
        notifyCriteria.add(criteria);
    }

    public void removeGame(String gameName) {
        int index = gameTitles.indexOf(gameName);
        if (index >= 0) {
            gameTitles.remove(index);
            notifyCriteria.remove(index);
        }
    }
}
