package wfy.jxnu.action;

import wfy.jxnu.dao.OrderDao;
import wfy.jxnu.dao.OrderDaoImpl;
import wfy.jxnu.dao.OrderItemDao;
import wfy.jxnu.dao.OrderItemDaoImpl;
import wfy.jxnu.mo.Book;
import wfy.jxnu.mo.Customer;
import wfy.jxnu.mo.Order;
import wfy.jxnu.mo.OrderItem;
import wfy.jxnu.service.OrderService;
import wfy.jxnu.util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "AddOrderServlet",urlPatterns = "/addOrder.do")
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1.得到所有请求参数,封装到Order对象中
        Order order = new Order();
        try {
            // 封装了订单的 送货地址,总价.
           order.setReceiverinfo(request.getParameter("receiverinfo"));
           float money= Float.parseFloat(request.getParameter("money"));
           order.setMoney(money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.手动封装一些信息,利用UUID生成订单号
        String id = UUID.randomUUID().toString();
        order.setId(id);// 封装订单的id
        order.setPaystate(0);// 默认值为0,代表未支付。如果为1，代表支付.

        // 封装user_id
        // 从session中获取当前用户.
        Customer user = (Customer) request.getSession().getAttribute("loginer");
        int user_id = user.getCustId();
        order.setUserId(user_id);

        // 将订单中的所有的订单项信息封装。
        List<OrderItem> items = new ArrayList<OrderItem>();
        //得到购物车
        Map<Book, Integer> cart = (Map<Book, Integer>) request
                .getSession().getAttribute("cart");
        //遍历购物车
        for (Book p : cart.keySet()) {
            OrderItem item = new OrderItem(); //创建一个订单项

            item.setOrderId(order.getId()); //向订单项中封装当前订单编号
            item.setBookId(p.getBookId()); //封装订单项中商品id
            item.setBuyNum(cart.get(p));//封装订单项中的商品数量

            items.add(item);
        }

        order.setItems(items);

        OrderService service=new OrderService();
        service.addOrder(order);

        request.getSession().removeAttribute("cart");//生成订单后，将购物车中商品删除。

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("订单生成成功，<a href='"+request.getContextPath()+"/showOrder.do'>查看订单</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
