package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.GameDataAccessObject;
import entity.GameFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameViewModel;
import view.GameView;
import view.ViewManager;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                    .addGameSearchView()
                                    .addResultsView()
                                    .build();

        application.pack();
        application.setVisible(true);
    }
}
