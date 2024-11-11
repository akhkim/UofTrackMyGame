package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import interface_adapter.wishlist.*;


public class WishlistView {
    private JFrame frame;
    private JPanel listPanel;
    private WishlistViewModel viewModel;

    public WishlistView(WishlistViewModel viewModel) {
        this.viewModel = viewModel;
        setupUI();
        updateView();
    }

    private void setupUI() {
        frame = new JFrame("Wishlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));  // Vertical layout for game panels

        JLabel titleLabel = new JLabel("My List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void updateView() {
        listPanel.removeAll();

        // Retrieve game data from the ViewModel
        java.util.ArrayList<String> gameTitles = viewModel.getGameTitles();
        java.util.ArrayList<String> notifyCriteria = viewModel.getNotifyCriteria();

        // Create a panel for each game in the wishlist
        for (int i = 0; i < gameTitles.size(); i++) {
            String title = gameTitles.get(i);
            String criteria = notifyCriteria.get(i);

            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            gamePanel.setPreferredSize(new Dimension(350, 50));

            JLabel titleLabel = new JLabel(title);
            JLabel criteriaLabel = new JLabel("Notify when below: " + criteria);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                viewModel.removeGame(title);
                updateView();  // Refresh view after removal
            });

            gamePanel.add(titleLabel, BorderLayout.WEST);
            gamePanel.add(criteriaLabel, BorderLayout.CENTER);
            gamePanel.add(removeButton, BorderLayout.EAST);

            // Click listener to open GameWindowView when clicking the game panel
            gamePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new GameView(title);  // Pass the game title to the new window
                }
            });

            listPanel.add(gamePanel);
        }

        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        WishlistState state = new WishlistState();  // Example state setup
        WishlistViewModel viewModel = new WishlistViewModel(state);
        new WishlistView(viewModel);
    }
}
