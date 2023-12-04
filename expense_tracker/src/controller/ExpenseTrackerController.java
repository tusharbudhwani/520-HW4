package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.ExpenseTrackerModel;
import model.Transaction;
import model.Filter.TransactionFilter;

/**
 * Class for handling the controller of the Expense Tracker App
 */
public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  /**
   * The Controller is applying the Strategy design pattern.
   * This is the has-a relationship with the Strategy class
   * being used in the applyFilter method.
   */
  private TransactionFilter filter;

  /**
   * Constructor for ExpenseTrackerController class.
   * 
   * @param model
   * @param view
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
    // For the MVC architecture pattern, the Observer design pattern is being
    // used to update the View after manipulating the Model.
    this.model.register(this.view);
  }

  /*
   * Setter method for Filter
   */
  public void setFilter(TransactionFilter filter) {
    // Sets the Strategy class being used in the applyFilter method.
    this.filter = filter;
  }

  /**
   * Method for input validation and adding a transaction in the Table
   * Returns true if transaction is added.
   * Returns false if fails inputValidation
   * 
   * @return booleanValue
   */
  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    // Remove direct view update
    // view.update(model);
    return true;
  }

  /**
   * Method for applying filter
   */
  public void applyFilter() {
    // null check for filter
    if (filter != null) {
      // Use the Strategy class to perform the desired filtering
      List<Transaction> transactions = model.getTransactions();
      List<Transaction> filteredTransactions = filter.filter(transactions);
      List<Integer> rowIndexes = new ArrayList<>();
      for (Transaction t : filteredTransactions) {
        int rowIndex = transactions.indexOf(t);
        if (rowIndex != -1) {
          rowIndexes.add(rowIndex);
        }
      }
      model.setMatchedFilterIndices(rowIndexes);
      view.update(model);
    } else {
      JOptionPane.showMessageDialog(view, "No filter applied");
      view.toFront();
    }

  }

  /**
   * Method to undo the transactions
   */
  // for undoing any selected transaction
  public boolean undoTransaction(int rowIndex) {
    if (rowIndex >= 0 && rowIndex < model.getTransactions().size()) {
      Transaction removedTransaction = model.getTransactions().get(rowIndex);
      model.removeTransaction(removedTransaction);
      // comment out
      // view.update(model);
      // The undo was allowed.
      return true;
    }

    // The undo was disallowed.
    return false;
  }
}
