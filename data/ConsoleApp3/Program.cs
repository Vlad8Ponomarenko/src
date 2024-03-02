using System;
using System.Data;
using System.Data.SQLite;

class DatabaseInitializer
{
    const string connectionString = "Data Source=OnlineWalletDB.sqlite;";

    static void Main()
    {
        DisplayMenu();
    }

    static void DisplayMenu()
    {
        Console.WriteLine("Select an option:");
        Console.WriteLine("1. Display User Wallet Info");
        Console.WriteLine("2. Filter Transactions By User And Amount");
        Console.WriteLine("3. Get Total Wallet Balance");
        Console.WriteLine("4. Exit");
        Console.Write("Enter your choice: ");

        int choice;
        if (int.TryParse(Console.ReadLine(), out choice))
        {
            switch (choice)
            {
                case 1:
                    DisplayUserWalletInfo();
                    break;
                case 2:
                    FilterTransactionsByUserAndAmount();
                    break;
                case 3:
                    GetTotalWalletBalance();
                    break;
                case 4:
                    Console.WriteLine("Exiting...");
                    return;
                default:
                    Console.WriteLine("Invalid choice. Please try again.");
                    break;
            }
        }
        else
        {
            Console.WriteLine("Invalid choice. Please enter a number.");
        }

        Console.WriteLine();
        DisplayMenu();
    }

    static void DisplayUserWalletInfo()
    {
        string query = @"
            SELECT Users.UserID, Users.Username, Wallets.Balance
            FROM Users
            INNER JOIN Wallets ON Users.UserID = Wallets.UserID;
        ";

        ExecuteAndDisplayQueryResults(query);
    }

    static void FilterTransactionsByUserAndAmount()
    {
        Console.Write("Enter user ID: ");
        int userId;
        if (!int.TryParse(Console.ReadLine(), out userId))
        {
            Console.WriteLine("Invalid user ID.");
            return;
        }

        Console.Write("Enter amount threshold: ");
        decimal amountThreshold;
        if (!decimal.TryParse(Console.ReadLine(), out amountThreshold))
        {
            Console.WriteLine("Invalid amount threshold.");
            return;
        }

        string query = @"
            SELECT *
            FROM Transactions
            WHERE SenderWalletID IN (SELECT WalletID FROM Wallets WHERE UserID = @UserID)
                OR ReceiverWalletID IN (SELECT WalletID FROM Wallets WHERE UserID = @UserID)
                AND Amount > @AmountThreshold;
        ";

        SQLiteParameter[] parameters = {
            new SQLiteParameter("@UserID", DbType.Int32) { Value = userId },
            new SQLiteParameter("@AmountThreshold", DbType.Decimal) { Value = amountThreshold }
        };

        ExecuteAndDisplayQueryResults(query, parameters);
    }

    static void GetTotalWalletBalance()
    {
        string query = @"
            SELECT SUM(Balance) AS TotalBalance
            FROM Wallets;
        ";

        ExecuteAndDisplayQueryResults(query);
    }

    static void ExecuteAndDisplayQueryResults(string query, SQLiteParameter[] parameters = null)
    {
        using (SQLiteConnection connection = new SQLiteConnection(connectionString))
        {
            SQLiteCommand command = new SQLiteCommand(query, connection);
            if (parameters != null)
            {
                command.Parameters.AddRange(parameters);
            }
            SQLiteDataAdapter adapter = new SQLiteDataAdapter(command);
            DataTable table = new DataTable();

            try
            {
                connection.Open();
                adapter.Fill(table);

                if (table.Rows.Count == 0)
                {
                    Console.WriteLine("No data found.");
                    return;
                }

                foreach (DataRow row in table.Rows)
                {
                    foreach (DataColumn column in table.Columns)
                    {
                        Console.WriteLine($"{column.ColumnName}: {row[column]}");
                    }
                    Console.WriteLine();
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLite error occurred: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error occurred: " + ex.Message);
            }
        }
    }
}
