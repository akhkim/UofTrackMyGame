package use_case.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSearchInteractorTest {
    private GameSearchDataAccessInterface mockDataAccess;
    private GameSearchOutputBoundary mockPresenter;
    private GameSearchInteractor interactor;
    private String mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = "{\"mockResponse\": \"test\"}";
        
        // Create mock data access
        mockDataAccess = new GameSearchDataAccessInterface() {
            @Override
            public String searchByTitle(String title) {
                return mockResponse;
            }

            @Override
            public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, 
                                        String onSale, String sortBy, String desc) {
                return mockResponse;
            }
        };

        // Create mock presenter
        mockPresenter = new GameSearchOutputBoundary() {
            @Override
            public void presentSearchResults(String response) {
                assertEquals(mockResponse, response);
            }
        };

        interactor = new GameSearchInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void searchByTitle() {
        String testTitle = "Test Game";
        interactor.searchByTitle(testTitle);
    }

    @Test
    void searchByFilters() {
        String upperPrice = "60";
        String lowerPrice = "0";
        String metacritic = "75";
        boolean onSale = true;
        String sortBy = "Price";
        boolean desc = true;

        interactor.searchByFilters(upperPrice, lowerPrice, metacritic, onSale, sortBy, desc);
    }
}