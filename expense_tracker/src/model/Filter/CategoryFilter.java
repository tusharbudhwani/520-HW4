package model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import controller.InputValidation;

/**
 * Handles Transaction filtering by category.
 * Category are provided by the user through the input field.
 * Here, validation is performed to check if the category field is in the
 * desired format or not.
 */
public class CategoryFilter implements TransactionFilter {
    private String categoryFilter;

    /**
     * Method for filtering the transactions by category and checking for
     * inputValidation
     */
    public CategoryFilter(String categoryFilter) {
        // Since the CategoryFilter constructor is public,
        // the input validation needs to be performed again.
        if (!InputValidation.isValidCategory(categoryFilter)) {
            throw new IllegalArgumentException("Invalid category filter");
        } else {
            this.categoryFilter = categoryFilter;
        }
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(categoryFilter)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}
