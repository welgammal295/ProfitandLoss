package com.welgammal.walid.profitandloss.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.welgammal.walid.profitandloss.Database.ProfitLossDB;

import java.util.Objects;

@Entity(tableName = ProfitLossDB.ELEMENTS_TABLE)
public class Elements {
    @PrimaryKey(autoGenerate = true)
    private int elementID;
    double revenue = 0.0;
    double costOfSale = 0.0;
    double operatingExpenses = 0.0;
    double otherExpenses = 0.0;
    double otherIncomes = 0.0;
    int year = 2024;
    String month = "January";


    public Elements(double revenue, double costOfSale, double operatingExpenses,
                    double otherExpenses, double otherIncomes, int year, String month) {
        this.revenue = revenue;
        this.costOfSale = costOfSale;
        this.operatingExpenses = operatingExpenses;
        this.otherExpenses = otherExpenses;
        this.otherIncomes = otherIncomes;
        this.year = year;
        this.month = month;
    }

    public int getElementID() {
        return elementID;
    }

    public void setElementID(int elementID) {
        this.elementID = elementID;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getCostOfSale() {
        return costOfSale;
    }

    public void setCostOfSale(double costOfSale) {
        this.costOfSale = costOfSale;
    }

    public double getOperatingExpenses() {
        return operatingExpenses;
    }

    public void setOperatingExpenses(double operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public double getOtherIncomes() {
        return otherIncomes;
    }

    public void setOtherIncomes(double otherIncomes) {
        this.otherIncomes = otherIncomes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elements elements = (Elements) o;
        return elementID == elements.elementID && Double.compare(elements.revenue, revenue) == 0 && Double.compare(elements.costOfSale, costOfSale) == 0 && Double.compare(elements.operatingExpenses, operatingExpenses) == 0 && Double.compare(elements.otherExpenses, otherExpenses) == 0 && Double.compare(elements.otherIncomes, otherIncomes) == 0 && year == elements.year && Objects.equals(month, elements.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementID, revenue, costOfSale, operatingExpenses, otherExpenses, otherIncomes, year, month);
    }
}
