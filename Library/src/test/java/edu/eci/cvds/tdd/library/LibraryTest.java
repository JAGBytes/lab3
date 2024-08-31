package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.*;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.LibraryException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LibraryTest {
    Library library;
    Book book;
    User user;
    Loan loan;
    @BeforeEach
    public void setUp(){
        library = new Library();
        book = new Book("Cien anios de soledad","Gabriel Garcia Marquez","L123");
        user = new User();
        loan = new Loan();
        user.setName("Camila");
        user.setId("U123");
        library.addUser(user);
    }
/*ADD A BOOK*/
    //pasa
    @Test
    public void AddanExistingBookSuccessfully(){

    }
    //pasa
    @Test
    public void AddNewBookSuccessfully(){

    }
    //Nodeberia pasar
    @Test
    public void ParameterWithOtherObject(){

    }
    //No deberia pasar, verifica que no sea valido parametros nulos
    @Test
    public void SendNullBook(){
        assertFalse(!library.addBook(null));

    }

    //Pendiente
    //No deberia PASAR
    /*@Test
    public void VerifyBookWithDiferentAuthor(){

    }
    //No deberia pasar
    @Test
    public void VerifyBookWithDiferentId(){

    }*/

/*LEAN A BOOK*/

    @Test
    public void AddNewLeanSuccessfully(){

    }
    @Test
    public void LeanABookNotAvailable(){


    }
    @Test
    public void VerifyBookWithDiferentNameButSameAuthor(){


    }
    //Añade Usuarios y libros que no existen, no deberia pasar
    //Debería lanzar una excepcion que indique que el usuario o libro no se encuentra
    @Test
    public void VerifyParametersNotFound(){
        Book newBook = new Book("IT","Stephen King","L432");
        //Enviar un usuario que no existe
        LibraryException exception = assertThrows(LibraryException.class, () -> {
            library.loanABook("U895","L432");
        });
        assertEquals("User not found", exception.getMessage());
        //Enviar un libro que no existe
        LibraryException exception2 = assertThrows(LibraryException.class, () -> {
            library.loanABook("U123","L000");
        });
        assertEquals("Book not found", exception2.getMessage());
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

/*RETURN LOAN*/

    @Test
    public void VerifyReturnedStatusLoan(){


    }
    @Test
    public void VerifyReturnedBookIncrement(){

    }
    @Test
    public void LoanDoesNotExist(){


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
        assertEquals(returnedLoan.getReturnDate(), LocalDate.now());
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
        Loan returnedLoan = library.returnLoan(isloan);
        Book leanBook = returnedLoan.getBook();
        assertEquals(library.getBooks().get(leanBook), 1);

    }
    

}
