package com.expense_tracker;

import jaakarta.persistence;
import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
public class Expense{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Lond id;

    private String name;
    private double amount;
    private String category;
    private boolean isPaid;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}