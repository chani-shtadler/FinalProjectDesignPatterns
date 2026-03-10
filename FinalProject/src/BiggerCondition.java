public class BiggerCondition implements ICondition {
    private String columnName;
    private Comparable value; // אנחנו משתמשים ב-Comparable כי הוא מאפשר השוואה

    public BiggerCondition(String columnName, Comparable value) {
        this.columnName = columnName;
        this.value = value;
    }

    @Override
    public boolean evaluate(Row row) {
        Object rowValue = row.get(columnName);

        if (rowValue == null) return false;


        if (rowValue instanceof Comparable) {
            Comparable compRowValue = (Comparable) rowValue;
            return compRowValue.compareTo(value) > 0;
        }

        return false;
    }
}

