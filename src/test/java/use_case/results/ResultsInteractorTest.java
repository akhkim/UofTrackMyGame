//package use_case.results;
//
//import entity.Game;
//import entity.GameFactory;
//
//import org.junit.jupiter.api.Test;
//import org.json.JSONObject;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ResultsInteractorTest {
//
//    @Test
//    void successTest() {
//        JSONObject jsonObject = new JSONObject("{\"gameID\":\"1\",\"title\":\"Game Title\",\"salePrice\":\"$1.00\",\"normalPrice\":\"$2.00\",\"isOnSale\":\"true\",\"savings\":\"$1.00\",\"metacriticScore\":\"100\",\"steamRatingText\":\"Very Positive\",\"steamRatingPercent\":\"90%\",\"steamRatingCount\":\"1000\",\"dealRating\":\"10\",\"thumb\":\"http://www.example.com/image.jpg\",\"storeName\":\"Steam\"}");
//        GameFactory gameFactory = new GameFactory();
//        Game game = gameFactory.create(jsonObject);
//        ResultsInputData inputData = new ResultsInputData(game);
//
//        ResultsOutputBoundary successPresenter = new ResultsOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ResultsOutputData user) {
//                assertEquals("1", user.getGame().getGameID());
//                assertEquals("Game Title", user.getGame().getTitle());
//                assertEquals("$1.00", user.getGame().getSalePrice());
//                assertEquals("$2.00", user.getGame().getNormalPrice());
//                assertEquals("true", user.getGame().getIsOnSale());
//                assertEquals("$1.00", user.getGame().getSavings());
//                assertEquals("100", user.getGame().getMetacriticScore());
//                assertEquals("Very Positive", user.getGame().getSteamRatingText());
//                assertEquals("90%", user.getGame().getSteamRatingPercent());
//                assertEquals("1000", user.getGame().getSteamRatingCount());
//                assertEquals("10", user.getGame().getDealRating());
//                assertEquals("http://www.example.com/image.jpg", user.getGame().getThumb());
//                assertEquals("Steam", user.getGame().getStoreName());
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        ResultsInputBoundary interactor = new ResultsInteractor(successPresenter);
//        interactor.execute(inputData);
//    }
//
//    @Test
//    void failureInformationUnavaiableTest() {
//        JSONObject jsonObject = new JSONObject("{}");
//        GameFactory gameFactory = new GameFactory();
//        Game game = gameFactory.create(jsonObject);
//        ResultsInputData inputData = new ResultsInputData(game);
//
//        ResultsOutputBoundary failurePresenter = new ResultsOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ResultsOutputData user) {
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("Missing required game parameters", error);
//            }
//        };
//
//        ResultsInputBoundary interactor = new ResultsInteractor(failurePresenter);
//        interactor.execute(inputData);
//    }
//}