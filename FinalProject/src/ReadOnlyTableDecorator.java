public class ReadOnlyTableDecorator extends TableDecorator {

    public ReadOnlyTableDecorator(Table table) {
        super(table);
    }

    @Override
    public void addRow(Row row) {

        System.out.println("!!! SECURITY ALERT: Attempt to write to a Read-Only Table: " + getName());
        throw new UnsupportedOperationException("Table '" + getName() + "' is protected and cannot be modified.");
    }
}
