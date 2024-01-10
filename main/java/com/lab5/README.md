The Banking System simulates a basic banking application with functionalities to create accounts, deposit funds, withdraw funds, and transfer money between accounts.


Main Class
Main
Contains the main method to demonstrate account creation, fund transfer, and summary display functionalities.

BankAccount Class
BankAccount
Represents a bank account with attributes such as account number, account name, and balance. Provides functionalities to deposit, withdraw, and fetch account details.

Bank Class
Bank
Manages the collection of bank accounts and provides functionalities to create accounts, find accounts by account number, and transfer money between accounts.

Exceptions
InsufficientFundsException
Thrown when a withdrawal or transfer operation is attempted with insufficient funds.

NegativeAmountException
Thrown when attempting a transaction with a negative amount.

AccountNotFoundException
Thrown when attempting to access an account that doesn't exist.

Usage
This banking system allows users to perform the following operations:

Create bank accounts with an initial deposit.
Find accounts using account numbers.
Deposit funds into accounts.
Withdraw funds from accounts.
Transfer funds between accounts.

How to Use
Account Creation:

Use the createAccount method in the Bank class to create new accounts.
Account Transactions:

Use the withdraw, deposit, or transferMoney methods in the Bank class to perform transactions between accounts.
Exception Handling:

Handle exceptions such as InsufficientFundsException, NegativeAmountException, or AccountNotFoundException when performing transactions.