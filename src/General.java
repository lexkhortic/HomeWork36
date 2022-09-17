import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class General {

    public static int countCashRegisters = 3;
    public static ArrayList<CashRegister> cashRegisters = new ArrayList<>();
    public static ArrayList<Client> clients = new ArrayList<>();
    public static ExecutorService executor = Executors.newFixedThreadPool(countCashRegisters);

    public static void main(String[] args) {

        Client client1 = new Client("Егор", 5000, OperationType.GET_MONEY, 100);
        Client client2 = new Client("Натали", 2000,OperationType.PUT_MONEY, 100);
        Client client3 = new Client("Серега", 7000, OperationType.PAY_MONEY, 100);
        Client client4 = new Client("Настя", 500, OperationType.TRANSACTION_MONEY, 100);
        Client client5 = new Client("Лёша", 1500, OperationType.EXCHANGE_MONEY, 100);

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        for (int i = 0; i < countCashRegisters; i++) {
            cashRegisters.add(new CashRegister("Касса №" + (i + 1), 0));
        }

        while (clients.size() > 0) {
            try {
                startWork();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int sumAll = cashRegisters
                .stream()
                .peek((el -> System.out.println(el.getNumber() + " сумма по операциям: " + el.getSumCash() + "$.")))
                .mapToInt(CashRegister::getSumCash).sum();

        System.out.println("Общая сумма по операциям касс: " + sumAll + "$.");
    }

    public static void startWork() {
        Iterator<Client> iterator = clients.iterator();
        cashRegisters.forEach(el -> {
            if (!clients.isEmpty()) {
                el.setClient(iterator.next());
                executor.execute(el::selectionOperation);
                iterator.remove();
            } else {
                executor.shutdown();
            }
        });
    }
}
