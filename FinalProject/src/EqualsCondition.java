public class EqualsCondition implements ICondition {
    private String columnName;
    private Object value;

    public EqualsCondition(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    @Override
    public boolean evaluate(Row row) {
        Object rowValue = row.get(columnName);
        return rowValue != null && rowValue.equals(value);
    }
}
