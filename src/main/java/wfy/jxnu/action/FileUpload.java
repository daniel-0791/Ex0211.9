package wfy.jxnu.action;

import wfy.jxnu.util.UploadUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload23.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UploadUtil util=new UploadUtil(this, request);
		String fosPath="";
		try{
			fosPath="custImages/"+util.processUploadedFile("userlogo", "custImages");
		}catch(Exception e){
			e.printStackTrace();
		
			return;
		}
		
		
		
		String basePath = request.getContextPath()+"/";

	    PrintWriter out=response.getWriter();

		    out.println(basePath+fosPath);
	//    out.println("<script>alert(\"h2\");</script>");
	    System.out.print("path="+basePath+fosPath);
	    
	   
	}

}
