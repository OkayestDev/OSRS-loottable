package com.loottable.views.components;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.util.Iterator;
import java.util.function.Consumer;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.loottable.helpers.UiUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.runelite.client.ui.ColorScheme;

public class MultipleItems extends JPanel {
    private Consumer<Integer> selectItemConsumer;

    public MultipleItems(JSONArray items, Consumer<Integer> selectItemConsumer) {
        this.selectItemConsumer = selectItemConsumer;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        constructHeader();
        
        Iterator<JSONObject> itemsIter = items.iterator();
        while (itemsIter.hasNext()) {
            JSONObject item = itemsIter.next();
            constructItemPanel(item);
        }
    }

    public void constructHeader() {
        JLabel multipleResultsLabel = new JLabel("Multiple results found:");
        JPanel container = new JPanel();
        container.setBorder(new EmptyBorder(0, 0, 10, 0));
        container.add(multipleResultsLabel);
        add(container);
    }

    public void constructItemPanel(JSONObject item) {
        JPanel container = new JPanel();
        container.setBorder(UiUtilities.ITEM_BORDER);
        container.setLayout(new GridLayout(2, 1));
        String wikiName = (String) item.get("wiki_name");

        JTextArea wikiNameTextArea = new JTextArea(2, 1);
        wikiNameTextArea.setLineWrap(true);
        wikiNameTextArea.setOpaque(false);
        wikiNameTextArea.setEditable(false);
        wikiNameTextArea.setFocusable(false);
        wikiNameTextArea.setBackground(ColorScheme.DARK_GRAY_COLOR);
        wikiNameTextArea.setText(wikiName);
        wikiNameTextArea.setBorder(new EmptyBorder(2, 2, 2, 2));
        container.add(wikiNameTextArea);

        JPanel buttonContainer  = new JPanel();
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent event) -> {
            Integer monsterId = Integer.parseInt((String) item.get("id"));
            selectItemConsumer.accept(monsterId);
        });
        buttonContainer.add(selectButton);
        container.add(buttonContainer);

        JPanel spaceContainer = new JPanel();
        spaceContainer.setLayout(new BorderLayout());
        spaceContainer.setBorder(new EmptyBorder(0, 0, 5, 0));
        spaceContainer.add(container);

        add(spaceContainer);
    }
}
