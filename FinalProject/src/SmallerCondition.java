public class SmallerCondition implements ICondition {
    private String columnName;
    private Comparable value; // אנחנו משתמשים ב-Comparable כי הוא מאפשר השוואה

    public SmallerCondition(String columnName, Comparable value) {
        this.columnName = columnName;
        this.value = value;
    }

    @Override
    public boolean evaluate(Row row) {
        Object rowValue = row.get(columnName);

        if (rowValue == null) return false;

        // כאן קורה ה"קסם" של ההמרה:
        // אנחנו בודקים אם הערך שמהשורה הוא אכן בר-השוואה (כמו Integer)
        if (rowValue instanceof Comparable) {
            Comparable compRowValue = (Comparable) rowValue;
            return compRowValue.compareTo(value) < 0;
        }

        return false;
    }
}
