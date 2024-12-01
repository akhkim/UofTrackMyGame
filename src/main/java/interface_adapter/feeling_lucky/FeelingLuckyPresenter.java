package interface_adapter.feeling_lucky;

import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameViewModel;
import use_case.feeling_lucky.FeelingLuckyOutputBoundary;

public class FeelingLuckyPresenter implements FeelingLuckyOutputBoundary {
    private GameViewModel gameViewModel;
    private ViewManagerModel viewManagerModel;


    @Override
    public void prepareSuccessView(Game game) {

    }

}
