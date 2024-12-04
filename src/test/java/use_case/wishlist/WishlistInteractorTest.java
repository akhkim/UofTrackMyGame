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

        testDataAccess.addGame(new Game("1", "Test Game 1", "2.99", "9.99",
                "100", "90", "10", "Positive", "5",
                "1000", "4.5", "test_image.jpg", "test_url"));
        testDataAccess.addGame(new Game("2", "Test Game 2", "5.99", "19.99",
                "200", "80", "15", "Mixed", "10",
                "2000", "4.0", "test_image2.jpg", "test_url2"));
    }

    @Test
    void testRemoveGameFromWishlist() {
        interactor.removeGameFromWishlist(new WishlistInputData("1"));

        ArrayList<Game> games = testDataAccess.loadWishlist();
        assertEquals(1, games.size());
        assertEquals("Test Game 2", games.get(0).getTitle());
    }

    @Test
    void testGetWishlistGames() {
        interactor.getWishlistGames();
        ArrayList<Game> games = testPresenter.getLastPresentedGames();

        assertEquals(2, games.size());
        assertEquals("Test Game 1", games.get(0).getTitle());
        assertEquals("Test Game 2", games.get(1).getTitle());

        // Verify presenter received the correct data
        assertEquals(2, testPresenter.getLastPresentedGames().size());
        assertEquals("Test Game 1", testPresenter.getLastPresentedGames().get(0).getTitle());
    }

    @Test
    void testGetWishlistGamesError() {
        // Simulate error in data access
        testDataAccess.setSimulateError(true);

        interactor.getWishlistGames();

        // Verify presenter handles error
        assertEquals("Error loading wishlist: Simulated data access error", testPresenter.getLastError());
    }

    @Test
    void testEmptyWishlist() {
        testDataAccess.clearWishlist();
        interactor.getWishlistGames();

        // Verify wishlist is empty
        ArrayList<Game> games = testPresenter.getLastPresentedGames();
        assertEquals(0, games.size());
    }

    private static class TestWishlistDataAccess implements WishlistDataAccessInterface {
        private final ArrayList<Game> games = new ArrayList<>();
        private boolean simulateError = false;

        @Override
        public void removeGameFromWishlist(String gameID) {
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
