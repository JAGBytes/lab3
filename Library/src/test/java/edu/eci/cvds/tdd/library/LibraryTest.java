package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.*;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;


public class LibraryTest {
    Library library;
    Book book;
    User user,user2,user3;
    Loan loan;
    @BeforeEach
    public void setUp(){
        library = new Library();
        book = new Book("Cien anios de soledad","Gabriel Garcia Marquez","L123");
        user = new User();
        user2 = new User();
        user3 = new User();
        loan = new Loan();
        user.setName("Camila");
        user.setId("U123");
        user2.setName("pedro");
        user2.setId("Z323");
        user3.setName("pablito");
        user3.setId("T999");
        library.addUser(user);
        library.addUser(user2);
        library.addUser(user3);
    }
/*ADD A BOOK*/
    //pasa
    @Test
    public void AddanExistingBookSuccessfully(){
        library.addBook(book);
        Book book2 = new Book("Cien anios de soledad","Gabriel Garcia Marquez","L123");
        library.addBook(book2);
        assertEquals(2,library.getBooks().get(book));
    }
    //pasa
    @Test
    public void AddNewBookSuccessfully(){
        Book book1 = new Book("Brandon Sanderson", "El Camino de los Reyes", "C113");
        assertTrue(library.addBook(book1));
    }
    //Nodeberia pasar
    @Test
    public void VerifyThatBooksWithEmptyParametersCannotBeAdded(){
        assertFalse(library.addBook(new Book("El necronomicon","","")));
        assertFalse(library.addBook(new Book("","Abdul Alhazred","")));
        assertFalse(library.addBook(new Book("","","G666")));
        assertFalse(library.addBook(new Book("","","")));
        assertFalse(library.addBook(new Book("El necronomicon","Abdul Alhazred","")));
        assertFalse(library.addBook(new Book("","Abdul Alhazred","L999")));
        assertFalse(library.addBook(new Book("El necronomicon","Abdul Alhazred","")));
        assertFalse(library.addBook(new Book("El necronomicon","","L999")));

    }
    //No deberia pasar, verifica que no sea valido parametros nulos
    @Test
    public void SendNullBook(){
        assertFalse(library.addBook(null));

    }
    //Enviar un libro con atributos nulos
    @Test
    public void sendNullAttributes() {
        Book book2 = new Book(null,"Mario Mendoza","L478");
        assertFalse(library.addBook(book2));
        Book book3 = new Book("Lady Masacre",null,"L478");
        assertFalse(library.addBook(book3));
        Book book4 = new Book("Lady Masacre","Mario Mendoza",null);
        assertFalse(library.addBook(book4));
        Book book5 = new Book(null,null,null);
        assertFalse(library.addBook(book5));
    }


    /*LEAN A BOOK*/

    @Test
    public void AddNewLeanSuccessfully()  {
        User user1 = new User();
        user1.setName("jorge");
        user1.setId("U711");
        Book book1 = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        library.addUser(user1);
        library.addBook(book1);
        assertNotNull(library.loanABook(user1.getId(), book1.getIsbn()));
    }
    @Test
    public void LeanABookNotAvailable(){
        /*library.loanABook(user.getId(), book.getIsbn());
        LibraryException exception = assertThrows(LibraryException.class, () -> {
            library.loanABook(user2.getId(), book.getIsbn());
        });
        assertEquals("Book not available", exception.getMessage());*/
        library.addBook(book);
        Loan loan1 = library.loanABook(user2.getId(), book.getIsbn());
        Loan noLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(noLoan);
    }
    @Test
    public void VerifyThatAllBooksOfTheSameIdCanBeLeant(){
        Book book1 = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        Book book2 = new Book("Brandon Sanderson", "El camino de los reyes", "R200");
        Book book3 = new Book("Brandon Sanderson", "Juramentada", "R200");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        Loan loan1 = library.loanABook(user.getId(),book1.getIsbn());
        Loan loan2 = library.loanABook(user2.getId(), book2.getIsbn());
        Loan loan3 = library.loanABook(user3.getId(), book3.getIsbn());
        assertNotNull(loan3);
        assertNotNull(loan2);
        assertNotNull(loan1);
    }
    @Test
    //Prestar paramateros vacios
    public void nullParemeters(){
        User newUser = new User();
        newUser.setId("U432");
        newUser.setName("Jorge");
        library.addUser(newUser);
        Loan isloan = library.loanABook("U432",null);
        assertNull(isloan);
        Loan isloan2 = library.loanABook(null,null);
        assertNull(isloan2);
        Loan isloan3 = library.loanABook(null,"L123");
        assertNull(isloan3);
    }
    //Añade Usuarios y libros que no existen, no deberia pasar
    @Test
    public void VerifyParametersNotFound(){
        Book newBook = new Book("IT","Stephen King","L432");
        assertNull(library.loanABook("U895","L432"));
        assertNull(library.loanABook("U123","L000"));
    }
    //Verifica que el monto del libro prestado se decremente
    @Test
    public void VerifyDecrementAmountOfBook(){
        User newUser = new User();
        newUser.setId("U432");
        newUser.setName("Jorge");
        Book newBook = new Book("IT","Stephen King","L432");
        library.addBook(newBook);
        library.addUser(newUser);
        assertEquals(1,library.getBooks().get(newBook));
        Loan isloan = library.loanABook("U432","L432");
        assertEquals(0,library.getBooks().get(newBook));

    }
    //Verifica que el estado del prestamo esté activo
    @Test
    public void LoanStatusActive(){
        User newUser = new User();
        newUser.setId("U432");
        newUser.setName("Andres");
        Book newBook = new Book("Harry Potter","J.K Rowling","L895");
        library.addBook(newBook);
        library.addUser(newUser);
        Loan isloan = library.loanABook("U432","L895");
        assertEquals(isloan.getStatus(), LoanStatus.ACTIVE);
    }
    @Test
    public void UserCannotLoanSameBookTwiceAtSameTime() {
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        /*LibraryException exception = assertThrows(LibraryException.class, () -> {
            library.loanABook(user.getId(), book.getIsbn());
        });
        assertEquals("Loan not found", exception.getMessage());*/
        assertNull(library.loanABook(user.getId(), book.getIsbn()));
    }
    @Test
    public void verifySameLoan(){
        User u = new User();
        u.setId("U432");
        u.setName("Andres");
        Book b = new Book("Harry Potter","J.K Rowling","L895");
        library.addBook(b);
        library.addUser(u);
        Loan l = library.loanABook(u.getId(),b.getIsbn());
        assertEquals(LoanStatus.ACTIVE,l.getStatus());
        assertEquals(u.getId(),l.getUser().getId());
        assertEquals(b.getIsbn(),l.getBook().getIsbn());
        Loan l2 = library.loanABook(u.getId(),b.getIsbn());
        assertNull(l2);
    }
    @Test
    public void differentUserButSameLoanAndBook(){
        User u = new User();
        u.setId("U432");
        u.setName("Andres");
        User u2 = new User();
        u2.setId("U888");
        u2.setName("Andrea");
        Book b = new Book("Harry Potter","J.K Rowling","L895");
        library.addBook(b);
        library.addBook(b);
        library.addUser(u);
        library.addUser(u2);
        Loan l = library.loanABook(u.getId(),b.getIsbn());
        Loan l2 = library.loanABook(u2.getId(),b.getIsbn());
        assertNotNull(l2);
    }
    @Test
    public void differentBookButSameLoanAndUser(){
        User u = new User();
        u.setId("U432");
        u.setName("Andres");
        Book b = new Book("Harry Potter","J.K Rowling","L895");
        Book b2 = new Book("Los juegos del hambre","Suzzane Collings","L888");
        library.addBook(b);
        library.addUser(u);
        library.addBook(b2);
        Loan l = library.loanABook(u.getId(),b.getIsbn());
        Loan l2 = library.loanABook(u.getId(),b2.getIsbn());
        assertNotNull(l2);
    }
    @Test
    public void userWithNullId(){
        User u = new User();
        u.setId(null);
        u.setName("Andres");
        User u2 = new User();
        u2.setId("U777");
        u2.setName("Juan");
        library.addUser(u);
        library.addUser(u2);
        library.addBook(book);
        Loan l = library.loanABook(u2.getId(), book.getIsbn());
        assertNotNull(l);
    }
    @Test
    public void availableButSameLean(){
        User u = new User();
        u.setId("U555");
        u.setName("Andres");
        library.addUser(u);
        library.addBook(book);
        library.addBook(book);
        Loan l = library.loanABook(u.getId(),book.getIsbn());
        assertEquals(1,library.getBooks().get(book));
        Loan l2 = library.loanABook(u.getId(),book.getIsbn());
        assertNull(l2);
    }


/*RETURN LOAN*/

    @Test
    public void VerifyReturnedStatusLoan(){
        Book book1 = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        library.addBook(book1);
        library.addBook(book);
        Loan loan1 = library.loanABook(user.getId(),book1.getIsbn());
        assertEquals(LoanStatus.ACTIVE,loan1.getStatus());
        Loan loan2 = library.loanABook(user.getId(),book.getIsbn());
        assertEquals(LoanStatus.ACTIVE,loan2.getStatus());
        library.returnLoan(loan1);
        assertEquals(LoanStatus.RETURNED,loan1.getStatus());
    }
    @Test
    public void VerifyReturnedBookIncrement(){
        Book book1 = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        Book book2 = new Book("Brandon Sanderson", "El camino de los reyes", "R200");
        Book book3 = new Book("Brandon Sanderson", "Juramentada", "R200");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        Loan loan1 = library.loanABook(user.getId(),book1.getIsbn());
        Loan loan2 = library.loanABook(user2.getId(), book2.getIsbn());
        Loan loan3 = library.loanABook(user3.getId(), book3.getIsbn());
        assertEquals(0,library.getBooks().get(book1));
        library.returnLoan(loan1);
        assertEquals(1,library.getBooks().get(book1));
        library.returnLoan(loan2);
        assertEquals(2,library.getBooks().get(book1));
        library.returnLoan(loan3);
        assertEquals(3,library.getBooks().get(book1));
    }
    @Test
    public void LoanDoesNotExist(){
        loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);
        assertNull(library.returnLoan(loan));

    }
    //Verifica que la fecha que se devolvió el prestamos coincida con la fecha actual
    @Test
    public void VerifyCurrentDate(){
        User newUser = new User();
        newUser.setId("U534");
        newUser.setName("Juan");
        Book newBook = new Book("Cumbres Borrascosas","Emily Bronte","L989");
        library.addBook(newBook);
        library.addUser(newUser);
        Loan isloan = library.loanABook("U534","L989");
        Loan returnedLoan = library.returnLoan(isloan);
        assertEquals(returnedLoan.getReturnDate(), LocalDateTime.now());

    }
    //Verifica que el prestamo coincida con el usuario
    @Test
    public void VerifyCorrectUser(){
        User newUser = new User();
        newUser.setId("U534");
        newUser.setName("Juan");
        Book newBook = new Book("Cumbres Borrascosas","Emily Bronte","L989");
        library.addBook(newBook);
        library.addUser(newUser);
        Loan isloan = library.loanABook("U534","L989");
        Loan returnedLoan = library.returnLoan(isloan);
        assertEquals(returnedLoan.getUser(), newUser);

    }
    //Verifica que el monto del libro se haya incrementado
    @Test
    public void CheckIncreaseLeanBook(){
        User newUser = new User();
        newUser.setId("U436");
        newUser.setName("Esteban");
        Book newBook = new Book("Frankenstein","Mary Shelly","L448");
        library.addBook(newBook);
        library.addUser(newUser);
        Loan isloan = library.loanABook("U436","L448");
        assertEquals(library.getBooks().get(isloan.getBook()), 0);
        Loan returnedLoan = library.returnLoan(isloan);
        Book leanBook = returnedLoan.getBook();
        assertEquals(library.getBooks().get(leanBook), 1);
    }
    //Verifica que un libro prestado que acabó de devolverse, pueda volver a prestarse
    @Test
    public void LendABookThatWasReturned(){
        User newUser = new User();
        newUser.setId("U634");
        newUser.setName("Camilo");
        library.addUser(newUser);
        library.addBook(book);
        Loan l = library.loanABook("U123","L123");
        Loan l2 = library.returnLoan(l);
        Loan l3 = library.loanABook(newUser.getId(), l2.getBook().getIsbn());
        assertNotNull(l3);
    }
    //Verificar que el prestamo que se va a devolver este en estado ACTIVE antes de ser devuelto
    @Test
    public void LendABookThatWasNotReturned(){
        User newUser = new User();
        newUser.setId("U634");
        newUser.setName("Maria");
        library.addUser(newUser);
        library.addBook(book);
        Loan l = library.loanABook("U123","L123");
        Loan l2 = library.returnLoan(l);
        Loan l3 = library.returnLoan(l2);
        assertNull(l3);
    }
    @Test
    public void sendNullLoan(){
        assertNull(library.returnLoan(null));
    }
    @Test
    public void getTheCorrectNumberOfLoans() {
        library.addBook(book);
        library.loanABook(user.getId(), book.getIsbn());
        assertEquals(1,library.getLoans().size());
    }
    @Test
    public void addingLoadCorrectly(){
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        assertTrue(library.addLoan(loan));
        assertEquals(library.getLoans().size(),1);
    }
    @Test
    public void CorrectLoanButNotExistBook(){
        Loan l = new Loan();
        l.setBook(book);
        l.setUser(user);
        l.setStatus(LoanStatus.ACTIVE);
        library.addLoan(l);
        Loan l2 = library.returnLoan(l);
        assertNull(l2);
    }

}
