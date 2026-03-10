import java.util.List;

public class Schema {
    private List<Column> columns;

    public Schema(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public boolean validate(Row row) {
        for (Column col : columns) {
            Object value = row.get(col.getName());
            // אין יותר if-else! כל עמודה שואלת את ה-DataType שלה אם הערך תקין
            if (!col.getDataType().isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
