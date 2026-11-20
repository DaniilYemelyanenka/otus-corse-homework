import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {
    private Atm atm;


    @BeforeEach
    void setup(){
        atm = new Atm();
    }

    @Test
    void deposit_AddSumAndIncrement(){
        int[] deposit = new int[]{1,3,5,4,2};
        atm.deposit(deposit);
        assertEquals(total(deposit),atm.watchTotalBalance());
    }

    @Test
    void deposit_AddOnlyOneBanknote(){
        int[] deposit = new int[]{1,0,0,0,0};

        atm.deposit(deposit);
        assertEquals(total(deposit),atm.watchTotalBalance());
    }

    @Test
    void deposit_GetExceptionWhenAddNegative(){
        int[] deposit = new int[]{-1,0,0,0,0};

        assertThrows(AtmException.class,() -> atm.deposit(deposit));
    }

    @Test
    void deposit_AddOutOfBounceArray() {
        int[] deposit = new int[]{1,0,0,0,0,0};

        assertThrows(AtmException.class, () -> atm.deposit(deposit));
    }

    @Test
    void withdraw_returnsPlanThatMatchesRequest(){

        int[] deposit = new int[]{1,3,5,4,2};
        atm.deposit(deposit);
        int before = atm.watchTotalBalance();

        Map<Bill,Integer> plan = atm.withdraw(100);

        assertAll(
                () -> assertEquals(Map.of(Bill.B100,1),plan),
                () -> assertEquals(100,planValue(plan),"Sum and request sum must be equals"),
                () -> assertEquals(before - 100,atm.watchTotalBalance())
        );

    }

    @Test
    void withdraw_AnswerWithLessCountOfBanknotes(){
        int[] deposit = new int[]{0,0,0,2,1};
        atm.deposit(deposit);

        assertEquals(Map.of(Bill.B100,1),atm.withdraw(100));
    }

    @Test
    void withdraw_FindingSolutionWhenGreedyFails(){
        int[] deposit = new int[]{0,0,3,1,0};
        atm.deposit(deposit);

        assertEquals(Map.of(Bill.B20,3),atm.withdraw(60));
    }

    @Test
    void withdraw_ImpossibleAmountAtWithdrawThrowsAtmException(){
        atm.deposit(new int[]{1,1,1,1,1});
        int before = atm.watchTotalBalance();

        assertAll(
                () -> assertThrows(AtmException.class,() -> atm.withdraw(3)),
                () -> assertEquals(before,atm.watchTotalBalance())
        );
    }




    private int total(int[] arr){
        Bill[] bill = Bill.values();
        int sum = 0;
        for(int i = 0; i < arr.length;i++) sum += bill[i].value() * arr[i];
        return sum;
    }

    private int planValue(Map<Bill,Integer> plan){
        return plan.entrySet().stream().mapToInt(e -> e.getKey().value() * e.getValue()).sum();
    }
}