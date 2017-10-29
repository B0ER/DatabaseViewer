package ua.b0er.buttonListener;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import ua.b0er.DataBaseGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Open implements ActionListener {
    private JFileChooser filepath = new JFileChooser("E:/");
    private DataBaseGUI adminFrame;

    public Open(DataBaseGUI frame) {
        adminFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filepath.addChoosableFileFilter(new FileNameExtensionFilter("Access mdb", "mdb"));
        filepath.setFileFilter(new FileNameExtensionFilter("Access accdb", "accdb"));

        int ref = filepath.showDialog(adminFrame.getContentPane(), "Open");
        if (ref == JFileChooser.APPROVE_OPTION) {
            adminFrame.path = filepath.getSelectedFile().getPath();
            try {

                adminFrame.currentDatabase = DatabaseBuilder.open(filepath.getSelectedFile());

                adminFrame.dataTable.clearTable();
                adminFrame.jtable_table.clearSelection();
                adminFrame.tableTable.gettingTable(adminFrame.path);
                adminFrame.jtable_table_scroll.repaint();
                adminFrame.jtable_data_scroll.repaint();
                adminFrame.setVisibleAll(true);

                adminFrame.textField_path.setText(adminFrame.path);
                adminFrame.pack();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            adminFrame.paintAgain();
        }
    }
}
