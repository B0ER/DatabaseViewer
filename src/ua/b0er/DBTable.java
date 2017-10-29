package ua.b0er;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class DBTable extends AbstractTableModel {
    ArrayList<String> tablesName = new ArrayList<>();
    Set<String> st;

    @Override
    public String getColumnName(int column) {
        return "";
    }

    @Override
    public int getRowCount() {
        return tablesName.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tablesName.get(rowIndex);
    }

    public void gettingTable(String path){
        File fl = new File(path);
        tablesName.clear();
        try {
            Database db = DatabaseBuilder.open(fl);
            st = db.getTableNames();
            db.close();
            for (String c : st) {
                tablesName.add(c);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
