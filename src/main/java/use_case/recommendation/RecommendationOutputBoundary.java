
package use_case.recommendation;

/**
 * The RecommendationOutputBoundary interface defines the contract for preparing
 * the output data for a recommendation operation.
 * Implementations of this interface handle how the recommendation results are
 * presented or passed back to the caller.
 */
public interface RecommendationOutputBoundary {

    /**
     * Prepares and presents the recommendation results in the success view.
     * This method is called when the recommendation logic has successfully
     * found a set of recommended games.
     *
     * @param outputData The data containing the recommended games to be presented.
     */
    void prepareSuccessView(RecommendationOutputData outputData);
}