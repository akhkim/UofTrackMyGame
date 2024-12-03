package interface_adapter.home;

import use_case.home.HomeInputBoundary;

public class HomeController {
    HomeInputBoundary homeInteractor;

    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute() {
        homeInteractor.execute();
    }

}
