import java.util.Map;

public class Atm {
    private final Map<Integer,Bill> converter;
    private Cassete cassete;
    private DispenceStrategy strategy;

    public Atm(){
        this.cassete = new StandartCassete();
        this.strategy = new MinDispenceStrategy();
        converter = Map.of(
                0,Bill.B5,
                1,Bill.B10,
                2,Bill.B20,
                3,Bill.B50,
                4,Bill.B100
        );
    }
    public int watchTotalBalance(){
        return cassete.totalBalance();
    }
    public Map<Bill,Integer> withdraw(int amount){
        Map<Bill,Integer> planMap = strategy.plan(amount,cassete.cells());
        for(Map.Entry<Bill, Integer> bill : planMap.entrySet()){
            cassete.cell(bill.getKey()).withdraw(bill.getValue());
        }
        return planMap;
    }

    public void deposit(int[] bills){
        for(int i = 0;i < bills.length; i++){
            if(converter.size() == bills.length) cassete.deposit(converter.get(i),bills[i]);
            else throw new AtmException("Check your input Data: bills array mast have length like Bill count ");
        }
    }
}
