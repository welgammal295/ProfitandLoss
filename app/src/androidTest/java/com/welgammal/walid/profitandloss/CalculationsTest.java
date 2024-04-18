/** Project 2 - Unit Tests */

package com.welgammal.walid.profitandloss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.welgammal.walid.profitandloss.Calc.Calculations;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;


/**  Android Studio project - Unit Tests */
@RunWith(AndroidJUnit4.class)
public class CalculationsTest {
    /**
     * Setting up values to be used in the upcoming tests.
     */
    Double revenue;
    Double costOfSale;
    Double otherIncome;
    Double opIncome;
    Double taxRate ;
    Calculations calculations = null;
    Random random;
    @Before
    public void setup(){
        System.out.println("Processing Setup");
        System.out.println("=-=-=-=-=-=-=-=-=");
        random = new Random();
        revenue = random.nextDouble();
        costOfSale = random.nextDouble();
        otherIncome = random.nextDouble();
        opIncome = random.nextDouble();
        taxRate = Math.max(2.33, Math.min(9.99, 2.33 + (9.99 - 2.33) * random.nextDouble()));
    }
    @After
    public void tearDown(){
        System.out.println("Processing Teardown");
        System.out.println("=-=-=-=-=-=-=-=-=");
        revenue = null;
        costOfSale = null;
        otherIncome = null;
        opIncome = null;
        taxRate = null;

    }

    @Test
    public void testGrossProfitCalculation() {
        Double grossProfit = revenue - costOfSale;
        System.out.println("Your gross profit is; " + String.format("%.2f",grossProfit));
        // Calculate the gross profit using the method from Calculations.java
        Double calcGrossProfit = Calculations.grossProfit(revenue, costOfSale);
        System.out.println("Your calculated gross profit is; " + String.format("%.2f",grossProfit));
        // Verify that the calculated gross profit matches the expected value
        assertEquals(grossProfit, calcGrossProfit);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.welgammal.walid.profitandloss", appContext.getPackageName());
    }

    @Test
    public void testNetIncomeCalculation() {

        Double NetIncome = ((otherIncome + opIncome) - ((otherIncome + opIncome) * taxRate / 100));

        // Calculate the net income using the method from Calculations.java
        Double calcNetIncome = Calculations.netIncome(otherIncome, opIncome, taxRate);

        // Verify that the calculated net income matches the expected value
        assertEquals(NetIncome, calcNetIncome);
        System.out.print("Calculation is turned correct because the net income of " + String.format("%.2f", NetIncome));
        System.out.println(" is equal to calculated income of " + String.format("%.2f", calcNetIncome));
        // Verify negative income & tax calc
        assertNotEquals(calcNetIncome, costOfSale);
        System.out.print("Your net income " + String.format("%.2f",calcNetIncome));
        System.out.println(" but your cost of sale is " + String.format("%.2f",costOfSale));
    }



    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getCostOfSale() {
        return costOfSale;
    }

    public void setCostOfSale(Double costOfSale) {
        this.costOfSale = costOfSale;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getOpIncome() {
        return opIncome;
    }

    public void setOpIncome(Double opIncome) {
        this.opIncome = opIncome;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
