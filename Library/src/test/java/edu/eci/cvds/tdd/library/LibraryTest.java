package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.*;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.LibraryException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;



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

    }
    //Verifica que el estado del prestamo esté activo
    @Test
    public void LoanStatusActive(){

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
       
    }
    //Verifica que el prestamo coincida con el usuario
    @Test
    public void VerifyCorrectUser(){
        

    }
    //Verifica que el monto del libro se haya incrementado
    @Test
    public void CheckIncreaseLeanBook(){
        

    }
    

}
