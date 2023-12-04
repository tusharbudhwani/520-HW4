package model;

import controller.InputValidation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class that handles all the transactions in the Expense Tracker App
 */
public class Transaction {

  public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

  // final means that the variable cannot be changed
  private final double amount;
  private final String category;
  private final String timestamp;

  /**
   * Constructor for the Transaction class
   * 
   * @param amount
   * @param category
   */
  public Transaction(double amount, String category) {
    // Since this is a public constructor, perform input validation
    // to guarantee that the amount and category are both valid
    if (InputValidation.isValidAmount(amount) == false) {
      throw new IllegalArgumentException("The amount is not valid.");
    }
    if (InputValidation.isValidCategory(category) == false) {
      throw new IllegalArgumentException("The category is not valid.");
    }

    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * Getter method for amount
   * 
   * @return amount
   */
  public double getAmount() {
    return amount;
  }

  // setter method is removed because we want to make the Transaction immutable
  // public void setAmount(double amount) {
  // this.amount = amount;
  // }

  /**
   * Getter method for category
   * 
   * @return category
   */
  public String getCategory() {
    return category;
  }

  // public void setCategory(String category) {
  // this.category = category;
  // }

  /**
   * Getter method to generate timestamp
   * 
   * @return timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Method to generate current timestamp
   * 
   * @return timestamp
   */
  // private helper method to generate timestamp
  private String generateTimestamp() {
    return dateFormatter.format(new Date());
  }

}
