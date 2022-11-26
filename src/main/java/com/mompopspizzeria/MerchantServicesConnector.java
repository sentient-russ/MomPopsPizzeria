package com.mompopspizzeria;

import java.util.ArrayList;
/*
 *This class temporarily stores the customers credit card processing information in memory.
 *Later implementation to include an actual SSL connection to the Merchant Services API.
 *Prints each transaction the console for confirmation.
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class MerchantServicesConnector<String> {
    ArrayList<String> transactions = new ArrayList<>();
    /*
     *Default constructor
     */
    public MerchantServicesConnector(){
    }
    /*
     * This method is a placeholder that can later be connected to an external merchant services processor
     * @param firstNameIn The first name of the cardholder as a String
     * @param lastNameIn The last name of the cardholder as a String
     * @param cardNumIn The card number as a 16 character String
     * @param cardExpIn The expiration data as a string
     * @param cadCVVIn The CVV as a 3-4 character String
     * @param totalAmountIn The total to be charged as a String
     */
    public boolean merchantServicesConnector(String firstNameIn, String lastNameIn, String cardNumIn, String cardExpIn,String cardCVVIn, String totalAmountIn){
        String proccessString = (String) (firstNameIn + ", " + lastNameIn + ", " + cardNumIn + ", " + cardExpIn + ", " + cardCVVIn + ", " + totalAmountIn);
        System.out.println(proccessString);
        transactions.add(proccessString);
        return true;
    }
}
