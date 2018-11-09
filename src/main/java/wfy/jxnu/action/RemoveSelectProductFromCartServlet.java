package wfy.jxnu.action;

import wfy.jxnu.dao.BookDao;
import wfy.jxnu.dao.BookDaoImpl;
import wfy.jxnu.mo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RemoveSelectProductFromCartServlet",urlPatterns = "/removeSelectProductFromCart.do")
public class RemoveSelectProductFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] id = request.getParameterValues("id");

        int intID=0;


        Map<Book, Integer> cart = (Map<Book, Integer>) request
                .getSession().getAttribute("cart");

        BookDao service = new BookDaoImpl();
        for (int i = 0; i < id.length; i++) {

            try{
                intID=Integer.parseInt(id[i]);
            }catch (Exception e){
                intID=0;
            }
            try {
                Book p = service.findById(intID);
                cart.remove(p);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

      //  request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath() + "/personal/showCart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
