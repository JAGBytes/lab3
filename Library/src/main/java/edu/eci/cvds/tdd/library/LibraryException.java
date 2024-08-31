package edu.eci.cvds.tdd.library;

public class LibraryException extends Exception {
  public static final String notUser = "User not found";
  public static final String notBook = "Book not found";
  public LibraryException(String message) {
    super(message);
  }
}
