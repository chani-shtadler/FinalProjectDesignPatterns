public class Column {
    private String name;
    private DataType dataType;

    public Column(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    // Getters
    public String getName() { return name; }
    public DataType getDataType() { return dataType; }
}
