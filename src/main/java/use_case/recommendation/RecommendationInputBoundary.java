package use_case.recommendation;

/**
 * The RecommendationInputBoundary interface defines the contract for handling the input
 * data and executing the recommendation use case.
 * It is responsible for processing the recommendation logic based on the input provided.
 */
public interface RecommendationInputBoundary {

    /**
     * Executes the recommendation use case with the provided input data.
     *
     * @param inputData The input data containing information required for generating recommendations.
     */
    void execute(RecommendationInputData inputData);
}
