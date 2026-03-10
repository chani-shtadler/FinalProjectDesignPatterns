import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting In-Memory Database Engine ---\n");

        // 1. Singleton & Facade: קבלת המופע היחיד של בסיס הנתונים
        // אנחנו לא עושים new, אלא מבקשים את המופע היחיד שקיים
        Database db = Database.getInstance();

        // 2. בניית מבנה הטבלה (Schema & Columns)
        // אנחנו מגדירים עמודת שם (טקסט) ועמודת גיל (מספר)
        Column nameCol = new Column("Name", DataType.STRING);
        Column ageCol = new Column("Age", DataType.INT);
        Schema userSchema = new Schema(Arrays.asList(nameCol, ageCol));

        // 3. יצירת טבלה והוספתה ל-Database (ה-Facade מנהל את זה)
        Table usersTable = new Table("Users", userSchema);
        db.addTable(usersTable);

        // 4. Observer Pattern: הוספת "צופה" שידווח על כל שינוי בטבלה
        // מעכשיו, כל addRow יגרום להדפסה אוטומטית למסך דרך ה-Logger
        usersTable.addObserver(new Logger());

        // 5. הוספת נתונים (עם ולידציה אוטומטית בתוך ה-addRow)
        System.out.println("Inserting rows...");
        Row r1 = new Row();
        r1.set("Name", "Alice");
        r1.set("Age", 25);
        usersTable.addRow(r1);

        Row r2 = new Row();
        r2.set("Name", "Bob");
        r2.set("Age", 17);
        usersTable.addRow(r2);

        // 6. Builder Pattern & Strategy: ביצוע שאילתה עם Fluent API
        // אנחנו בונים שאילתה בצורה קריאה: "חפש בטבלת משתמשים איפה שהגיל גדול מ-18"
        System.out.println("\nRunning Query: Age > 18");
        List<Row> results = db.query("Users")
                .where(new BiggerCondition("Age", 18))
                .execute();

        // הדפסת תוצאות השאילתה
        for (Row row : results) {
            System.out.println("Found match: " + row.getValues());
        }

        // 7. Prototype Pattern: שכפול טבלה שלמה
        // אנחנו יוצרים עותק מושלם ועצמאי של הטבלה הקיימת
        System.out.println("\nCloning Users table...");
        Table clonedUsers = usersTable.copy();
        System.out.println("Cloned table name: " + clonedUsers.getName());

        // 8. Decorator Pattern: יצירת טבלה מוגנת (ReadOnly)
        // אנחנו עוטפים את הטבלה המקורית כדי לחסום אפשרות כתיבה
        System.out.println("\nCreating a Read-Only version of the table...");
        Table protectedTable = new ReadOnlyTableDecorator(usersTable);

        try {
            // ניסיון להוסיף שורה לטבלה המוגנת - אמור לזרוק שגיאה!
            Row r3 = new Row();
            r3.set("Name", "Charlie");
            r3.set("Age", 30);
            protectedTable.addRow(r3);
        } catch (UnsupportedOperationException e) {
            System.out.println("Success! Decorator blocked the insert: " + e.getMessage());
        }

        // 9. Composite Pattern: שימוש ב-OR (גדול מ-20 או שם שווה ל-Bob)
        System.out.println("\nRunning Composite Query: (Age > 20 OR Name = 'Bob')");
        OrCondition complexCondition = new OrCondition();
        complexCondition.add(new BiggerCondition("Age", 20));
        complexCondition.add(new EqualsCondition("Name", "Bob"));

        List<Row> complexResults = db.query("Users")
                .where(complexCondition)
                .execute();

        for (Row row : complexResults) {
            System.out.println("Complex match found: " + row.getValues());
        }

        System.out.println("\n--- Demo Completed Successfully ---");
    }
}