package com.company;
//Daniel Duong
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {
        seatingChart chart = new seatingChart();
        int id;
        int n = 1;
        int seatNumber = 0;
        boolean endProgram;
        String studentName;
        Scanner scanner = new Scanner( System.in );

        do {
            endProgram = false;
            Boolean validOption = false;
            do {
                try {
                    System.out.println("Enter 1 to add student, 2 to move/swap student, 3 to check available seats, or 4 to end program: ");
                    n = scanner.nextInt();
                    validOption = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!");
                    scanner.nextLine();
                }
            } while (!validOption);

            if(n == 1) {
                try {
                    System.out.println("Enter a student's first name and last name separated by a space: ");
                    scanner.nextLine();
                    studentName = scanner.nextLine();
                    String[] parts = studentName.split(" ");
                    String first = parts[0];
                    String last = parts[1];
                    System.out.println("Assign a student ID: ");
                    id = scanner.nextInt();
                    System.out.println("Assign a seat: ");
                    seatNumber = scanner.nextInt();
                    chart.addStudent(first, last, id, seatNumber);
                }  catch (InputMismatchException e) {
                    System.out.println("Error! Please enter numeric seat number/ID.");
                    scanner.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error! Please enter first name and last name separated by a space.");
                }
            } else if (n == 2) {
                Boolean isNumeric = false;
                Boolean studentExists = false;

                System.out.println("Enter a student's first name: ");
                scanner.nextLine();
                studentName = scanner.nextLine();

                for(Student student: chart.seatingAssignment()) {
                    if(student != null && student.getFirst().toLowerCase().equals(studentName.toLowerCase())) {
                        studentName = student.getFirst();
                        studentExists = true;
                        break;
                    }
                }

                if(!studentExists) System.out.println("Student does not exist!");
                if(studentExists) System.out.println("Select a seat: ");

                while(!isNumeric && studentExists) {
                    try {
                        seatNumber = scanner.nextInt();
                        if(seatNumber > 19) {
                            throw new InputMismatchException();
                        }
                        isNumeric = true;
                    } catch(InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a numeric seat between 0 and 19: ");
                        scanner.nextLine();
                    }
                }
                if(isNumeric && studentExists) {
                    chart.swapStudent(studentName, seatNumber);
                }
            } else if (n == 3) {
                System.out.println(chart.toString());
            } else if (n == 4) {
                endProgram = true;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
        while (!endProgram);
    }
}

class Student {
    private String first;
    private String last;
    private int studentID;

    public Student(String fn, String ln, int id) {
        first = fn;
        last = ln;
        studentID = id;
    }

    public String toString() {
        return "First Name: " + first + " Last Name: " + last + " ID: " + studentID;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getID() {
        return studentID;
    }
}

class seatingChart {

    private Student [] seatingAssignment = new Student[20];

    public Student[] seatingAssignment() {
        return seatingAssignment;
    }

    public void addStudent(String first, String last, int id, int seatNum) {
        if((seatNum < 20) && (seatingAssignment[seatNum] == null)) {

            seatingAssignment[seatNum] = new Student(first, last, id);
        } else {
            System.out.println("Seat occupied, please pick from these available seats");
            System.out.println(toString());
        }
    }

    public int findStudent(String first) {
        int seatNum = 0;
        for (int i = 0; i < seatingAssignment.length; i++) {
            if (seatingAssignment[i] != null && first.equals(seatingAssignment[i].getFirst())) {
                seatNum = i;
                break;
            }
        }
        return seatNum;
    }

    public void swapStudent(String first, int targetSeat) {
        Student targetPositionStudent = seatingAssignment[targetSeat];
        int seatNum = findStudent(first);
        seatingAssignment[targetSeat] = seatingAssignment[seatNum];
        seatingAssignment[seatNum] = targetPositionStudent;
    }

    public String toString() {
        int column = 0;
        String availableSeats = "";
        System.out.println("Available Seats:");
        for(int i = 0; i < seatingAssignment.length; i++) {
            String currentSeat = (seatingAssignment[i] == null) ? "Empty" : seatingAssignment[i].getFirst();
            if(i<10) {
                availableSeats += "Seat " + i + ":  (" + currentSeat + ") ";
            } else {
                availableSeats += "Seat " + i + ": (" + currentSeat + ") ";
            }
            column ++;
            if((column%4) == 0) {
                availableSeats += "\n";
            }
        }
        return availableSeats;
    }
}