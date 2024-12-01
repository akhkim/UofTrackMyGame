package app;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                    .addGameSearchView()
                                    .addResultsView()
                                    .addWishlistView()
                                    .build();

        application.pack();
        application.setVisible(true);
    }
}