package com.loottable.views.components;

import java.awt.BorderLayout;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.runelite.client.ui.ColorScheme;

import javax.swing.BoxLayout;

public class LootTablePanel extends JPanel {
    private static final long serialVersionUID = 1132676497548426861L;

    public LootTablePanel(JSONArray dropTable) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setBackground(ColorScheme.DARK_GRAY_COLOR);

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBackground(ColorScheme.LIGHT_GRAY_COLOR);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        /** @todo get price and icon */
        for (Object drop : dropTable) {
            JSONObject jsonDrop = (JSONObject) drop;
            ItemPanel itemPanel = new ItemPanel("", jsonDrop.get("name").toString(),
                    jsonDrop.get("quantity").toString(), jsonDrop.get("rarity").toString(), "0");
            container.add(itemPanel);
        }

        add(container, BorderLayout.WEST);
    }
}