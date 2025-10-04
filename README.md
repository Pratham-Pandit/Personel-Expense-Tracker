# Personal Expense Tracker

A simple **Personal Expense Tracker** built in Java to help users record, manage, and analyze their daily expenses.  
This project demonstrates basic file handling, data structures, and simple reporting.

---

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/Personal-Expense-Tracker.git

2. Navigate into the project folder:

    bash
        Copy code
        cd Personal-Expense-Tracker/src
    Compile the program:

    bash
        Copy code
        javac ExpenseTracker.java
    Run the program:

    bash
        Copy code
        java ExpenseTracker


## Features

    1. Add expense (amount, date, note)

    2. View expenses

    3. Update expense

    4. Delete expense

    5. Save data (file or database)

## Sample Input and Output

    1. User chooses to enter the expense, date and remark:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 1
            Enter the amount (positive number): 2000
            Enter the date (YYYY-MM-DD): 2025-10-03
            Enter the remarks: fees
        Output:
            Expense added successfully!
    
    2. User chooses to view the expense list:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 2
        
        Output:
            ------ Expense List ------
            1. Amount: 2000.00 | Date: 2025-10-03 | Note: fees
            Total Spent: 2000.00

    3. User chooses to update the expense list:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 3
        Output:
            ------ Expense List ------
            1. Amount: 2000.00 | Date: 2025-10-03 | Note: fees
            Total Spent: 2000.00
            Enter the index (1-based) to update: 1
            Enter the amount (positive number): 2500
            Enter the date (YYYY-MM-DD): 2025-10-02
            Enter new remarks: books
            Expense updated successfully!

    4. User chooses to delete an expense:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 4
        Output:
            ------ Expense List ------
            1. Amount: 2500.00 | Date: 2025-10-02 | Note: books
            Total Spent: 2500.00
            Enter the index (1-based) to delete: 1
            Expense deleted successfully!
    
    5. User chooses to save the expense list to the file:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 5
        Output:
            Expenses saved to file successfully!

    6. User chooses to exit the program:
        Input:
            --------MENU--------
            1. Add expense (amount, date, note)
            2. View expenses
            3. Update expense
            4. Delete expense
            5. Save to file
            6. Exit
            Enter your choice (1-6): 6
        
        Output:
            Data saved automatically. Exiting safely...