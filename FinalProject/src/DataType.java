public enum DataType {
    INT(Integer.class),
    STRING(String.class),
    BOOL(Boolean.class);

    private final Class<?> typeClass;

    DataType(Class<?> typeClass) {
        this.typeClass = typeClass;
    }

    // הפונקציה הכוללת - היא בודקת אם האובייקט מתאים לסוג ה-Class שהגדרנו
    public boolean isValid(Object value) {
        if (value == null) return true; // או false, תלוי אם אתן מאפשרות Null
        return typeClass.isInstance(value);
    }
}
