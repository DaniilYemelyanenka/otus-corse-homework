import java.util.List;
import java.util.Map;

public interface DispenceStrategy {
    Map<Bill,Integer> plan(int amount, List<Cell> cells);
}
