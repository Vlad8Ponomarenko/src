import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionAnalyzer {
    private List<Transaction> transactions;

    public TransactionAnalyzer() {
        this.transactions = new ArrayList<>();
    }

    public void readTransactionsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { 
                    Transaction transaction = new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2]);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalBalance() {
        double totalBalance = 0;
        for (Transaction transaction : transactions) {
            totalBalance += transaction.getAmount();
        }
        return totalBalance;
    }

    public int countTransactionsByMonth(String month) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getMonth().equalsIgnoreCase(month)) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        TransactionAnalyzer analyzer = new TransactionAnalyzer();
        analyzer.readTransactionsFromCSV("transactions.csv"); 
        double totalBalance = analyzer.calculateTotalBalance();
        System.out.println("Total Balance: $" + totalBalance);
        int transactionsInJanuary = analyzer.countTransactionsByMonth("January");
        System.out.println("Transactions in January: " + transactionsInJanuary);
    }
}
