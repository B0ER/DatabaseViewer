package ua.b0er.buttonListener;

import com.healthmarketscience.jackcess.*;
import ua.b0er.DataBaseGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

public class TableAdd {
    private JButton buttonOK;
    private JButton buttonAdd;
    private JButton buttonClose;
    private JTextField jTFNameTable;
    private JTextField jTFCountColumn;
    private JDialog jDialog;
    private DataBaseGUI adminFrame;
    private JLabel jtablename;

    private JTextField[] nameColumn;

    private Container cnTx = new Container();

    public TableAdd(DataBaseGUI frame) {
        adminFrame = frame;
        cnTx.setLayout(new BoxLayout(cnTx, BoxLayout.Y_AXIS));

        jDialog = new JDialog(adminFrame, "Add table");
        jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jTFNameTable = new JTextField(15);
        jTFCountColumn = new JTextField(2);
        buttonOK = new JButton("OK");
        buttonOK.addActionListener(new listenerOK());

        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new addTableAction());

        buttonClose = new JButton("Close");
        buttonClose.addActionListener(new closeAction());
        jtablename = new JLabel("Enter table name!");

        jDialog.setLayout(new GridBagLayout());

        jDialog.add(new JLabel("Count column"), new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));

        jDialog.add(jTFCountColumn, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        jDialog.add(buttonOK, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(3, 5, 5, 3), 0, 0));

        jDialog.add(buttonClose, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 5, 5), 0, 0));

        jDialog.add(cnTx, new GridBagConstraints(0, 5, 4, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 0, 5), 0, 0));

        jDialog.setModal(true);
        jDialog.setVisible(false);
        jDialog.setResizable(false);
        jDialog.pack();

    }

    public void setVisible(boolean a) {
        jDialog.setLocation(adminFrame.getLocation().x + (adminFrame.getSize().width / 2) - (jDialog.getSize().width / 2),
                adminFrame.getY());
        cnTx.setVisible(a);
        jDialog.setVisible(a);
    }

    private class closeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cnTx.setVisible(false);
            setVisible(false);
        }
    }

    private class addTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File fl = new File(adminFrame.path);
            try {
                TableBuilder tableBuilder = new TableBuilder(jTFNameTable.getText());

                for (int i = 0; i < nameColumn.length; i++) {
                    tableBuilder.addColumn(new ColumnBuilder(nameColumn[i].getText()).setSQLType(Types.CHAR).toColumn());
                }
                tableBuilder.toTable(adminFrame.currentDatabase);
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(jDialog, "Open or create file!", "Error!", JOptionPane.OK_OPTION);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
            adminFrame.tableTable.gettingTable(adminFrame.path);
            adminFrame.paintAgain();
        }
    }

    private class listenerOK implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameColumn = null;

            cnTx.removeAll();
            int countColumn;
            try {
                if(Integer.parseInt(jTFCountColumn.getText()) > 10) throw new NumberFormatException();
                countColumn = Integer.parseInt(jTFCountColumn.getText());
                nameColumn = new JTextField[countColumn];

                jDialog.add(new JLabel("Enter table name"), new GridBagConstraints(0, 2, 4, 1, 1, 1,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

                jDialog.add(jTFNameTable, new GridBagConstraints(0, 3, 4, 1, 1, 1,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

                jDialog.add(new JLabel("Enter column name"), new GridBagConstraints(0, 4, 4, 1, 1, 1,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

                for (int i = 0; i < nameColumn.length; i++) {
                    nameColumn[i] = new JTextField(10);
                    cnTx.add(nameColumn[i], new GridBagConstraints(0, i, 4, 1, 1, 1,
                            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
                }
                jDialog.add(buttonAdd, new GridBagConstraints(0, 6, 4, 1, 1, 1,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

                buttonAdd.setVisible(true);
                cnTx.repaint();
                jDialog.repaint();
                jDialog.pack();
            } catch (NumberFormatException el) {
                JOptionPane.showMessageDialog(jDialog, "Count is number and < 10!", "Error!", JOptionPane.OK_OPTION);
            }

        }
    }
}
