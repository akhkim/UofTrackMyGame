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
        Font secondaryFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout(10, 10));

        // 1. Panel for "Search by Title"
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(backgroundColor);
        titlePanel.setLayout(new BorderLayout(10, 10));
        titlePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Search by Title", 0, 0, labelFont, textColor));

        JPanel titleLeftPanel = new JPanel();
        titleLeftPanel.setBackground(backgroundColor);
        titleLeftPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JLabel titleLabel = new JLabel("Title", SwingConstants.RIGHT);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(secondaryFont);

        titleLeftPanel.add(titleLabel);
        titleLeftPanel.add(titleField);
        titleLeftPanel.add(new JLabel()); // Spacer
        titleLeftPanel.add(createStyledButton(searchByTitleButton = new JButton("Search by Title"), buttonFont));

        // Right-side Panel for the "Title" Section
        JPanel titleRightPanel = new JPanel();
        titleRightPanel.setBackground(backgroundColor);
        titleRightPanel.setPreferredSize(new Dimension(200, 0)); // Adjust width as needed

        titlePanel.add(titleLeftPanel, BorderLayout.CENTER);
        titlePanel.add(titleRightPanel, BorderLayout.EAST);

        // 2. Panel for "Search by Filters"
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(backgroundColor);
        filterPanel.setLayout(new BorderLayout(10, 10));
        filterPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Search by Filters", 0, 0, labelFont, textColor));

        JPanel filterLeftPanel = new JPanel();
        filterLeftPanel.setBackground(backgroundColor);
        filterLeftPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel upperPriceLabel = createStyledLabel("Upper Price (in $)", textColor, secondaryFont);
        JLabel lowerPriceLabel = createStyledLabel("Lower Price (in $)", textColor, secondaryFont);
        JLabel metacriticLabel = createStyledLabel("Metacritic (0-100)", textColor, secondaryFont);
        JLabel onSaleLabel = createStyledLabel("On Sale", textColor, secondaryFont);

        filterLeftPanel.add(upperPriceLabel);
        filterLeftPanel.add(upperPriceField);
        filterLeftPanel.add(lowerPriceLabel);
        filterLeftPanel.add(lowerPriceField);
        filterLeftPanel.add(metacriticLabel);
        filterLeftPanel.add(metacriticField);
        filterLeftPanel.add(onSaleLabel);
        filterLeftPanel.add(onSaleCheckBox);
        filterLeftPanel.add(new JLabel()); // Spacer
        filterLeftPanel.add(createStyledButton(searchByFiltersButton = new JButton("Search by Filters"), buttonFont));

        // Right-side Panel for the "Filters" Section
        JPanel filterRightPanel = new JPanel();
        filterRightPanel.setBackground(backgroundColor);
        filterRightPanel.setPreferredSize(new Dimension(200, 0)); // Adjust width as needed

        filterPanel.add(filterLeftPanel, BorderLayout.CENTER);
        filterPanel.add(filterRightPanel, BorderLayout.EAST);

        // 3. Panel for Sorting
        JPanel sortingPanel = new JPanel();
        sortingPanel.setBackground(backgroundColor);
        sortingPanel.setLayout(new GridBagLayout());
        sortingPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Sorting", 0, 0, labelFont, textColor));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add some padding around components
        gbc.anchor = GridBagConstraints.CENTER;  // Center all components

        // Sort By Label
        JLabel sortByLabel = createStyledLabel("Sort By", textColor, secondaryFont);
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 0;  // Row 0
        sortingPanel.add(sortByLabel, gbc);

        // Panel for the ComboBox and Toggle Button (to align horizontally)
        JPanel sortOptionsPanel = new JPanel();
        sortOptionsPanel.setBackground(backgroundColor);
        sortOptionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));  // Center align with spacing

        // Sort By ComboBox
        String[] sortByOptions = {"DealRating", "Title", "Savings", "Price", "Metacritic"};
        sortByComboBox = new JComboBox<>(sortByOptions);
        sortByComboBox.setFont(buttonFont);

        // Sort Direction Toggle Button
        descToggleButton.setFont(buttonFont);
        descToggleButton.setBackground(buttonColor);

        // Add ComboBox and Toggle Button to the sortOptionsPanel
        sortOptionsPanel.add(sortByComboBox);
        sortOptionsPanel.add(descToggleButton);

        // Add the sortOptionsPanel to the sortingPanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        sortingPanel.add(sortOptionsPanel, gbc);

        // Add sorting panel to the main layout
        this.add(sortingPanel, BorderLayout.CENTER);

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
