import java.util.*;

public class StandartCassete implements Cassete {

    private final Map<Bill,Cell> bills = new HashMap<>();

    StandartCassete(){
        for(Bill bill : Bill.values()){
            this.bills.put(bill,new Cell(bill));

        }
    }
    @Override
    public List<Cell> cells() {
        List<Cell> cells = new ArrayList<>(bills.values());
        cells.sort(Comparator.comparing((Cell cell) -> cell.getBill().value()).reversed());
      return cells;
    }

    @Override
    public int totalBalance() {
        int totalSum = 0;
        for(Cell cell : cells()){
            totalSum += cell.sum();
        }
        return totalSum;
    }

    @Override
    public void deposit(Bill bill, int count) {
        cell(bill).deposit(count);
    }

    @Override
    public Cell cell(Bill bill) {
        return bills.get(bill);
    }
}
