package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseTrackerModel {

  // encapsulation - data integrity
  private List<Transaction> transactions;
  private List<Integer> matchedFilterIndices;

  // This is applying the Observer design pattern.
  // Specifically, this is the Observable class.

  /**
   * Model of the Expense Tracker App
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<Transaction>();
    matchedFilterIndices = new ArrayList<Integer>();
  }

  /**
   * Method for adding transaction
   * 
   * @param t
   */
  public void addTransaction(Transaction t) {
    // Perform input validation to guarantee that all transactions added are
    // non-null.
    if (t == null) {
      throw new IllegalArgumentException("The new transaction must be non-null.");
    }
    transactions.add(t);

    stateChanged();

    // The previous filter is no longer valid.
    matchedFilterIndices.clear();
  }

  /**
   * Method to remove transaction
   * 
   * @param t
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
    // The previous filter is no longer valid.
    matchedFilterIndices.clear();

    stateChanged();
  }

  /**
   * Method to return an Immutable arrayList.
   * 
   * @return list
   */
  public List<Transaction> getTransactions() {
    // encapsulation - data integrity
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

  public void setMatchedFilterIndices(List<Integer> newMatchedFilterIndices) {
    // Perform input validation
    if (newMatchedFilterIndices == null) {
      throw new IllegalArgumentException("The matched filter indices list must be non-null.");
    }
    for (Integer matchedFilterIndex : newMatchedFilterIndices) {
      if ((matchedFilterIndex < 0) || (matchedFilterIndex > this.transactions.size() - 1)) {
        throw new IllegalArgumentException(
            "Each matched filter index must be between 0 (inclusive) and the number of transactions (exclusive).");
      }
    }
    // For encapsulation, copy in the input list
    this.matchedFilterIndices.clear();
    this.matchedFilterIndices.addAll(newMatchedFilterIndices);
  }

  public List<Integer> getMatchedFilterIndices() {
    // For encapsulation, copy out the output list
    List<Integer> copyOfMatchedFilterIndices = new ArrayList<Integer>();
    copyOfMatchedFilterIndices.addAll(this.matchedFilterIndices);
    return copyOfMatchedFilterIndices;
  }

  /**
   * Registers the given ExpenseTrackerModelListener for
   * state change events.
   *
   * @param listener The ExpenseTrackerModelListener to be registered
   * @return If the listener is non-null and not already registered,
   *         returns true. If not, returns false.
   */
  // Store all the registered observers
  private List<ExpenseTrackerModelListener> listeners = new ArrayList<>();

  /**
   * 
   * @param listener
   * @return
   */
  public boolean register(ExpenseTrackerModelListener listener) {
    // For the Observable class, this is one of the methods.
    //
    // TODO

    if (listener == null || listeners.contains(listener)) {
      return false;
    }
    listeners.add(listener);
    return true;

    // return false;
  }

  public int numberOfListeners() {
    // For testing, this is one of the methods.
    //
    // TODO

    return listeners.size();

    // return 0;
  }

  public boolean containsListener(ExpenseTrackerModelListener listener) {
    // For testing, this is one of the methods.
    //
    // TODO

    return listeners.contains(listener);
    // return false;
  }

  protected void stateChanged() {
    // For the Observable class, this is one of the methods.
    // make the model update the view
    // TODO

    for (ExpenseTrackerModelListener listener : listeners) {
      listener.update(this);
    }
  }
}
