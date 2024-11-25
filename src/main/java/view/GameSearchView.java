package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import interface_adapter.search.GameSearchController;
import entity.GameSearchState;
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
    private final JButton feelingLuckyButton;

    public GameSearchView(GameSearchViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        Color backgroundColor = new Color(45, 45, 45);
        Color textColor = new Color(230, 230, 230);

        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(8, 2, 10, 10));

        final JLabel titleLabel = new JLabel("Title");
        titleLabel.setForeground(textColor);
        final JLabel upperPriceLabel = new JLabel("Upper Price");
        upperPriceLabel.setForeground(textColor);
        final JLabel lowerPriceLabel = new JLabel("Lower Price");
        lowerPriceLabel.setForeground(textColor);
        final JLabel metacriticLabel = new JLabel("Metacritic");
        metacriticLabel.setForeground(textColor);
        final JLabel onSaleLabel = new JLabel("On Sale");
        onSaleLabel.setForeground(textColor);
        final JLabel sortByLabel = new JLabel("Sort By");
        sortByLabel.setForeground(textColor);

        String[] sortByOptions = {"DealRating", "Title", "Savings", "Price", "Metacritic"};
        sortByComboBox = new JComboBox<>(sortByOptions);

        searchByTitleButton = new JButton("Search by Title");
        searchByFiltersButton = new JButton("Search by Filters");
        feelingLuckyButton = new JButton("Feeling Lucky!");

        JPanel sortPanel = new JPanel();
        sortPanel.setBackground(backgroundColor);
        sortPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sortPanel.add(sortByComboBox);
        sortPanel.add(descToggleButton);

        // Add components in the desired order
        this.add(titleLabel);
        this.add(titleField);
        this.add(new JLabel());  // Empty space
        this.add(searchByTitleButton);
        this.add(upperPriceLabel);
        this.add(upperPriceField);
        this.add(lowerPriceLabel);
        this.add(lowerPriceField);
        this.add(metacriticLabel);
        this.add(metacriticField);
        this.add(onSaleLabel);
        this.add(onSaleCheckBox);
        this.add(sortByLabel);
        this.add(sortPanel);
        this.add(searchByFiltersButton);
        this.add(feelingLuckyButton);

        // Add listeners
        searchByTitleButton.addActionListener(this);
        searchByFiltersButton.addActionListener(this);
        feelingLuckyButton.addActionListener(this);

        addTitleListener();
        addUpperPriceListener();
        addLowerPriceListener();
        addMetacriticListener();
        addOnSaleListener();
        addSortByListener();
        addDescListener();
    }

    private void addTitleListener() {
        titleField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                viewModel.updateTitle(titleField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addUpperPriceListener() {
        upperPriceField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                viewModel.updateUpperPrice(upperPriceField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addLowerPriceListener() {
        lowerPriceField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                viewModel.updateLowerPrice(lowerPriceField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addMetacriticListener() {
        metacriticField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                viewModel.updateMetacritic(metacriticField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addOnSaleListener() {
        onSaleCheckBox.addActionListener(e -> viewModel.updateOnSale(onSaleCheckBox.isSelected()));
    }

    private void addSortByListener() {
        sortByComboBox.addActionListener(e -> viewModel.updateSortBy((String) sortByComboBox.getSelectedItem()));
    }

    private void addDescListener() {
        descToggleButton.addActionListener(e -> {
            if (descToggleButton.isSelected()) {
                descToggleButton.setText("▼");
            } else {
                descToggleButton.setText("▲");
            }
            viewModel.updateDesc(descToggleButton.isSelected());
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchByTitleButton) {
            controller.searchByTitle();
        } else if (evt.getSource() == searchByFiltersButton) {
            controller.searchByFilters();
        } else if (evt.getSource() == feelingLuckyButton) {
            controller.feelingLucky();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle updates from the ViewModel
        GameSearchState state = (GameSearchState) evt.getNewValue();
        // Update the view based on the new state if needed
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

    public void displayResponse(String response) {
        // You'll need to decide how to display the response
        // For example, you might want to add a JTextArea to show results:
        // responseArea.setText(response);
        System.out.println(response);
    }
}
