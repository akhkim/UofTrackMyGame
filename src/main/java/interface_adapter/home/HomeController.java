
package interface_adapter.home;

import use_case.home.HomeInputBoundary;

/**
 * The {@code HomeController} class is responsible for handling interactions between the view and the business logic
 * related to the home functionality.
 * <p>
 * It acts as a controller in the MVC pattern and communicates with the {@code HomeInputBoundary} to execute the
 * business logic for the home-related actions.
 * </p>
 */
public class HomeController {
    private HomeInputBoundary homeInteractor;

    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    /**
     * Executes the home-related business logic by calling the {@code execute} method of the interactor.
     * <p>
     * This method delegates the responsibility to the {@code HomeInputBoundary} to perform the required operations.
     * </p>
     */
    public void execute() {
        homeInteractor.execute();
    }

    /**
     * Returns the {@code HomeInputBoundary} associated with this controller.
     *
     * @return the {@code HomeInputBoundary} instance
     */
    public HomeInputBoundary getHomeInteractor() {
        return homeInteractor;
    }

}
