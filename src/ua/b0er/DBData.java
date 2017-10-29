package ua.b0er;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.LinkedList;

public class DBData extends AbstractTableModel {

    private LinkedList<Column> columns;
    private LinkedList<Row> data;

    private Row row;
    private Table currentTable;

    public void gettingAllTable() {
        try {
            clearTable();
            columns.addAll(currentTable.getColumns());

            for (int i = 0; i < currentTable.getRowCount(); i++) {
                row = currentTable.getNextRow();
                data.add(row);
            }
            fireTableStructureChanged();

        } catch (IOException e) {
        }
    }

    public void clearTable(){
        data = new LinkedList<>();
        columns = new LinkedList<>();
        System.gc();
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columns.get(columnIndex).getName());
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column).getName();
    }

    @Override
    public void fireTableStructureChanged() {
        super.fireTableStructureChanged();
    }

}