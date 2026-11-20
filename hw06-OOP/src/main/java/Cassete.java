import java.util.List;

public interface Cassete {
    List<Cell> cells();
    Cell cell(Bill bill);
    int totalBalance();
    void deposit(Bill bill,int count);
}
