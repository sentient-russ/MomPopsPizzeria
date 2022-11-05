package com.mompopspizzeria;

public class EmployeeModel {
    boolean isEmployee = false;
    boolean isManager = false;
    String employeePassword = "2345";
    String managerPassword = "9876";
    public EmployeeModel(){

    }
    public boolean checkEmployeePassword(String passwordIn){
        boolean result = false;
        if(passwordIn.equals(employeePassword)){
            result = true;
            isEmployee = true;
            isManager = false;
            return result;
        }
        return result;
    }
    public boolean checkManagerPassword(String passwordIn){
        boolean result = false;
        if(passwordIn.equals(managerPassword)){
            result = true;
            isManager = true;
            isEmployee = false;
            return result;
        }
        return result;
    }
}
