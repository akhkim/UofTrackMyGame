package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.home.HomeController;
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

        Color buttonColor = new Color(242, 243, 245);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(40, 40, 40));  // Set background color for listPanel

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 600));  // Adjust the size as needed

        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(buttonColor);
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
                gamePanel.setPreferredSize(new Dimension(listPanel.getWidth(), 70));  // Set fixed height for each game panel
                gamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));    // Ensure the width is flexible and height is constant
                gamePanel.setBackground(new Color(40, 40, 40));  // Set background color for gamePanel

                JLabel titleLabel = new JLabel(title);
                titleLabel.setForeground(Color.WHITE);

                JButton removeButton = new JButton("Remove");
                removeButton.setForeground(Color.GRAY);
                removeButton.addActionListener(e -> {
                    wishlistController.removeGame(title);  // Remove the game from wishlist
                });

                gamePanel.add(titleLabel, BorderLayout.WEST);
                gamePanel.add(removeButton, BorderLayout.EAST);

                listPanel.add(gamePanel);
            }

            listPanel.revalidate();
            listPanel.repaint();
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
