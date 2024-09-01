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
        assertTrue(library.addBook(book));
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
    }
    //No deberia pasar, verifica que no sea valido parametros nulos
    @Test
    public void SendNullBook(){
        assertFalse(library.addBook(null));

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
        User user = new User();
        user.setName("jorge");
        user.setId("U711");
        Book book = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        assertNotNull(library.loanABook(user.getId(), book.getIsbn()));
    }
    @Test
    public void LeanABookNotAvailable(){
        library.loanABook(user.getId(), book.getIsbn());
        LibraryException exception = assertThrows(LibraryException.class, () -> {
            library.loanABook(user2.getId(), book.getIsbn());
        });
        assertEquals("Book not available", exception.getMessage());
    }
    @Test
    public void VerifyThatAllBooksOfTheSameIdCanBeLeant(){
        Book book1 = new Book("Brandon Sanderson", "Palabras Radiantes", "R200");
        Book book2 = new Book("Brandon Sanderson", "El camino de los reyes", "R200");
        Book book3 = new Book("Brandon Sanderson", "Juramentada", "R200");
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
        Loan l = library.loanABook("U123","L123");
        Loan l2 = library.returnLoan(l);
        Loan l3 = library.returnLoan(l2);
        assertNull(l3);
    }
    

}
