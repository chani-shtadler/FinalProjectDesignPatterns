import java.util.ArrayList;
import java.util.List;

public class AndCondition implements ICondition {
    private List<ICondition> conditions = new ArrayList<>();

    public void add(ICondition condition) {
        this.conditions.add(condition);
    }

    @Override
    public boolean evaluate(Row row) {
        // תנאי AND - כולם חייבים להיות אמת
        for (ICondition condition : conditions) {
            if (!condition.evaluate(row)) {
                return false;
            }
        }
        return true;
    }
}
