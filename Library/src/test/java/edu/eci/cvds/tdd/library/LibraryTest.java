package edu.eci.cvds.tdd.library;


import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.book.Book;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book;
    private Loan loan;
    private User user;
    @BeforeEach
    public void setUp(){
        library = new Library();
        book = new Book("Cien anios de soledad", "Gabriel Garcia Marquez", "L123");
        loan = new Loan();
        user = new User();
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);
        user.setId("U123");
        user.setName("Camila");
    }
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
    /*
    *Try to add a null book
    * This should be false
     */
    @Test
    public void ParameterWithNullObject() {
        assertTrue(!library.addBook(null));
    }
    /*
     *Try to add a null book
     * This should be false
     */
    @Test
    public void VerifyIncrementsOfBook() {


    }
//Lean a Book
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
//Return Loan
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
