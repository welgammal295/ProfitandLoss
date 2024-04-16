package com.welgammal.walid.profitandloss.Calc;

import com.welgammal.walid.profitandloss.MainActivity;

public class Calculations extends MainActivity{
    /** Calculate Monthly and Yearly
     * Gross Profit: grossProfit = opeRevenue – costSales
     * */
    //TODO: We need to have the admin to adjust the tax rate
    //static double taxRate = 8.85;
    public static double grossProfit(double rev, double costSales){

        return  rev - costSales;
    }
    /** Calculate Total Operating Expenses
     * Operating Expenses: opex  is the overhead paid for this month
     * (rent, salaries, advertising, equipment…etc.)
     * Default this value and only allow admin to change it.
     * */
    public static double totalOperatingExpenses(double opex, double otherExp){
            return opex + otherExp;
    }
    /** Calculate  Operating Income */
    public static double operatingIncome(double grossProfit, double opExpenses) {
        return grossProfit - opExpenses;
    }
    public static double netIncome(double otherIncome, double opIncome, double taxRate){

        return  ((otherIncome + opIncome) - ((otherIncome + opIncome) * taxRate / 100));
    }


}
