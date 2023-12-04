# hw3

## Expense Tracker

Expense Tracker is an application that helps you manage all the daily transactions.

# Features

## Undo feature

A user can now undo transactions.

The user needs to select a row from the list of transactions and click on undo to remove the transaction from the transactions list.

## Amount

The user can input amount in the amount input field.

## Category

Users can select category for which they spent the amount.

## Input Validation

The input fields are validated.

The amount can be between 0 to 1000.
The category can only be a string and not a digit or a special character.

## Filtering

### Based on Amount

It is now possible to filter the transactions based on the input amount that the user provides.

### Based on Category

User can filter the transactions based on the category. For filtering based on category, User just needs to mention the category in the input field and click on 'Okay' button.

## Compile

To compile the code from terminal, use the following command:

```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation.

## Java Version

This code is compiled with `openjdk 17.0.7 2023-04-18`. Please update your JDK accordingly if you face any incompatibility issue.
