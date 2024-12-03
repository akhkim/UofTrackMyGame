
package use_case.home;

/**
 * The HomeOutputBoundary interface defines the contract for preparing the success view
 * in response to the execution of the Home use case.
 * It is intended to be implemented by classes responsible for updating the view
 * after the use case logic has been executed.
 */
public interface HomeOutputBoundary {

    /**
     * Prepares the success view to be presented to the user.
     * This method is called when the Home use case logic completes successfully.
     */
    void prepareSuccessView();
}
