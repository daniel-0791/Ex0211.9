package wfy.jxnu.action;

import wfy.jxnu.dao.CustomerDao;
import wfy.jxnu.dao.CustomerDaoImpl;
import wfy.jxnu.mo.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String  userName=request.getParameter("name");
        String  userPWd=request.getParameter("pwd");

        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=null;
        try {
            customer = customerDao.getCustomer(userName,userPWd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer!=null)
        {
            HttpSession session=request.getSession();
            session.setAttribute("loginer",customer);
            response.sendRedirect(request.getContextPath()+"/index.do");
        }else{
            request.setAttribute("loginName",userName);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/front/logerror.jsp");
            dispatcher.forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("/front/logerror.jsp");
        dispatcher.forward(request,response);
    }
}
