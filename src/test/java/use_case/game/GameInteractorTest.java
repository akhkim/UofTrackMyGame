package use_case.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameInteractorTest {

    private TestGameDataAccessInterface testDataAccessInterface;
    private GameInteractor gameInteractor;

    @BeforeEach
    void setUp() {
        // Create a simple test implementation of GameDataAccessInterface
        testDataAccessInterface = new TestGameDataAccessInterface();

        // Instantiate the GameInteractor with the test implementation
        gameInteractor = new GameInteractor(testDataAccessInterface);
    }

    @Test
    void testAddToWishlist() {
        // Arrange
        String gameID = "123";
        String title = "Test Game";
        String salePrice = "10.99";
        String normalPrice = "19.99";
        String isOnSale = "true";
        String savings = "45%";
        String metacriticScore = "85";
        String steamRatingText = "Very Positive";
        String steamRatingPercent = "89";
        String steamRatingCount = "1000";
        String dealRating = "9.5";
        String thumb = "http://example.com/image.jpg";
        String storeName = "Steam";

        // Act
        gameInteractor.addToWishlist(gameID, title, salePrice, normalPrice, isOnSale, savings,
                metacriticScore, steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, storeName);

        // Assert
        assertTrue(testDataAccessInterface.isWishlistSaved);
        assertEquals(gameID, testDataAccessInterface.savedGameID);
        assertEquals(title, testDataAccessInterface.savedTitle);
        assertEquals(salePrice, testDataAccessInterface.savedSalePrice);
    }

    @Test
    void testSetPriceAlert() {
        // Arrange
        String email = "test@example.com";
        String gameID = "123";
        String price = "15.99";

        // Act
        gameInteractor.setPriceAlert(email, gameID, price);

        // Assert
        assertTrue(testDataAccessInterface.isPriceAlertSet);
        assertEquals(email, testDataAccessInterface.savedEmail);
        assertEquals(gameID, testDataAccessInterface.savedGameID);
        assertEquals(price, testDataAccessInterface.savedPrice);
    }

    // Simple test implementation of GameDataAccessInterface
    private static class TestGameDataAccessInterface implements GameDataAccessInterface {
        boolean isWishlistSaved = false;
        boolean isPriceAlertSet = false;

        String savedGameID;
        String savedTitle;
        String savedSalePrice;
        String savedEmail;
        String savedPrice;

        @Override
        public void saveToWishlist(String gameID, String title, String salePrice, String normalPrice, String isOnSale,
                                   String savings, String metacriticScore, String steamRatingText, String steamRatingPercent,
                                   String steamRatingCount, String dealRating, String thumb, String storeName) {
            isWishlistSaved = true;
            savedGameID = gameID;
            savedTitle = title;
            savedSalePrice = salePrice;
        }

        @Override
        public void setPriceAlert(String email, String gameID, String price) {
            isPriceAlertSet = true;
            savedEmail = email;
            savedGameID = gameID;
            savedPrice = price;
        }
    }
}
