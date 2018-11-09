package wfy.jxnu.dao;

import wfy.jxnu.mo.Book;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void findById(){
        BookDaoImpl bookDao=new BookDaoImpl();

        Book book=null;
        try {
                book=bookDao.findById(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("9787544716376",book.getBookISBN());

    }

    @Test
    public void getRecommadation() {

        BookDaoImpl bookDao=new BookDaoImpl();
        List<Book> bookList=null;
        try {
            bookList=bookDao.getRecommadation(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(bookList.size(),4);
    }


    @Test
    public void getRecommadation2() {

        BookDaoImpl bookDao=new BookDaoImpl();
        List<Book> bookList=null;
        try {
            bookList=bookDao.getRecommadation(4,2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(bookList.size(),2);
        assertEquals(bookList.get(0).getBookId(),5);
        assertEquals(bookList.get(1).getBookId(),6);
    }


    @Test
    public void getbookNumber() {

        BookDaoImpl bookDao=new BookDaoImpl();
        int bookNumber=0;
        try {
            bookNumber=bookDao.getBookNumber();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(bookNumber,7);

    }


}