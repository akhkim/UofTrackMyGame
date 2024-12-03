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

        testDataAccess.addGame(new Game("1", "Test Game 1", "2.99", "9.99", "100", "90", "10", "Positive", "5", "1000", "4.5", "test_image.jpg", "test_url"));
        testDataAccess.addGame(new Game("2", "Test Game 2", "5.99", "19.99", "200", "80", "15", "Mixed", "10", "2000", "4.0", "test_image2.jpg", "test_url2"));
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
        ArrayList<Game> games = interactor.getWishlistGames();

        assertEquals(2, games.size());
        assertEquals("Test Game 1", games.get(0).getTitle());
        assertEquals("Test Game 2", games.get(1).getTitle());
    }

    private static class TestWishlistDataAccess implements WishlistDataAccessInterface {
        private final ArrayList<Game> games = new ArrayList<>();

        @Override
        public void removeGameFromWishlist(String gameID) {
            games.removeIf(game -> game.getGameID().equals(gameID));
        }

        @Override
        public ArrayList<Game> loadWishlist() {
            return new ArrayList<>(games);
        }

        @Override
        public void saveWishlist(ArrayList<Game> games) {
            this.games.clear();
            this.games.addAll(games);
        }

        public void addGame(Game game) {
            games.add(game);
        }
    }

    private static class TestWishlistPresenter implements WishlistOutputBoundary {
        private String lastMessage;

        @Override
        public WishlistOutputData presentSuccess(String message) {
            this.lastMessage = message;
            return new WishlistOutputData(true, message);
        }

        @Override
        public WishlistOutputData presentError(String message) {
            this.lastMessage = message;
            return new WishlistOutputData(false, message);
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
