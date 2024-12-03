package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import entity.Game;
import interface_adapter.home.HomeController;
import interface_adapter.results.ResultsState;
import interface_adapter.wishlist.*;

public class WishlistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "wishlist";
    private JPanel listPanel;
    private WishlistViewModel wishlistViewModel;
    private JButton backButton;
    private WishlistController wishlistController;
    private HomeController homeController;

    public WishlistView(WishlistViewModel wishlistViewModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.wishlistViewModel.addPropertyChangeListener(this);
        setupUI();
        // Initial view setup
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("My Wishlist", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for Game List
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // Vertical layout

        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateView(WishlistState wishlistState) {
        SwingUtilities.invokeLater(() -> {
            listPanel.removeAll();  // Clear the existing game list

            java.util.ArrayList<String> gameTitles = wishlistState.getGameTitles();

            // Create new UI components based on the updated list
            for (String title : gameTitles) {
                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BorderLayout());
                gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                gamePanel.setPreferredSize(new Dimension(350, 50));

                JLabel titleLabel = new JLabel(title);

                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    wishlistController.removeGame(title);  // Remove the game from wishlist
                });

                gamePanel.add(titleLabel, BorderLayout.WEST);
                gamePanel.add(removeButton, BorderLayout.EAST);

                gamePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null, "Clicked on: " + title);
                    }
                });

                listPanel.add(gamePanel);  // Add new game panel to the list
            }

            // After modifying the list, revalidate and repaint to ensure UI refresh
            revalidate();
            repaint();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof WishlistState) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            updateView(state);
        }
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource() == backButton) {
            homeController.execute();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setWishlistController(WishlistController wishlistController) {
        this.wishlistController = wishlistController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
