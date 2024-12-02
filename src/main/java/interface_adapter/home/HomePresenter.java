package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;

public class HomePresenter implements HomeOutputBoundary {
    ViewManagerModel viewManagerModel;

    public HomePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();
    }
}
