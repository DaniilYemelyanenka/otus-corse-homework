import java.util.HashMap;
import java.util.Map;

public class Cell {

    private final Bill bill;
    private int count;

    public Cell(Bill bill){
        if(bill == null) throw new IllegalArgumentException("bill can't be null");
        this.bill = bill;
        this.count = 0;
    }

    public int availableFor(int amount){
        return Math.min(count,amount/bill.value());
    }

    public void deposit(int count){
        if(count < 0)  throw new AtmException("Deposit cant be less than 0");
        this.count += count;
    }

    public void withdraw(int count){
        if(count < 0 || count > this.count) throw new AtmException("Withdraw can't  be less than 0 and more than count");
        this.count -= count;
    }

    public int sum(){
        return  this.count * bill.value();
    }

    public Bill getBill() {
        return bill;
    }

    public int getCount() {
        return count;
    }
}
