
package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;

/**
 * {@code HomePresenter} is responsible for preparing the success view by interacting with the
 * {@code ViewManagerModel}. It receives output from the use case layer and updates the view accordingly.
 * The presenter ensures that the appropriate view is displayed to the user.
 */
public class HomePresenter implements HomeOutputBoundary {
    private ViewManagerModel viewManagerModel;

    public HomePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Returns the {@code ViewManagerModel} associated with this presenter.
     *
     * @return the {@code ViewManagerModel} instance
     */
    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
