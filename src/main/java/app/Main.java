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
// The main application window.
        final JFrame application = new JFrame("Game Window Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are "observable", and will
        // be "observed" by the Views.
        final GameViewModel gameViewModel = new GameViewModel();

        // TODO:
        final GameDataAccessObject gameDataAccessObject = new GameDataAccessObject(new GameFactory());

        //TODO:
        final GameView gameView = GameUseCaseFactory.create(viewManagerModel, gameViewModel, gameDataAccessObject);
        views.add(gameView, gameView.getViewName());

        viewManagerModel.setState(gameView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
