package ua.b0er.buttonListener;

import ua.b0er.DataBaseGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRow {
    DataBaseGUI adminFrame;

    AddRow(DataBaseGUI frame){
        adminFrame = frame;
    }


    public class addRow implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //int countcolumn = adminFrame.dataTable.columns.size();
        }
    }
}
