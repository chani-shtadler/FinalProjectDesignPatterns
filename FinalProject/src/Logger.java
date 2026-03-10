public class Logger implements ITableObserver {
    @Override
    public void onTableChanged(String tableName, String action, Row row) {
        System.out.println("[NOTIFICATION] Table '" + tableName +
                "' performed action: " + action +
                ". Data: " + row.getValues());
    }
}