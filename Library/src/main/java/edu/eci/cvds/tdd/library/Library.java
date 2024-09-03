package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (book == null || book.getTittle() == null || book.getTittle().isEmpty() || book.getAuthor() == null || book.getAuthor().isEmpty()
                || book.getIsbn() == null || book.getIsbn().isEmpty()) {
                return false;
            }
            /*if (books.containsKey(book)) {
                //se añade el libro
                books.put(book, books.get(book)+ 1);
            } else {
                //se agrega con una cantidad inicial de 1
                books.put(book, 1);
            }*/
        for(Book b : books.keySet()){
            if (b.equals(book)) {
                books.put(b, books.get(b) + 1);
                return true;
            }
        }
        books.put(book, 1);
        return true;
    }


    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn   book identification.
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        User userFound = null;
        Book bookFound = null;
        boolean notAvailable = false;
        boolean sameLean = false;
        //Verifica que el usuario exista
        for(User u : users){
            String id = u.getId();
            if (id != null && id.equals(userId)) {
                userFound=u;
            }
        }
        //Verifica que el libro exista y que si existe, esté disponible
        for(Book b : books.keySet()){
            String id = b.getIsbn();
            if (id.equals(isbn)) {
                bookFound=b;
                if (books.get(bookFound) == 0){
                    notAvailable = true;
                }
            }
        }
        if (userFound != null && bookFound != null) {
            //Verifica que el prestamo no sea el mismo para el mismo usuario
            for (Loan l : loans) {
                if (l.getStatus() == LoanStatus.ACTIVE && l.getUser().getId().equals(userId) && l.getBook().getIsbn().equals(isbn)) {
                    sameLean = true;
                }
            }
            //Verifica que todos los requerimientos se cumplan
            if (notAvailable || sameLean) {
                return null;
            }
            //Decrementa el libro
            books.put(bookFound, books.get(bookFound) - 1);
            //Crea el prestamo y le asigna el usuario, libro y estado activo
            Loan newLoan = new Loan();
            newLoan.setUser(userFound);
            newLoan.setBook(bookFound);
            newLoan.setStatus(LoanStatus.ACTIVE);
            newLoan.setLoanDate(LocalDateTime.now());
            loans.add(newLoan);
            return newLoan;
        }
        return null;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        boolean exist = false;
        boolean isReturned = false;
        //Verificar que el prestamo existe
        for (Loan l : loans) {
            if (l.equals(loan)) {
                exist = true;
                if (l.getStatus() == LoanStatus.RETURNED) isReturned = true;
            }
        }
        if (exist && !isReturned) {
            Book book = loan.getBook();
            if (books.containsKey(book)) {
                books.put(book, books.get(book) + 1);
                loan.setStatus(LoanStatus.RETURNED);
                loan.setReturnDate(LocalDateTime.now());
                return loan;
            }
        }
        return null;
    }
    public boolean addUser(User user) {
        return users.add(user);
    }
    public Map<Book, Integer> getBooks() {
        return books;
    }
    public List<Loan> getLoans() {
        return loans;
    }
    public boolean addLoan(Loan loan) {
            return loans.add(loan);
    }
}
