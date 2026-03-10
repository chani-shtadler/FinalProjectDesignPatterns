//singleton

import java.util.HashMap;
import java.util.Map;


public class Database {

    // 1. המופע היחיד של המחלקה (static)
    private static Database instance;

    // שדות מההוראות (דף 1)
    private String name;
    private Map<String, Table> tables;

    // 2. קונסטרקטור פרטי - אף אחד לא יכול לעשות new Database() מבחוץ
    private Database() {
        this.name = "MyInMemoryDB"; // אפשר לשנות לשם שרוצים
        this.tables = new HashMap<>();
    }

    // 3. הפונקציה הסטטית שנותנת את המופע היחיד
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // --- כאן יבואו הפונקציות של ה-Facade (החזית של המערכת) ---

    public QueryBuilder query(String tableName) {
        // 1. מחפשים את הטבלה במפה לפי השם שלה
        Table table = tables.get(tableName);

        // 2. בדיקת הגנה: אם הטבלה לא קיימת, זורקים שגיאה
        if (table == null) {
            throw new IllegalArgumentException("Table '" + tableName + "' not found in database.");
        }

        // 3. מחזירים אובייקט חדש של QueryBuilder שיעבוד על הטבלה הזו
        // זה השלב שבו ה-Database "מוליד" את ה-Builder
        return new QueryBuilder(table);
    }

    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    // גטר לשם של ה-DB
    public String getName() {
        return name;
    }
}
