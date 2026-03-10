import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Row implements IPrototype<Row> {
    // מפה ששומרת: שם עמודה -> ערך (מופיע בדף 1)
    private Map<String, Object> values;

    public Row() {
        this.values = new HashMap<>();
    }

    // קונסטרקטור פרטי לצורך השכפול (Deep Copy)
    private Row(Map<String, Object> values) {
        this.values = new HashMap<>(values); // יוצר עותק חדש של המפה
    }

    public void set(String columnName, Object value) {
        values.put(columnName, value);
    }

    public Object get(String columnName) {
        return values.get(columnName);
    }

    public List<Object> getValues() {
        return new ArrayList<>(values.values());
    }

    @Override
    public Row copy() {
        // מימוש ה-Prototype: מחזירים מופע חדש עם אותם נתונים
        return new Row(this.values);
    }
}
