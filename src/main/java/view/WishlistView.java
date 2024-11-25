package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import interface_adapter.wishlist.*;
import use_case.wishlist.WishlistInteractor;

public class WishlistView {
    private JFrame frame;
    private JPanel listPanel;
    private WishlistViewModel viewModel;
    private WishlistController controller;

    public WishlistView(WishlistViewModel viewModel, WishlistController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        setupUI();
        updateView();
    }

    private void setupUI() {
        frame = new JFrame("Wishlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Title Label
        JLabel titleLabel = new JLabel("My Wishlist", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel for Game List
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // Vertical layout

        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add Game Panel
        JPanel addPanel = new JPanel();
        JTextField gameInput = new JTextField(20);
        JButton addButton = new JButton("Add Game");

        addPanel.add(gameInput);
        addPanel.add(addButton);
        frame.add(addPanel, BorderLayout.SOUTH);

        // Add Game Button Listener
        addButton.addActionListener(e -> {
            String gameTitle = gameInput.getText().trim();
            if (!gameTitle.isEmpty()) {
                controller.addGame(gameTitle);
                updateView();
                gameInput.setText(""); // Clear input field
            }
        });

        frame.setVisible(true);
    }

    public void updateView() {
        listPanel.removeAll();

        // Retrieve game data from ViewModel
        java.util.ArrayList<String> gameTitles = viewModel.getGameTitles();

        for (String title : gameTitles) {
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            gamePanel.setPreferredSize(new Dimension(350, 50));

            JLabel titleLabel = new JLabel(title);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                controller.removeGame(title);
                updateView(); // Refresh UI
            });

            gamePanel.add(titleLabel, BorderLayout.WEST);
            gamePanel.add(removeButton, BorderLayout.EAST);

            gamePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(frame, "Clicked on: " + title);
                }
            });

            listPanel.add(gamePanel);
        }

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        // Example setup
        WishlistState state = new WishlistState();
        WishlistViewModel viewModel = new WishlistViewModel(state);
        WishlistPresenter presenter = new WishlistPresenter(viewModel);
        WishlistInteractor interactor = new WishlistInteractor(state, presenter); // No error now
        WishlistController controller = new WishlistController(interactor);

        // Launch UI
        new WishlistView(viewModel, controller);
    }
}
