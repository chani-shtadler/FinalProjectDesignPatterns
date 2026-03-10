public interface ITableObserver {
    void onTableChanged(String tableName, String action, Row row);
}
