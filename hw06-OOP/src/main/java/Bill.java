public enum Bill {
    B5(5),B10(10),B20(20),B50(50),B100(100);

    private final int value;

    Bill(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
