package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.GameSearchViewModel;
import use_case.home.HomeOutputBoundary;

public class HomePresenter implements HomeOutputBoundary {
    ViewManagerModel viewManagerModel;


    @Override
    public void prepareSuccessView() {
        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();
    }
}
