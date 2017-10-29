package ua.b0er.buttonListener;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import ua.b0er.DataBaseGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Create implements ActionListener {
    JFileChooser filepath = new JFileChooser("E:/");

    DataBaseGUI adminFrame;

    public Create(DataBaseGUI frame) {
        adminFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filepath.addChoosableFileFilter(new FileNameExtensionFilter("Access mdb", "mdb"));
        filepath.setFileFilter(new FileNameExtensionFilter("Access accdb", "accdb"));
        int ref = filepath.showSaveDialog(adminFrame.getContentPane());
        if (ref == JFileChooser.APPROVE_OPTION) {
        String path = filepath.getSelectedFile().toString();
        if(path.endsWith(".mdb") || path.endsWith(".accdb"))
            adminFrame.path = path;
        else
            adminFrame.path = path + ".accdb";

            File fl = new File(adminFrame.path);
            try{
                Database db = DatabaseBuilder.create(Database.FileFormat.V2010, fl);

                db.close();
                int result = JOptionPane.showConfirmDialog(null, "Open file?", "Question",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    adminFrame.paintAgain();
                    adminFrame.setVisibleAll(true);
                    adminFrame.pack();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }

    }
}
