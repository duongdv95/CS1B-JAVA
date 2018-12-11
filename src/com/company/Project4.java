package com.company;

// Author: Daniel Duong
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

// Project4 aka foothill class creates interface
public class Project4 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bookstore newBookStore = new bookstore();
        primaryStage.setTitle("Exceptional Bookstore");

        Text bookstore = new Text("Books currently in bookstore");
        bookstore.setFont(new Font(25));

        Text catalog = new Text("NO BOOKS YET");
//        catalog.setFont(new Font(20));

        Text instructions = new Text("Please enter ISBN with only numbers and no dashes and numeric prices.");
        Text status = new Text("Status: Update this area with exceptions");

        Label isbnLabel = new Label("ISBN: ");
        TextField ISBN = new TextField();

        Label titleLabel = new Label("title: ");
        TextField title = new TextField();

        Label lastnameLabel = new Label("last name: ");
        TextField lastName = new TextField();

        Label priceLabel = new Label("Price: ");
        TextField price = new TextField();

        Button saveBtn = new Button("SAVE");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String inputISBN = ISBN.getText();
                    String inputTitle = title.getText();
                    String inputLastName = lastName.getText();
                    String inputPrice = price.getText();
                    book newBook = new book();
                    newBook.setISBN(inputISBN);
                    newBook.setTitle(inputTitle);
                    newBook.setPrice(inputPrice);
                    newBook.setAuthorlastName(inputLastName);

                    if(newBookStore.addBook(newBook)) {
                        System.out.println(newBookStore.print());
                        status.setText("Status: Successfully added book!!!");
                        catalog.setText(newBookStore.print());
                    } else {
                        status.setText("Status: Error! Please check title, last name, or price is formatted correctly.");
                    }


                } catch(InvalidISBN13Exception ex) {
                    System.out.println(ex);
                    status.setText("Status: " + ex + " Try Again.");
                } catch(OutOfRangeException ex) {
                    System.out.println(ex);
                    status.setText("Status: " + ex + " Try Again.");
                } catch (ISBNException ex) {
                    System.out.println(ex);
                    status.setText("Status: " + ex + " Try Again.");
                }

            }
        });

        VBox vBox = new VBox();

        vBox.setMargin(bookstore, new Insets(10, 0, 10, 180));
        vBox.getChildren().addAll(bookstore, catalog, instructions, status);
        vBox.getChildren().addAll(isbnLabel, ISBN, titleLabel, title, lastnameLabel, lastName, priceLabel, price);
        vBox.getChildren().addAll(saveBtn);

        Scene scene = new Scene(vBox, 700, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

class OutOfRangeException extends Exception {
    public OutOfRangeException() {
        super("ISBN has less than 10 digits are greater than 13 digits!");
    }
}

class InvalidISBN13Exception extends Exception {
    public InvalidISBN13Exception() {
        super("First three digits are invalid! Must be 978 or 979");
    }
}

class ISBNException extends Exception {
    public ISBNException() {
        super("ISBN must be numeric or no ISBN was entered!");
    }
}

class book {
    private String ISBN;
    private String title;
    private String authorlastName;
    private String price;

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void setISBN(String newISBN) throws OutOfRangeException, InvalidISBN13Exception, ISBNException {
        if (newISBN.length() == 0 || !isNumeric(newISBN)) {
            throw new ISBNException();
        }
        String checkISBN = newISBN.substring(0,3);

        if (newISBN.length() > 13 || newISBN.length() < 10) {
            throw new OutOfRangeException();
        }
        if (checkISBN.equals("978") || checkISBN.equals("979")) {
            ISBN = newISBN;
        } else {
            throw new InvalidISBN13Exception();
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public String getTitle() {
        return title;
    }

    public void setAuthorlastName(String newlastName) {
        authorlastName = newlastName;
    }
    public String getAuthorlastName() {
        return authorlastName;
    }

    public void setPrice(String newPrice) {
        price = newPrice;
    }
    public String getPrice() {
        return price;
    }
}

class bookstore {
    ArrayList<book> alist = new ArrayList<book>();
    private int greatestTitleCharacterCount = 18;
    private int greatestLastNameCount = 15;

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public boolean addBook(book newBook) {
        if (newBook.getAuthorlastName().length() > 0 && newBook.getTitle().length() > 0 && isNumeric(newBook.getPrice()) && !newBook.getPrice().matches(".*[a-z].*") && !newBook.getPrice().matches(".*[A-Z].*")) {
            alist.add(newBook);
            if (newBook.getTitle().length() > greatestTitleCharacterCount) {
                greatestTitleCharacterCount = newBook.getTitle().length();
            }
            if (newBook.getAuthorlastName().length() > greatestLastNameCount) {
                System.out.println("true");
                greatestLastNameCount = newBook.getAuthorlastName().length();
            }
            return true;
        }
        return false;
    }

    public String print() {
        String spaces = "                                                                                              ";
        String allBooks = "ISBN" + spaces.substring(0,11)+ "TITLE" + spaces.substring(0, greatestTitleCharacterCount - 5) + " " + "LASTNAME" + spaces.substring(0,greatestLastNameCount - 8) + " " + "PRICE\n";
        for(book element: alist) {
            String ISBN = element.getISBN();
            String title = element.getTitle();
            String lastName = element.getAuthorlastName();
            String price = element.getPrice();

            String space1 = spaces.substring(0, 15 - ISBN.length());
            String space2 =  spaces.substring(0, greatestTitleCharacterCount - title.length());
            String space3 = spaces.substring(0, greatestLastNameCount - lastName.length());
            allBooks += ISBN + space1 + title + space2 + " " + lastName + space3 + " " + price + "\n";
        }
        return allBooks;
    }
}

