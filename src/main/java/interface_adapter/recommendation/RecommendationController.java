package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;

public class RecommendationController {
    private RecommendationInputBoundary recommendationInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    public void execute() {
        recommendationInteractor.execute();
    }
}
