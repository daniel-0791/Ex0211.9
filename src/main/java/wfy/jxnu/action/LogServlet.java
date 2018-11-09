package wfy.jxnu.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogServlet",urlPatterns = "/log.do")
public class LogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username=request.getParameter("uName");
        String userpwd=request.getParameter("uPWD");

        if ("abc".equals(username) && "111".equals(userpwd)
                || "aef".equals(username) &&  "222".equals(userpwd))
        {
            HttpSession session=request.getSession();


            request.setAttribute("userName",username+"hhhhhh");
            session.setAttribute("userName",username);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ok.jsp");
            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
