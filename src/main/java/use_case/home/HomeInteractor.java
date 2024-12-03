
package use_case.home;

/**
 * The HomeInteractor class is responsible for executing the logic of the Home use case.
 * It implements the HomeInputBoundary interface and interacts with the HomeOutputBoundary
 * to trigger the appropriate response or update the view.
 */
public class HomeInteractor implements HomeInputBoundary {

    private final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeOutputBoundary homePresenter) {
        this.homePresenter = homePresenter;
    }

    @Override
    public void execute() {
        homePresenter.prepareSuccessView();
    }
}
