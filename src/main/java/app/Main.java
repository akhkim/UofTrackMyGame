package app;
import interface_adapter.search.GameSearchController;
import view.GameSearchView;

public class Main {
    public static void main(String[] args) {
        GameSearchController controller = AppBuilder.buildGameSearchApp();
        GameSearchView view = controller.getView();
        view.show();
    }
}
