package com.moneylover.Modules.Budget.Entities;

import com.moneylover.Infrastructure.Models.BaseModel;

import java.util.Date;

public class Budget extends BaseModel {
    private int walletId;

    private int budgetableId;

    private String budgetableType;

    private Date startedAt;

    private Date endedAt;

    private float amount;

    private float spentAmount;

    private String categoryIcon;

    public static String getTable() {
        return "budgets";
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getBudgetableId() {
        return budgetableId;
    }

    public void setBudgetableId(int budgetableId) {
        this.budgetableId = budgetableId;
    }

    public String getBudgetableType() {
        return budgetableType;
    }

    public void setBudgetableType(String budgetableType) {
        this.budgetableType = budgetableType;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(float spentAmount) {
        this.spentAmount = spentAmount;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
}
