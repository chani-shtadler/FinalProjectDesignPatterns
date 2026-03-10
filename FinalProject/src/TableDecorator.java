import java.util.List;


public abstract class TableDecorator extends Table {

    protected Table decoratedTable;

    public TableDecorator(Table table) {
        super(table.getName(), table.getSchema());
        this.decoratedTable = table;
    }


    @Override
    public void addObserver(ITableObserver observer) {
        decoratedTable.addObserver(observer);
    }

    @Override
    public void addRow(Row row) {
        decoratedTable.addRow(row);
    }

    @Override
    public List<Row> getRows() {
        return decoratedTable.getRows();
    }

    @Override
    public String getName() {
        return decoratedTable.getName();
    }

    @Override
    public Schema getSchema() {
        return decoratedTable.getSchema();
    }

    @Override
    public Table copy() {
        return decoratedTable.copy();
    }
}
