package app;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                    .addGamesView()
                                    .addGameSearchView()
                                    .addResultsView()
                                    .addGameSearchUseCase()
                                    .addResultsUseCase()
                                    .addHomeUseCase()
                                    .build();

        application.pack();
        application.setVisible(true);
    }
}