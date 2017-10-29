package ua.b0er.buttonListener;

import ua.b0er.DataBaseGUI;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;

public class SelectCell implements ListSelectionListener {
    DataBaseGUI adminFrame;
    String tableName = "";

    public SelectCell(DataBaseGUI frame) {
        adminFrame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            tableName = (String)adminFrame.jtable_table.getValueAt(
                            adminFrame.jtable_table.getSelectedRow(),
                            adminFrame.jtable_table.getSelectedColumn());

            try {
                adminFrame.dataTable.setCurrentTable(adminFrame.currentDatabase.getTable(tableName));
                adminFrame.dataTable.gettingAllTable();

                adminFrame.jtable_data.setModel(adminFrame.dataTable);
                adminFrame.jtable_data_scroll.repaint();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
