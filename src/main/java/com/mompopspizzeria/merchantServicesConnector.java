package com.mompopspizzeria;

import java.util.ArrayList;

/*
 *This class temporarily stores the customers credit card processing information in memory.
 *Later implementation to include an actual SSL connection to the Merchant Services API.
 *Prints each transaction the console for testing as it's entered.
 */
public class merchantServicesConnector <String> {
    ArrayList<String> transactions = new ArrayList<>();


    public merchantServicesConnector(){

    }
    public boolean merchantServicesConnector(String firstNameIn, String lastNameIn, String cardNumIn, String cardExpIn,String cardCVVIn, String totalAmountIn){

        String proccessString = (String) (firstNameIn + ", " + lastNameIn + ", " + cardNumIn + ", " + cardExpIn + ", " + cardCVVIn + ", " + totalAmountIn);

        System.out.println(proccessString);
        transactions.add(proccessString);
        return true;
    }
}
