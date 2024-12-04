package use_case.wishlist;

import entity.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishlistInteractorTest {

    private WishlistInteractor interactor;
    private TestWishlistDataAccess testDataAccess;
    private TestWishlistPresenter testPresenter;

    @BeforeEach
    void setUp() {
        testDataAccess = new TestWishlistDataAccess();
        testPresenter = new TestWishlistPresenter();
        interactor = new WishlistInteractor(testDataAccess, testPresenter);

        // Add initial games to the test data access
        testDataAccess.addGame(new Game("1", "Test Game 1", "2.99", "9.99",
                "100", "90", "10", "Positive", "5",
                "1000", "4.5", "test_image.jpg", "test_url"));
        testDataAccess.addGame(new Game("2", "Test Game 2", "5.99", "19.99",
                "200", "80", "15", "Mixed", "10",
                "2000", "4.0", "test_image2.jpg", "test_url2"));
    }

    @Test
    void testRemoveGameFromWishlistSuccess() {
        interactor.removeGameFromWishlist(new WishlistInputData("1"));

        ArrayList<Game> games = testDataAccess.loadWishlist();
        assertEquals(1, games.size());
        assertEquals("Test Game 2", games.get(0).getTitle());
    }

    @Test
    void testRemoveNonExistentGame() {
        interactor.removeGameFromWishlist(new WishlistInputData("999")); // Non-existent game ID

        ArrayList<Game> games = testDataAccess.loadWishlist();
        assertEquals(2, games.size()); // No change in the wishlist
    }

    @Test
    void testRemoveGameFromWishlistError() {
        testDataAccess.setSimulateError(true);

        interactor.removeGameFromWishlist(new WishlistInputData("1"));

        assertEquals("Error loading wishlist: Simulated data access error", testPresenter.getLastError());
    }

    @Test
    void testGetWishlistGamesSuccess() {
        interactor.getWishlistGames();
        ArrayList<Game> games = testPresenter.getLastPresentedGames();

        assertEquals(2, games.size());
        assertEquals("Test Game 1", games.get(0).getTitle());
        assertEquals("Test Game 2", games.get(1).getTitle());
    }

    @Test
    void testGetWishlistGamesError() {
        testDataAccess.setSimulateError(true);

        interactor.getWishlistGames();

        assertEquals("Error loading wishlist: Simulated data access error", testPresenter.getLastError());
    }

    @Test
    void testEmptyWishlistHandling() {
        testDataAccess.clearWishlist();
        interactor.getWishlistGames();

        ArrayList<Game> games = testPresenter.getLastPresentedGames();
        assertEquals(0, games.size());
    }

    @Test
    void testConstructorWithEmptyWishlist() {
        testDataAccess.clearWishlist();
        WishlistInteractor emptyInteractor = new WishlistInteractor(testDataAccess, testPresenter);

        emptyInteractor.getWishlistGames();
        ArrayList<Game> games = testPresenter.getLastPresentedGames();
        assertEquals(0, games.size());
    }

    private static class TestWishlistDataAccess implements WishlistDataAccessInterface {
        private final ArrayList<Game> games = new ArrayList<>();
        private boolean simulateError = false;

        @Override
        public void removeGameFromWishlist(String gameID) {
            if (simulateError) {
                throw new RuntimeException("Simulated data access error");
            }
            games.removeIf(game -> game.getGameID().equals(gameID));
        }

        @Override
        public ArrayList<Game> loadWishlist() {
            if (simulateError) {
                throw new RuntimeException("Simulated data access error");
            }
            return new ArrayList<>(games);
        }

        public void addGame(Game game) {
            games.add(game);
        }

        public void clearWishlist() {
            games.clear();
        }

        public void setSimulateError(boolean simulateError) {
            this.simulateError = simulateError;
        }
    }

    private static class TestWishlistPresenter implements WishlistOutputBoundary {
        private ArrayList<Game> lastPresentedGames = new ArrayList<>();
        private String lastError;

        @Override
        public void presentSuccess(ArrayList<Game> games) {
            this.lastPresentedGames = new ArrayList<>(games);
        }

        @Override
        public WishlistOutputData presentError(String message) {
            this.lastError = message;
            return new WishlistOutputData(false, message);
        }

        public ArrayList<Game> getLastPresentedGames() {
            return lastPresentedGames;
        }

        public String getLastError() {
            return lastError;
        }
    }
}
