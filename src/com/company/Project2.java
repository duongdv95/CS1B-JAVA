package com.company;
//Daniel Duong
//Inheriting Employees

public class Project2 {
    public static void main(String[] args) {
        employee bob = new employee("bob", 345532);
        FTE gary = new FTE("gary", 54345345, 65000);
        contractor jim = new contractor("jim", 4545454, 20);

        employee[] employeeObjects = new employee[3];
        employeeObjects[0] = bob;
        employeeObjects[1] = gary;
        employeeObjects[2] = jim;

        System.out.println(employeeObjects[0].payCheck());
        System.out.println(employeeObjects[1].payCheck());
        System.out.println(employeeObjects[2].payCheck(20));

    }
}

class employee {
    private String name;
    private int socialSecurityNumber;

    public employee(String fullName, int ssn) {
        name = fullName;
        socialSecurityNumber = ssn;
    }

    public String getName() {
        return name;
    }

    public int getSSN() {
        return socialSecurityNumber;
    }

    public String payCheck() {
        String payCheck = "Employee Name: " + name
                + " \nSocial Security Number: " + socialSecurityNumber
                + "\n--------------------------";
        return payCheck;
    }

    public String payCheck(int hoursWorked) {
        String payCheck = "Employee Name: " + this.getName()
                + "\nSocial Security Number: " + this.getSSN()
                + "\nHours worked: " + hoursWorked
                + "\n--------------------------";
        return payCheck;
    }
}

class FTE extends employee{
    private int salary;

    public FTE(String fullName, int ssn, int startSalary) {
        super(fullName, ssn);
        salary = startSalary;
    }

    public String payCheck() {
        String payCheck = "Employee Name: " + this.getName()
                + "\nSocial Security Number: " + this.getSSN()
                + "\nMonthly paycheck: " + salary/12
                + "\n--------------------------";
        return payCheck;
    }
}

class contractor extends employee {
    private int hourlyPayRate;

    public contractor(String fullName, int ssn, int payRate) {
        super(fullName, ssn);
        hourlyPayRate = payRate;
    }

    public String payCheck(int hoursWorked) {
        String payCheck = "Employee Name: " + this.getName()
                + "\nSocial Security Number: " + this.getSSN()
                + "\nTotal Pay: " + hourlyPayRate*hoursWorked
                + "\n--------------------------";
        return payCheck;
    }
}