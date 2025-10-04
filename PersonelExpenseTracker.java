import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class PersonelExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tracking track = new tracking();

        // Load existing data
        ArrayList<String> expenses = track.loadExpenses();
        System.out.println("Loaded " + expenses.size() + " expenses from file.");

        while (true) {
            System.out.println("\n--------MENU--------");
            System.out.println("1. Add expense (amount, date, note)");
            System.out.println("2. View expenses");
            System.out.println("3. Update expense");
            System.out.println("4. Delete expense");
            System.out.println("5. Save to file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = track.getIntInput(sc);

            switch (choice) {
                case 1 -> {
                    String amount = track.getValidAmount(sc);
                    String date = track.getValidDate(sc);

                    System.out.print("Enter the remarks: ");
                    String remarks = sc.nextLine();

                    track.addExpenses(expenses, amount, date, remarks);
                    System.out.println("Expense added successfully!");
                }

                case 2 -> track.viewExpenses(expenses);

                case 3 -> {
                    track.viewExpenses(expenses);
                    if (expenses.isEmpty()) break;

                    System.out.print("Enter the index (1-based) to update: ");
                    int index = track.getIntInput(sc) - 1;

                    if (!track.isValidIndex(expenses, index)) break;

                    String newAmount = track.getValidAmount(sc);
                    String newDate = track.getValidDate(sc);

                    System.out.print("Enter new remarks: ");
                    String newRemarks = sc.nextLine();

                    track.updateExpenses(expenses, index, newAmount, newDate, newRemarks);
                    System.out.println("Expense updated successfully!");
                }

                case 4 -> {
                    track.viewExpenses(expenses);
                    if (expenses.isEmpty()) break;

                    System.out.print("Enter the index (1-based) to delete: ");
                    int index1 = track.getIntInput(sc) - 1;

                    if (!track.isValidIndex(expenses, index1)) break;

                    track.deleteExpenses(expenses, index1);
                    System.out.println("Expense deleted successfully!");
                }

                case 5 -> {
                    track.saveExpenses(expenses);
                    System.out.println("Expenses saved to file successfully!");
                }

                case 6 -> {
                    track.saveExpenses(expenses);
                    System.out.println("Data saved automatically. Exiting safely...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Please enter a valid option!");
            }
        }
    }
}

class tracking {

    private static final String FILE_NAME = "expenses.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Add expense
    void addExpenses(ArrayList<String> expenses, String amount, String date, String remarks) {
        expenses.add("Amount: " + amount + " | Date: " + date + " | Note: " + remarks);
    }

    // View expenses
    void viewExpenses(ArrayList<String> expenses) {
        System.out.println("\n------ Expense List ------");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet!");
            return;
        }
        double total = 0;
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));

            // Extract amount for total sum
            try {
                String part = expenses.get(i).split("\\|")[0].replace("Amount:", "").trim();
                total += Double.parseDouble(part);
            } catch (Exception ignored) {}
        }
        System.out.printf("Total Spent: %.2f\n", total);
    }

    // Update expense
    void updateExpenses(ArrayList<String> expenses, int index, String amount, String date, String remarks) {
        expenses.set(index, "Amount: " + amount + " | Date: " + date + " | Note: " + remarks);
    }

    // Delete expense
    void deleteExpenses(ArrayList<String> expenses, int index1) {
        expenses.remove(index1);
    }

    // Save to file
    void saveExpenses(ArrayList<String> expenses) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String exp : expenses) {
                writer.write(exp + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error while saving to file: " + e.getMessage());
        }
    }

    // Load from file
    ArrayList<String> loadExpenses() {
        ArrayList<String> expenses = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return expenses;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) expenses.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error while loading from file: " + e.getMessage());
        }
        return expenses;
    }

    // Validate numeric amount
    String getValidAmount(Scanner sc) {
        while (true) {
            System.out.print("Enter the amount (positive number): ");
            String input = sc.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) throw new NumberFormatException();
                return String.format("%.2f", value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a positive number.");
            }
        }
    }

    // Validate date (proper format and existing day)
    String getValidDate(Scanner sc) {
        while (true) {
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String input = sc.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMAT);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Date cannot be in the future!");
                    continue;
                }
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please enter a valid date like 2025-10-04.");
            }
        }
    }

    // Validate integer input
    int getIntInput(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number! Please enter again: ");
            }
        }
    }

    // Check if index is valid
    boolean isValidIndex(ArrayList<String> expenses, int index) {
        if (index < 0 || index >= expenses.size()) {
            System.out.println("Invalid index! Please choose a number from the list.");
            return false;
        }
        return true;
    }
}
