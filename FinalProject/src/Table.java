import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Table implements IPrototype<Table> {
    private String name;
    private Schema schema;
    private List<Row> rows;
    private List<ITableObserver> observers = new ArrayList<>();

    public Table(String name, Schema schema) {
        this.name = name;
        this.schema = schema;
        this.rows = new ArrayList<>();
    }

    public void addObserver(ITableObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObservers(String action, Row row) {
        for (ITableObserver obs : observers) {
            obs.onTableChanged(this.name, action, row);
        }
    }

    public void addRow(Row row) {
        if (this.schema.validate(row)) {
            this.rows.add(row);
            notifyObservers("INSERT", row);
        } else {
            throw new IllegalArgumentException("Invalid row data");
        }
    }

    public List<Row> getRows() {
        return this.rows;
    }
    @Override
    public Table copy() {
        // 1. יוצרים מופע חדש של טבלה עם אותה סכימה
        Table newTable = new Table(this.name + "_copy", this.schema);

        // 2. העתקה עמוקה של כל השורות (Deep Copy)
        for (Row row : this.rows) {
            // קוראים ל-copy של כל שורה בנפרד!
            newTable.addRow(row.copy());
        }

        return newTable;
    }

    // Getters
    public String getName() { return name; }
    public Schema getSchema() { return schema; }
}