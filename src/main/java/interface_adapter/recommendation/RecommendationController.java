
package interface_adapter.recommendation;

import entity.Game;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;

/**
 * {@code RecommendationController} is responsible for receiving a game object from the view layer and
 * passing it to the use case layer for processing recommendations. It acts as a bridge between the
 * view layer and the use case layer, invoking the necessary use case methods and providing the required input data.
 */
public class RecommendationController {
    private RecommendationInputBoundary recommendationInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    /**
     * Executes the recommendation use case by passing a {@code Game} object to the interactor.
     * The controller converts the {@code Game} object into a {@code RecommendationInputData}
     * and calls the {@code execute} method on the interactor.
     *
     * @param game the {@code Game} object for which recommendations are being generated
     */
    public void execute(Game game) {
        RecommendationInputData inputData = new RecommendationInputData(game);
        recommendationInteractor.execute(inputData);
    }
}
