package edu.eci.cvds.tdd.library;

public class LibraryException extends Exception {
  public static final String notUser = "User not found";
  public static final String notBook = "Book not found";
  public static final String notLoan = "Loan not found";
  public static final String CannotHaveMultipleBooksAtSameTime = "User cannot have multiple books on loan at the same time.";
  public static final String BookNotAvailable = "Book not available";
  public LibraryException(String message) {
    super(message);
  }

}