import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSearchUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UofTrackMyGames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        Color backgroundColor = new Color(45, 45, 45);
        Color textColor = new Color(230, 230, 230);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setForeground(textColor);
        JTextField titleField = new JTextField();

        JLabel upperPriceLabel = new JLabel("Upper Price");
        upperPriceLabel.setForeground(textColor);
        JTextField upperPriceField = new JTextField();

        JLabel lowerPriceLabel = new JLabel("Lower Price");
        lowerPriceLabel.setForeground(textColor);
        JTextField lowerPriceField = new JTextField();

        JLabel metacriticLabel = new JLabel("Metacritic");
        metacriticLabel.setForeground(textColor);
        JTextField metacriticField = new JTextField();

        JLabel onSaleLabel = new JLabel("On Sale");
        onSaleLabel.setForeground(textColor);
        JCheckBox onSaleCheckBox = new JCheckBox();

        JLabel sortByLabel = new JLabel("Sort By");
        sortByLabel.setForeground(textColor);
        String[] sortByOptions = {"DealRating", "Title", "Savings", "Price", "Metacritic"};
        JComboBox<String> sortByComboBox = new JComboBox<>(sortByOptions);

        JToggleButton descToggleButton = new JToggleButton("▲");
        descToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (descToggleButton.isSelected()) {
                    descToggleButton.setText("▼");
                } else {
                    descToggleButton.setText("▲");
                }
            }
        });

        JButton searchByTitleButton = new JButton("Search by Title");
        JButton searchByFiltersButton = new JButton("Search by Filters");

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
        frame.setVisible(true);

        searchByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String response = CheapSharkDealLookup.searchByTitle(title);
                System.out.println("Response by Title: " + response);
            }
        });

        searchByFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String upperPrice = upperPriceField.getText();
                String lowerPrice = lowerPriceField.getText();
                String metacritic = metacriticField.getText();
                String onSale = onSaleCheckBox.isSelected() ? "1" : "0";
                String sortBy = (String) sortByComboBox.getSelectedItem();
                String desc = descToggleButton.isSelected() ? "1" : "0";

                String response = CheapSharkDealLookup.searchByFilters(upperPrice, lowerPrice, metacritic, onSale, sortBy, desc);
                System.out.println("Response by Filters: " + response);
            }
        });
    }
} 