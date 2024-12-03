package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchViewModel;

public class GameSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "GameSearchView";
    private final GameSearchViewModel viewModel;

    private final JTextField titleField = new JTextField(15);
    private final JTextField upperPriceField = new JTextField(15);
    private final JTextField lowerPriceField = new JTextField(15);
    private final JTextField metacriticField = new JTextField(15);
    private final JCheckBox onSaleCheckBox = new JCheckBox();
    private final JComboBox<String> sortByComboBox;
    private final JToggleButton descToggleButton = new JToggleButton("▲");
    private GameSearchController controller;

    private final JButton searchByTitleButton;
    private final JButton searchByFiltersButton;
    private final JButton goToWishlistButton;

    public GameSearchView(GameSearchViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        Color backgroundColor = new Color(18, 18, 18);
        Color buttonColor = new Color(242, 243, 245);
        Color textColor = new Color(224, 224, 224);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout(10, 10));

        // 1. Panel for "Search by Title"
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(backgroundColor);
        titlePanel.setLayout(new GridLayout(2, 2, 5, 5));
        titlePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Search by Title", 0, 0, labelFont, textColor));

        JLabel titleLabel = new JLabel("Title", SwingConstants.RIGHT);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(labelFont);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);
        titlePanel.add(new JLabel()); // Spacer
        titlePanel.add(createStyledButton(searchByTitleButton = new JButton("Search by Title"), buttonFont));

        // 2. Panel for "Search by Filters"
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(backgroundColor);
        filterPanel.setLayout(new GridLayout(5, 2, 5, 5));
        filterPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Search by Filters", 0, 0, labelFont, textColor));

        JLabel upperPriceLabel = createStyledLabel("Upper Price (in $)", textColor, labelFont);
        JLabel lowerPriceLabel = createStyledLabel("Lower Price (in $)", textColor, labelFont);
        JLabel metacriticLabel = createStyledLabel("Metacritic (0-100)", textColor, labelFont);
        JLabel onSaleLabel = createStyledLabel("On Sale", textColor, labelFont);

        filterPanel.add(upperPriceLabel);
        filterPanel.add(upperPriceField);
        filterPanel.add(lowerPriceLabel);
        filterPanel.add(lowerPriceField);
        filterPanel.add(metacriticLabel);
        filterPanel.add(metacriticField);
        filterPanel.add(onSaleLabel);
        filterPanel.add(onSaleCheckBox);
        filterPanel.add(new JLabel()); // Spacer
        filterPanel.add(createStyledButton(searchByFiltersButton = new JButton("Search by Filters"), buttonFont));

        // 3. Panel for Sorting
        JPanel sortingPanel = new JPanel();
        sortingPanel.setBackground(backgroundColor);
        sortingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        sortingPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Sorting", 0, 0, labelFont, textColor));

        JLabel sortByLabel = createStyledLabel("Sort By", textColor, labelFont);
        String[] sortByOptions = {"DealRating", "Title", "Savings", "Price", "Metacritic"};
        sortByComboBox = new JComboBox<>(sortByOptions);
        sortByComboBox.setFont(buttonFont);

        sortingPanel.add(sortByLabel);
        sortingPanel.add(sortByComboBox);
        sortingPanel.add(descToggleButton);
        descToggleButton.setBackground(buttonColor);

        // Add panels to the main layout
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(backgroundColor);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(titlePanel);
        centerPanel.add(filterPanel);
        centerPanel.add(sortingPanel);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(createStyledButton(goToWishlistButton = new JButton("Go to Wishlist"), buttonFont), BorderLayout.SOUTH);

        // Add listeners
        searchByTitleButton.addActionListener(this);
        searchByFiltersButton.addActionListener(this);
        goToWishlistButton.addActionListener(this);

        descToggleButton.addActionListener(e -> {
            if (descToggleButton.isSelected()) {
                descToggleButton.setText("▼");
            } else {
                descToggleButton.setText("▲");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchByTitleButton) {
            controller.searchByTitle();
        } else if (evt.getSource() == searchByFiltersButton) {
            controller.searchByFilters();
        } else if (evt.getSource() == goToWishlistButton) {
            controller.goToWishlist();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle updates from the ViewModel
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(GameSearchController controller) {
        this.controller = controller;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getUpperPriceField() {
        return upperPriceField;
    }

    public JTextField getLowerPriceField() {
        return lowerPriceField;
    }

    public JTextField getMetacriticField() {
        return metacriticField;
    }

    public JCheckBox getOnSaleCheckBox() {
        return onSaleCheckBox;
    }

    public JComboBox<String> getSortByComboBox() {
        return sortByComboBox;
    }

    public JToggleButton getDescToggleButton() {
        return descToggleButton;
    }

    private JLabel createStyledLabel(String text, Color textColor, Font font) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setForeground(textColor);
        label.setFont(font);
        return label;
    }

    private JButton createStyledButton(JButton button, Font font) {
        button.setFont(font);
        Color buttonColor = new Color(242, 243, 245);
        button.setBackground(buttonColor);
        return button;
    }
}
