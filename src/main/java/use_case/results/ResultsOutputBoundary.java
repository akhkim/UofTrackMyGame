
package use_case.results;

/**
 * The ResultsOutputBoundary interface defines the methods for presenting the results of a game search.
 * It serves as a contract for any presenter that handles the output of the results use case.
 *
 * This interface has methods to prepare the success and failure views based on the processed results.
 */
public interface ResultsOutputBoundary {

    /**
     * Prepares the success view with the provided results data.
     * This method is called when the game search results are valid and ready to be presented.
     *
     * @param data The data that will be presented, typically containing the game details.
     */
    void prepareSuccessView(ResultsOutputData data);

    /**
     * Prepares the failure view with the provided error message.
     * This method is called when the game search results contain missing or invalid data.
     *
     * @param error The error message that will be displayed in the failure view.
     */
    void prepareFailView(String error);
}