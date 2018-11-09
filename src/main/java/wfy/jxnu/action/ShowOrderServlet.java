package wfy.jxnu.action;

import wfy.jxnu.dao.OrderDao;
import wfy.jxnu.dao.OrderDaoImpl;
import wfy.jxnu.mo.Customer;
import wfy.jxnu.mo.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShowOrderServlet",urlPatterns = "/showOrder.do")
public class ShowOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1.得到当前用户
        Customer user = (Customer) request.getSession().getAttribute("loginer");

        // 2.调用OrderDao完成查询订单操作
        OrderDao service = new OrderDaoImpl();
        try {
            List<Order> orders = service.findOrder(user);

            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/personal/showOrder.jsp").forward(request,
                    response);
            return;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
