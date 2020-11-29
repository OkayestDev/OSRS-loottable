package com.loottable.views.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.loottable.helpers.DecimalToFraction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class LootTablePanel extends JPanel {
    private static final long serialVersionUID = 1132676497548426861L;

    public LootTablePanel(JSONArray dropTable, ItemManager itemManager) {
        ArrayList<String> dropRequirementList = new ArrayList<String>();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setBackground(ColorScheme.DARK_GRAY_COLOR);

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBackground(ColorScheme.LIGHT_GRAY_COLOR);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (Object drop : dropTable) {
            JSONObject jsonDrop = (JSONObject) drop;
            Long temp = (Long) jsonDrop.get("id");
            int itemId = temp.intValue();

            BufferedImage image = itemManager.getImage(itemId);
            int price = itemManager.getItemPrice(itemId);

            String rarityFraction = DecimalToFraction.convertDecimalToFraction((double) jsonDrop.get("rarity"));

            Object dropRequirement = jsonDrop.get("drop_requirements");
            String dropRequirementString = dropRequirement == null ? null : dropRequirement.toString();

            if (!dropRequirementList.contains(dropRequirementString) && dropRequirementString != null) {
                dropRequirementList.add(dropRequirementString);
                dropRequirementString = dropRequirementString.replace("-", " ");
                JLabel dropRequirementLabel = new JLabel(dropRequirementString);
                container.add(dropRequirementLabel);
            }

            ItemPanel itemPanel = new ItemPanel(image, jsonDrop.get("name").toString(),
                    jsonDrop.get("quantity").toString(), rarityFraction, String.valueOf(price));
            container.add(itemPanel);
        }

        add(container, BorderLayout.WEST);
    }
}