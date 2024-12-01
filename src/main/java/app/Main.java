package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import entity.Game;
import entity.GameFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameViewModel;
import view.GameView;
import view.ViewManager;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("gameID", "title","salePrice","normalPrice",
                "isOnSale", "savings","metacriticScore","steamRatingText",
                "steamRatingPercent","steamRatingCount","dealRating",
                "thumb");
        new GameView(game.getTitle(), game.getSalePrice(), game.getMetacriticScore(), game.getDealRating());
    }
}
