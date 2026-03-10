import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private Table table;
    private List<ICondition> conditions = new ArrayList<>();


    public QueryBuilder(Table table) {
        this.table = table;
    }


    public QueryBuilder where(ICondition condition) {
        this.conditions.add(condition);
        return this;
    }


    public List<Row> execute() {
        List<Row> results = new ArrayList<>();


        for (Row row : table.getRows()) {
            if (matchesAllConditions(row)) {
                // שימוש ב-Prototype Pattern:
                // מחזירים עותק של השורה כדי שהמשתמש לא יוכל לשנות את הנתונים המקוריים בטעות
                results.add(row.copy());
            }
        }
        return results;
    }


    private boolean matchesAllConditions(Row row) {
        for (ICondition condition : conditions) {
            // שימוש ב-Strategy Pattern:
            // אנחנו מפעילים את ה-evaluate של כל תנאי (Equals, GreaterThan וכו')
            if (!condition.evaluate(row)) {
                return false; // מספיק שתנאי אחד לא מתקיים כדי לפסול את השורה
            }
        }
        return true; // השורה עברה את כל התנאים
    }
}
