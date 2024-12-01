package use_case.home;

public class HomeInteractor implements HomeInputBoundary {

    HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeOutputBoundary homePresenter) {
        this.homePresenter = homePresenter;
    }
    @Override
    public void execute() {
        homePresenter.prepareSuccessView();
    }
}
