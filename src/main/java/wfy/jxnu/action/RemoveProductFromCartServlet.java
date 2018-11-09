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
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "RemoveProductFromCartServlet",urlPatterns = "/removeProductFromCart.do")
public class RemoveProductFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 得到要删除的商品的id
        String id = request.getParameter("id");
        int intID = 0;
        try {
            intID = Integer.parseInt(id);
        } catch (Exception e) {
            intID = 0;
        }
        // 得到购物车，从购物车中将商品删除,
        Map<Book, Integer> cart = (Map<Book, Integer>) request
                .getSession().getAttribute("cart");
        BookDao service = new BookDaoImpl();
        try {

            Book p = service.findById(intID);
            cart.remove(p);

            //如果购物车中无商品，将购物车删除。
            if (cart.size() == 0) {
                request.getSession().removeAttribute("cart");
            }

            response.sendRedirect(request.getContextPath() + "/personal/showCart.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
