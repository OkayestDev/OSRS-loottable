package com.loottable.views;

import java.util.function.Consumer;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import com.loottable.views.components.LootTablePanel;
import com.loottable.views.components.NoDropTable;

import org.json.simple.JSONArray;

import com.loottable.views.components.Header;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;

public class LootTablePluginPanel extends PluginPanel {
    private static final long serialVersionUID = 5758361368464139958L;

    private ActionListener onSearchButtonPressed;
    private Consumer<String> onSearchBarTextChanged;
    private ItemManager itemManager;

    public LootTablePluginPanel(
        ItemManager itemManager, 
        ActionListener onSearchButtonPressed,
        Consumer<String> onSearchBarTextChanged
    ) {
        this.itemManager = itemManager;
        this.onSearchButtonPressed = onSearchButtonPressed;
        this.onSearchBarTextChanged = onSearchBarTextChanged;
        Header header = new Header("", onSearchButtonPressed, onSearchBarTextChanged);
        add(header);
    }

    public void rebuildPanel(String monsterName, JSONArray dropTable) {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            Header header = new Header(monsterName, onSearchButtonPressed, onSearchBarTextChanged);
            add(header);

            if (dropTable == null || dropTable.size() == 0) {
                if (monsterName != "") {
                    NoDropTable noDropTable = new NoDropTable();
                    add(noDropTable);
                }
            } else {
                LootTablePanel lootTablePanel = new LootTablePanel(dropTable, this.itemManager);
                add(lootTablePanel, BorderLayout.WEST);
            }

            this.revalidate();
        });
    }
}