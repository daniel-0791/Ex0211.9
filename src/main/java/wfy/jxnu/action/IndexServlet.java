package wfy.jxnu.action;

import wfy.jxnu.dao.BookDaoImpl;
import wfy.jxnu.dao.BookDao;
import wfy.jxnu.mo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns = "/index.do")
public class IndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doIndex(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doIndex(request,response);
    }


    protected void doIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookDao bookDao=new BookDaoImpl();
        List<Book> bookList=null;
        List<Book> booksThisWeek=null;
        Book bookThisWeek =null;

        try {
            bookList=bookDao.getRecommadation(7);
            booksThisWeek=bookDao.getRecommThisWeek(1);
            bookThisWeek=booksThisWeek.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("bList",bookList);
        request.setAttribute("bThis",bookThisWeek);
        request.getRequestDispatcher("/front/front.jsp").forward(request,response);
    }



}
