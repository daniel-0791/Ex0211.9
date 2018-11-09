package wfy.jxnu.action;

import wfy.jxnu.dao.BookDao;
import wfy.jxnu.dao.BookDaoImpl;
import wfy.jxnu.util.PageBean;
import wfy.jxnu.mo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookPageServlet",urlPatterns = "/bookpage.do")
public class BookPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String curpage=request.getParameter("curPage");
        //curPage
        int intPage=0;
        try {
            intPage = Integer.parseInt(curpage);
        }catch (Exception e){
            intPage=1;
        }


        BookDao bookDao=new BookDaoImpl();
        PageBean<Book>  pageBean=new PageBean<Book>();
        List<Book> bookList =null;
        int rowCount=0;
        try {
            rowCount=bookDao.getBookNumber();


            pageBean.setPageSize(2); //注入每页显示的大小
            pageBean.setRowCount(rowCount); //注入记录条数

            //先放大小，再放记录条数
            pageBean.setCurrentPage(intPage);//z注入要显示的页码
            bookList=bookDao.getRecommadation(pageBean.getRowStart(),2);

            pageBean.setPageList(bookList);

            request.setAttribute("pagebean",pageBean);
            request.getRequestDispatcher("/front/recompage.jsp").forward(request,response);


        } catch (SQLException e) {
            e.printStackTrace();
            rowCount=0;
        }
        //总记录条数



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
