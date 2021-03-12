package com.loottable.views.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoDropTable extends JPanel {
    public NoDropTable() {
        JLabel noDropTableLabel = new JLabel("No results to show.");
        add(noDropTableLabel);
    }
}
