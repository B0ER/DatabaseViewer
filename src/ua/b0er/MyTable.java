package ua.b0er;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.TableBuilder;
import com.healthmarketscience.jackcess.impl.DatabaseImpl;
import com.healthmarketscience.jackcess.impl.TableCreator;
import com.healthmarketscience.jackcess.impl.TableImpl;

import java.io.IOException;
import java.util.List;

public class MyTable extends TableCreator {

    public MyTable(DatabaseImpl database) {
        super(database);
    }

    @Override
    public List<ColumnBuilder> getColumns() {
        return super.getColumns();
    }

    @Override
    public TableImpl createTable(TableBuilder table) throws IOException {
        return super.createTable(table);
    }
}
