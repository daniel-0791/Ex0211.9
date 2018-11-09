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
import java.sql.SQLException;

@WebServlet(name = "UploadHeadImgServlet",urlPatterns ="/uploadheadimg.do" )
public class UploadHeadImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中取出用户名
        HttpSession  session=request.getSession();
        Customer  customer=( Customer) session.getAttribute("loginer");


        //将用户上传的图片保存到服务器端
        UploadUtil  uploadUtil=new UploadUtil(this,  request);
        String newFileName="";
        try {
            newFileName=uploadUtil.processUploadedFile("img","custImages");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置新的文件名
        customer.setCustImg("custImages/"+newFileName);
        CustomerDao  customerDao=new CustomerDaoImpl();
        boolean flag=false;
        try {

            flag= customerDao.updateCustomerHeadImg(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("loginer",customer);
        response.sendRedirect(request.getContextPath()+"personal/uploadImg.jsp");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
