package com.loottable.views.components;

import java.awt.GridLayout;

import java.util.Iterator;
import java.util.function.Consumer;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        container.setBorder(new EmptyBorder(0, 0, 10, 0));
        container.setLayout(new GridLayout(2, 1));
        String name = (String) item.get("name");
        String wikiName = (String) item.get("wiki_name");

        JPanel namesContainer = new JPanel();
        namesContainer.setLayout(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel("Name: " + name);
        JLabel wikiNameLabel = new JLabel("Wiki Name: " + wikiName);
        namesContainer.add(nameLabel);
        namesContainer.add(wikiNameLabel);

        container.add(namesContainer);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent event) -> {
            selectItemConsumer.accept((Integer) item.get("id"));
        });
        container.add(selectButton);

        add(container);
    }
}
