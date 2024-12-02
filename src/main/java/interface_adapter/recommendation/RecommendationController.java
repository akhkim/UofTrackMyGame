package interface_adapter.recommendation;

import entity.Game;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;

public class RecommendationController {
    private RecommendationInputBoundary recommendationInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    public void execute(Game game) {
        RecommendationInputData inputData = new RecommendationInputData(game);
        recommendationInteractor.execute(inputData);
    }
}
