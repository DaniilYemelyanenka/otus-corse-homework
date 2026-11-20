import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinDispenceStrategy implements DispenceStrategy {

    @Override
    public Map<Bill, Integer> plan(int amount, List<Cell> cells) {

        if (amount <= 0) {
            throw new AtmException("Amount must be greater than zero: " + amount);
        }

        List<Cell> notes = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getCount() > 0) {
                notes.add(cell);
            }
        }
        if (notes.isEmpty()) {
            throw new AtmException("It is not possible to dispense the amount: " + amount
                    + " - there are no banknotes in the cassette");
        }

        int n = notes.size();
        int[] value = new int[n];
        int[] stock = new int[n];
        int step = 0;
        for (int i = 0; i < n; i++) {
            value[i] = notes.get(i).getBill().value();
            stock[i] = notes.get(i).getCount();
            step = gcd(step, value[i]);
        }
        if (amount % step != 0) {
            throw new AtmException("It is not possible to dispense the amount: " + amount
                    + " using the available banknotes.");
        }

        int target = amount / step;
        final int INF = Integer.MAX_VALUE / 2;

        int[][] best = new int[n + 1][target + 1];
        int[][] take = new int[n + 1][target + 1];

        for (int a = 1; a <= target; a++) {
            best[0][a] = INF;
        }

        for (int i = 1; i <= n; i++) {
            int v = value[i - 1] / step;
            int count = stock[i - 1];

            for (int a = 0; a <= target; a++) {
                best[i][a] = best[i - 1][a];
                take[i][a] = 0;

                for (int k = 1; k <= count && k * v <= a; k++) {
                    int previous = best[i - 1][a - k * v];
                    if (previous + k < best[i][a]) {
                        best[i][a] = previous + k;
                        take[i][a] = k;
                    }
                }
            }
        }

        if (best[n][target] >= INF) {
            throw new AtmException("It is not possible to dispense the amount: " + amount
                    + " using the available banknotes.");
        }

        Map<Bill, Integer> plan = new HashMap<>();
        int rest = target;
        for (int i = n; i >= 1; i--) {
            int k = take[i][rest];
            if (k > 0) {
                plan.put(notes.get(i - 1).getBill(), k);
                rest -= k * (value[i - 1] / step);
            }
        }
        return plan;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
