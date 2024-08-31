package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.*;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
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
    @Test
    public void VerifyParametersNotFound(){

    }
    @Test
    public void VerifyDecrementAmountOfBook(){


    }
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
    @Test
    public void VerifyCurrentDate(){


    }
    @Test
    public void VerifyCorrectUser(){


    }
    @Test
    public void SendABookFromOtherUser(){


    }
    

}
