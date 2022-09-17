public class Client {
    private String name;
    private double sum;
    private OperationType operationType;
    private Integer cash;

    public Client(String name, double sum, OperationType operationType, int cash) {
        this.name = name;
        this.sum = sum;
        this.operationType = operationType;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Integer getCash() {
        return cash;
    }
}
