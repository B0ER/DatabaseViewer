package ua.b0er;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Set;

import com.healthmarketscience.jackcess.*;
import ua.b0er.buttonListener.SelectCell;
import ua.b0er.buttonListener.TableAdd;

public class DataBaseGUI extends JFrame {

    public JLabel jlabel_data = new JLabel("Data");
    public JLabel jlabel_table = new JLabel("Table");
    public JTextField textField_path = new JTextField("Path", 30);
    public JTable jtable_data;
    public JTable jtable_table;
    public JScrollPane jtable_data_scroll;
    public JScrollPane jtable_table_scroll;

    public Database currentDatabase;

    public String path;
    public DBData dataTable;
    public DBTable tableTable;

    private JLabel jlabel_path = new JLabel("Enter path:");
    private ListSelectionModel cellSelectionModelTable;
    private Container containerMenu;

    private IconAndButton jbuttonOpen = new IconAndButton(
            "res/openedfolder.png",
            "Open Database",
            new ua.b0er.buttonListener.Open(this));

    private IconAndButton createButton = new IconAndButton(
            "res/create.png",
            "Create file",
            new ua.b0er.buttonListener.Create(this));

    private IconAndButton tableAddButton = new IconAndButton(
            "res/tableAdd.png",
            "Add table",
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tableAdd.setVisible(true);
                }
            });

    private TableAdd tableAdd = new TableAdd(this);

    public DataBaseGUI() {
        super("DataBaseView");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        containerMenu = new Container();
        containerMenu.setLayout(new GridBagLayout());
        containerMenu.add(jbuttonOpen);
        containerMenu.add(createButton);
        containerMenu.add(tableAddButton);

        add(containerMenu, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7, 10, 0, 0), 0, 0));

        dataTable = new DBData();
        tableTable = new DBTable();
        jtable_data = new JTable();
        jtable_table = new JTable();

        jtable_data.setCellSelectionEnabled(false);
        jtable_table.setCellSelectionEnabled(false);
        jtable_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jtable_data_scroll = new JScrollPane(jtable_data);
        jtable_data_scroll.setPreferredSize(new Dimension(500, 300));

        jtable_table_scroll = new JScrollPane(jtable_table);
        jtable_table_scroll.setPreferredSize(new Dimension(100, 300));


        add(jlabel_path, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7, 10, 0, 10), 0, 0));

        add(textField_path, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 10, 0, 5), 0, 0));

        add(jbuttonOpen, new GridBagConstraints(2, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        add(jlabel_data, new GridBagConstraints(1, 4, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(3, 10, 0, 10), 0, 0));

        add(jlabel_table, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(3, 10, 0, 10), 0, 0));

        add(jtable_table_scroll, new GridBagConstraints(0, 5, 1, 3, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(jtable_data_scroll, new GridBagConstraints(1, 5, 1, 3, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        jlabel_data.setVisible(false);
        jtable_data_scroll.setVisible(false);

        jlabel_table.setVisible(false);
        jtable_table_scroll.setVisible(false);



        cellSelectionModelTable = jtable_table.getSelectionModel();
        cellSelectionModelTable.addListSelectionListener(new SelectCell(this));

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void paintAgain(){
        jtable_data.removeAll();
        dataTable.fireTableStructureChanged();
        jtable_data.repaint();
        jtable_data_scroll.repaint();

        jtable_table.removeAll();
        tableTable.gettingTable(path);
        jtable_table.setModel(tableTable);
        jtable_table_scroll.repaint();

        textField_path.setText(path);
        pack();
    }

    public void setVisibleAll(boolean flag){
        jlabel_data.setVisible(flag);
        jtable_data_scroll.setVisible(flag);

        jlabel_table.setVisible(flag);
        jtable_table_scroll.setVisible(flag);
    }
}
