package com.loottable.views.components;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.loottable.helpers.UiUtilities;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

public class ItemPanel extends JPanel {
    private static final long serialVersionUID = 8426321039456174778L;
    BufferedImage image;
    String itemName;
    String quantity;
    String rarity;
    String price;

    public ItemPanel(BufferedImage image, String itemName, String quantity, String rarity, String price) {
        this.image = image;
        this.itemName = itemName;
        this.quantity = quantity;
        this.rarity = rarity;
        this.price = price;
        setBorder(new EmptyBorder(0, 0, 5, 0));
        setLayout(new BorderLayout());

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(ColorScheme.DARK_GRAY_COLOR);
        container.setBorder(UiUtilities.ITEM_BORDER);

        JPanel paddingContainer = new JPanel(new BorderLayout());
        int padding = 2;
        paddingContainer.setBorder(new EmptyBorder(padding, padding, padding, padding));

        JPanel leftPanel = constructLeftSide();
        JPanel rightPanel = constructRightSide();

        paddingContainer.add(leftPanel, BorderLayout.WEST);
        paddingContainer.add(rightPanel, BorderLayout.EAST);

        container.add(paddingContainer);

        add(container);
    }

    private JPanel constructItemImage() {
        JPanel container = new JPanel(new BorderLayout());
        container.setSize(30, container.getHeight());

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setSize(35, imageLabel.getWidth());

        container.add(imageLabel, BorderLayout.WEST);
        container.setSize(35, container.getHeight());
        return container;
    }

    /**
     * Constructs left side of item panel Item Name Rarity
     */
    private JPanel constructLeftSide() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        JPanel itemImage = constructItemImage();

        JPanel leftSidePanel = new JPanel(new GridLayout(2, 1));
        leftSidePanel.setBorder(new EmptyBorder(2, 2, 2, 2));

        JLabel itemNameLabel = new JLabel(itemName);
        itemNameLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        itemNameLabel.setFont(FontManager.getRunescapeBoldFont());
        itemNameLabel.setHorizontalAlignment(JLabel.LEFT);
        itemNameLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel rarityLabel = new JLabel(rarity);
        rarityLabel.setFont(FontManager.getRunescapeSmallFont());
        rarityLabel.setHorizontalAlignment(JLabel.LEFT);
        rarityLabel.setVerticalAlignment(JLabel.CENTER);

        leftSidePanel.add(itemNameLabel);
        leftSidePanel.add(rarityLabel);

        container.add(itemImage);
        container.add(leftSidePanel);
        return container;
    }

    /**
     * Constructs right side of item panel quantity Price
     */
    private JPanel constructRightSide() {
        JPanel rightSidePanel = new JPanel(new GridLayout(2, 1));
        // rightSidePanel.setBorder(new EmptyBorder(2, 2, 2, 2));

        JLabel quantityLabel = quantity.equals("N/A") ? new JLabel(quantity) : new JLabel("x" + quantity);
        quantityLabel.setFont(FontManager.getRunescapeSmallFont());
        quantityLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        quantityLabel.setHorizontalAlignment(JLabel.RIGHT);
        quantityLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel priceLabel = price.equals("0") ? new JLabel() : new JLabel(price + "gp");
        priceLabel.setFont(FontManager.getRunescapeSmallFont());
        priceLabel.setForeground(ColorScheme.GRAND_EXCHANGE_ALCH);
        priceLabel.setVerticalAlignment(JLabel.CENTER);

        rightSidePanel.add(quantityLabel);
        rightSidePanel.add(priceLabel);

        return rightSidePanel;
    }
}
