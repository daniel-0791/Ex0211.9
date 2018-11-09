package wfy.jxnu.action;

import wfy.jxnu.dao.CustomerDao;
import wfy.jxnu.dao.CustomerDaoImpl;
import wfy.jxnu.mo.Customer;
import wfy.jxnu.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UploadHeadImgCutServlet",urlPatterns = "/upload.do")
public class UploadHeadImgCutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //
        UploadUtil util=new UploadUtil(this, request);
        String fosPath="";
        try{
            fosPath="custImages/"+util.processUploadedFile("userlogo", "custImages");
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

        //从session中取出用户名
        HttpSession session=request.getSession();
        Customer customer=( Customer) session.getAttribute("loginer");
//设置新的文件名
        customer.setCustImg(fosPath);
        CustomerDao customerDao=new CustomerDaoImpl();
        boolean flag=false;
        try {

            flag= customerDao.updateCustomerHeadImg(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        PrintWriter out=response.getWriter();

        out.println(request.getContextPath()+"/"+fosPath);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
