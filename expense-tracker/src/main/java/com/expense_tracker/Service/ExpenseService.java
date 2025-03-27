package com.expense_tracker.Service;

import com.expense_tracker.Service.User;
import com.expense_tracker.Repository.ExpenseRepository;
import com.expense_tracker.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userrepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository){
        this.expenseRepository = expenseRepository;
        this.userrepository = userRepository;
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return expenseRepository.findById(id);
    }

    public List<Expense> getExpensesByUserId(Long userId){
        return expenseRepository.findByUserId(userId);
    }

    public Expense createExpense(Long userId, Expense expense){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id).map(expense ->{
            expense.setName(updatedExpense.getName());
            expense.setAmount(updatedExpense.getAmount());
            expense.setCategory(updatedExpense.getCategory());
            expense.setPaid(updatedExpense.isPaid());
            expense.setDueDate(updatedExpense.getDueDate());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new RunTimeException("Expense not found"));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}