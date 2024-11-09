package view;
import javax.swing.*;
import java.awt.*;

public class GameSearchView {
    private JFrame frame;
    private JTextField titleField;
    private JTextField upperPriceField;
    private JTextField lowerPriceField;
    private JTextField metacriticField;
    private JCheckBox onSaleCheckBox;
    private JComboBox<String> sortByComboBox;
    private JToggleButton descToggleButton;
    private JButton searchByTitleButton;
    private JButton searchByFiltersButton;

    public GameSearchView() {
        frame = new JFrame("UofTrackMyGames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        Color backgroundColor = new Color(45, 45, 45);
        Color textColor = new Color(230, 230, 230);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setForeground(textColor);
        titleField = new JTextField();

        JLabel upperPriceLabel = new JLabel("Upper Price");
        upperPriceLabel.setForeground(textColor);
        upperPriceField = new JTextField();

        JLabel lowerPriceLabel = new JLabel("Lower Price");
        lowerPriceLabel.setForeground(textColor);
        lowerPriceField = new JTextField();

        JLabel metacriticLabel = new JLabel("Metacritic");
        metacriticLabel.setForeground(textColor);
        metacriticField = new JTextField();

        JLabel onSaleLabel = new JLabel("On Sale");
        onSaleLabel.setForeground(textColor);
        onSaleCheckBox = new JCheckBox();

        JLabel sortByLabel = new JLabel("Sort By");
        sortByLabel.setForeground(textColor);
        String[] sortByOptions = {"DealRating", "Title", "Savings", "Price", "Metacritic"};
        sortByComboBox = new JComboBox<>(sortByOptions);

        descToggleButton = new JToggleButton("▲");
        descToggleButton.addActionListener(e -> {
            if (descToggleButton.isSelected()) {
                descToggleButton.setText("▼");
            } else {
                descToggleButton.setText("▲");
            }
        });

        searchByTitleButton = new JButton("Search by Title");
        searchByFiltersButton = new JButton("Search by Filters");

        JPanel sortPanel = new JPanel();
        sortPanel.setBackground(backgroundColor);
        sortPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sortPanel.add(sortByComboBox);
        sortPanel.add(descToggleButton);

        Dimension textFieldSize = titleField.getPreferredSize();
        sortPanel.setPreferredSize(new Dimension(textFieldSize.width, textFieldSize.height));

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(upperPriceLabel);
        panel.add(upperPriceField);
        panel.add(lowerPriceLabel);
        panel.add(lowerPriceField);
        panel.add(metacriticLabel);
        panel.add(metacriticField);
        panel.add(onSaleLabel);
        panel.add(onSaleCheckBox);
        panel.add(sortByLabel);
        panel.add(sortPanel);
        panel.add(searchByTitleButton);
        panel.add(searchByFiltersButton);

        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    // Getters for UI components
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

    public JButton getSearchByTitleButton() {
        return searchByTitleButton;
    }

    public JButton getSearchByFiltersButton() {
        return searchByFiltersButton;
    }

    public void displayResponse(String response) {
        // Logic to display the response
        System.out.println("Response: " + response);
    }
}
