
package app;

import javax.swing.JFrame;

/**
 * Entry point of UofTrackMyGame.
 */
public class Main {

    /**
     * Entry point for the UofTrackMyGame application.
     * <p>
     * Initializes the {@link AppBuilder}, adds views and use cases to the application,
     * and builds the main window which is then displayed.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
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