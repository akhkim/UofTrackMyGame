package interface_adapter.wishlist;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WishlistViewModel {
    private WishlistState state;
    private JFrame frame;
    private JPanel panel;

    public WishlistViewModel(WishlistState state) {
        this.state = state;
        setupUI();
        updateView();
    }

    private void setupUI() {
        frame = new JFrame("Wishlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateView() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(state.getGameTitles().size() + 1, 2));

        listPanel.add(new JLabel("Game Titles"));
        listPanel.add(new JLabel("Notify when..."));

        ArrayList<String> gameTitles = state.getGameTitles();
        ArrayList<String> notifyCriteria = state.getNotifyCriteria();

        for (int i = 0; i < gameTitles.size(); i++) {
            listPanel.add(new JLabel(gameTitles.get(i)));
            listPanel.add(new JLabel(notifyCriteria.get(i)));
        }

        panel.add(listPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void addGame(String gameName, String notifyCriteria) {
        state.addGame(gameName, notifyCriteria);
        updateView();
    }

    public void removeGame(String gameName) {
        state.removeGame(gameName);
        updateView();
    }

    public ArrayList<String> getGameTitles() {
        return state.getGameTitles();
    }

    public ArrayList<String> getNotifyCriteria() {
        return state.getNotifyCriteria();
    }
}

