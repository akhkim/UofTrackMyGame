package app;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addGameView()
                .addGameUseCase()
                .addGameSearchView()
                .addWishlistView()
                .addResultsView()
                .addGameSearchUseCase()
                .addResultsUseCase()
                .addHomeUseCase()
                .addRecommendationUseCase()
                .addWishlistUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}