package wfy.jxnu.action;

import wfy.jxnu.dao.BookDao;
import wfy.jxnu.dao.BookDaoImpl;
import wfy.jxnu.mo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "AddProductToCartServlet",urlPatterns = "/tocart.do")
public class AddProductToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1.得到商品id
        String id = request.getParameter("id");

        int intID=0;
        try{
            intID=Integer.parseInt(id);
        }catch (Exception e){
            intID=0;
        }
        // 2.根据id查询商品
        BookDao service = new BookDaoImpl();
        try {
            Book p = service.findById(intID);

            // 3.将商品添加到购物车
            HttpSession session = request.getSession();
            // 从session中获取购物车
            Map<Book, Integer> cart = (Map<Book, Integer>) session
                    .getAttribute("cart");
            // 如果cart为null,说明，没有购物车，是第一次购物
            if (cart == null) {
                // 创建出购物车
                cart = new HashMap<Book, Integer>();
            }
            // 判断购物车中是滞有当前要买的商品
            Integer count = cart.get(p);

            if (count == null) {
                // 如果为null,说明购物车中没有这个商品，这时商品的数量就为1
                count = 1;
                System.out.println("1:"+count);
                System.out.println( cart.containsKey(p));
            } else {
                // 如果不为null,说明购物车中有这个商品，这时，就将商品的数量+1
                count += 1;
                System.out.println( cart.containsKey(p));
                System.out.println("2:"+count);
            }
            // 将商品存储到购物车中
            cart.put(p, count);
            // 将购物车存储到session中.
            session.setAttribute("cart", cart);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("添加商品到购物车成功，<a href='index.jsp'>继续购物</a>,<a href='personal/showCart.jsp'>查看购物车</a>");
            return;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
