package company;

public enum Foods {
    GOOD(3),
    BAD(2),
    CANNOTEAT(1);

    private final int constant;


    private Foods(int constant) {
        this.constant = constant;
    }
}
