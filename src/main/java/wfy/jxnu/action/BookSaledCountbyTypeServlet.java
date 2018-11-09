package wfy.jxnu.action;

import net.sf.json.JSONArray;
import wfy.jxnu.dao.BookTypeDao;
import wfy.jxnu.dao.BookTypeDaoImpl;
import wfy.jxnu.mo.BookType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BookSaledCountbyTypeServlet",urlPatterns = "/booktypecount.do")
public class BookSaledCountbyTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookTypeDao bookTypeDao=new BookTypeDaoImpl();
        List<BookType> bookTypeList=null;
        try {
            bookTypeList=bookTypeDao.countSaledNumbyType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out =response.getWriter();

        JSONArray jsoArray= JSONArray.fromObject(bookTypeList);
        System.out.println(jsoArray.toString());
        out.print(jsoArray.toString());
    }
}
