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

@WebServlet(name = "ChangeCountServlet", urlPatterns = "/changeCount.do")
public class ChangeCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1.得到请求参数
        String id = request.getParameter("id");

        int intID = 0;
        try {
            intID = Integer.parseInt(id);
        } catch (Exception e) {
            intID = 0;
        }
        int count = 1;
        try {
            count = Integer.parseInt(request.getParameter("count"));
        } catch (Exception e) {
            count = 0;
        }

// 2.根据id查询商品
        BookDao service = new BookDaoImpl();
        try {
            Book p = service.findById(intID);
            // 3.修改购物车中指定商品的数量.
            // 3.1得到购物车
            Map<Book, Integer> cart = (Map<Book, Integer>) request
                    .getSession().getAttribute("cart");

            // 3.2修改购物车中商品数量

            if (count == 0) {
                // 删除商品
                cart.remove(p);
                System.out.println("removeP");
            } else {
                cart.put(p, count);
            }

            System.out.println("remove");
          //  request.getSession().setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/personal/showCart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
