
package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsPresenter;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchViewModel;
import interface_adapter.wishlist.WishlistController;
import interface_adapter.wishlist.WishlistPresenter;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.game.GameDataAccessInterface;
import use_case.game.GameInputBoundary;
import use_case.game.GameInteractor;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInteractor;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInteractor;
import use_case.results.ResultsOutputBoundary;
import use_case.search.GameSearchInputBoundary;
import use_case.search.GameSearchInteractor;
import use_case.search.GameSearchOutputBoundary;
import use_case.wishlist.WishlistInputBoundary;
import use_case.wishlist.WishlistInteractor;
import use_case.wishlist.WishlistOutputBoundary;
import view.GameSearchView;
import view.GameView;
import view.ResultsView;
import view.ViewManager;
import view.WishlistView;

/**
 * The AppBuilder class constructs and configures the various views, use cases, and controllers
 * for the UofTrackMyGame application. It adds views to the card panel and wires them with
 * their respective models, presenters, and controllers.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

    // DO NOT DELETE viewManager – it listens to property change events fired by viewManagerModel
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
   
    private GameSearchView gameSearchView;
    private GameSearchViewModel gameSearchViewModel;
    private ResultsView resultsView;
    private ResultsViewModel resultsViewModel;
    private GameView gameView;
    private GameViewModel gameViewModel;
    private WishlistView wishlistView;
    private WishlistViewModel wishlistViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the GameSearchView to the application and sets up its model.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addGameSearchView() {
        gameSearchViewModel = new GameSearchViewModel();
        gameSearchView = new GameSearchView(gameSearchViewModel);
        cardPanel.add(gameSearchView, gameSearchView.getViewName());
        return this;
    }

    /**
     * Adds the ResultsView to the application and sets up its model.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addResultsView() {
        resultsViewModel = new ResultsViewModel();
        resultsView = new ResultsView(resultsViewModel);
        cardPanel.add(resultsView, resultsView.getViewName());
        return this;
    }

    /**
     * Adds the GameView to the application and sets up its model.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addGameView() {
        gameViewModel = new GameViewModel();
        gameView = new GameView(gameViewModel);
        cardPanel.add(gameView, gameView.getViewName());
        return this;
    }

    /**
     * Adds the wishlist view.
     */
    public AppBuilder addWishlistView() {
        wishlistViewModel = new WishlistViewModel();
        wishlistView = new WishlistView(wishlistViewModel);
        cardPanel.add(wishlistView, wishlistView.getViewName());
        return this;
    }

    /**
     * Sets up the GameSearch use case with its input boundary, presenter, and controller.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addGameSearchUseCase() {
        GameSearchOutputBoundary gameSearchPresenter = new GameSearchPresenter(
                resultsViewModel,
                viewManagerModel
        );
        GameSearchInputBoundary gameSearchInteractor = new GameSearchInteractor(
                new DataAccess(),
                gameSearchPresenter
        );
        GameSearchController controller = new GameSearchController(gameSearchView, gameSearchInteractor);
        gameSearchView.setGameSearchController(controller);
        return this;
    }

    /**
     * Sets up the Results use case with its input boundary, presenter, and controller.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addResultsUseCase() {
        final ResultsOutputBoundary resultsPresenter = new ResultsPresenter(resultsViewModel, gameViewModel,
                viewManagerModel);
        final ResultsInputBoundary resultsInteractor = new ResultsInteractor(resultsPresenter);
        final ResultsController resultsController = new ResultsController(resultsInteractor);
        resultsView.setResultsController(resultsController);
        return this;
    }

    /**
     * Adds the WishlistView to the application and sets up its model, state, and controller.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addHomeUseCase(){
        HomeOutputBoundary homePresenter = new HomePresenter(viewManagerModel);
        HomeInputBoundary homeInteractor = new HomeInteractor(homePresenter);
        HomeController homeController = new HomeController(homeInteractor);
        resultsView.setHomeController(homeController);
        wishlistView.setHomeController(homeController);
        return this;
    }

    /**
     * Sets up the Game use case with its input boundary, interactor, and controller.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addGameUseCase() {
        GameDataAccessInterface gameDataAccess = new DataAccess();
        GameInputBoundary gameInteractor = new GameInteractor(gameDataAccess);
        GameController gameController = new GameController(gameInteractor);
        gameView.setGameController(gameController);
        return this;
    }

    /**
     * Sets up the Recommendation use case with its input boundary, presenter, and controller.
     *
     * @return this AppBuilder instance for method chaining
     */
    public AppBuilder addRecommendationUseCase() {
        RecommendationOutputBoundary recommendationPresenter = new RecommendationPresenter(resultsViewModel,
                viewManagerModel);
        RecommendationInputBoundary recommendationInteractor = new RecommendationInteractor(
                recommendationPresenter,
                new DataAccess()
        );
        RecommendationController controller = new RecommendationController(recommendationInteractor);
        gameView.setRecommendationController(controller);

        return this;
    }

    public AppBuilder addWishlistUseCase(){
        WishlistOutputBoundary wishlistPresenter = new WishlistPresenter(wishlistViewModel, viewManagerModel);
        WishlistInputBoundary wishlistInteractor = new WishlistInteractor(new DataAccess(), wishlistPresenter);
        WishlistController controller = new WishlistController(wishlistInteractor);
        wishlistView.setWishlistController(controller);
        gameSearchView.setWishlistController(controller);

        return this;
    }

    /**
     * Builds the main application JFrame and sets up the view management.
     *
     * @return the JFrame for the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("UofTrackMyGame");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();

        return application;
    }
}