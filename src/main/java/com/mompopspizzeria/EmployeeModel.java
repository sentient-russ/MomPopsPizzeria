package com.mompopspizzeria;
/*
 * This class initializes the employee object.  The static passwords for employee and manager are both global variables
 * in the MomPopsPizzeriaMain class for easy location
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class EmployeeModel extends MomPopsPizzeriaMain {
    boolean isEmployee = false;
    boolean isManager = false;

    public EmployeeModel(){

    }
    /*
     * Authentication getter method for the employee password
     */
    public boolean checkEmployeePassword(String passwordIn){
        boolean result = false;
        if(passwordIn.equals(employeeModelPassword)){
            result = true;
            isEmployee = true;
            isManager = false;
            return result;
        }
        return result;
    }
    /*
     * Authentication getter method for the manager password
     */
    public boolean checkManagerPassword(String passwordIn){
        boolean result = false;
        if(passwordIn.equals(managerModelPassword)){
            result = true;
            isManager = true;
            isEmployee = false;
            return result;
        }
        return result;
    }
}
