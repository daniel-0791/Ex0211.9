package wfy.jxnu.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {
	private static List<FileItem> items;
	private static String basepath;

	public UploadUtil(HttpServlet servlet, HttpServletRequest request) {
		// 创建一个工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = servlet.getServletConfig().getServletContext();

		basepath = servletContext.getRealPath("/");
		basepath = basepath.endsWith("/") ? basepath : basepath + "/";

		System.out.println("basePath=" + basepath);
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理普通文本域，返回值为fieldname的value
	 * @param fieldname
	 * @return
	 */
	public String processFormField(String fieldname) {
		String value = "";
		// 这里采用了枚举类型
		// 返回字段域的值
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				String name = item.getFieldName();
				if (name.equalsIgnoreCase(fieldname)) {
					value = item.getString();
				}
			}
		}
		return value;
	}

	/**处理文件域，path为custImages说明是头像bookImages 图书封面
	 *  返回值为重命名后的文件名
	 * @param fieldname
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String processUploadedFile(String fieldname, String path) throws Exception {
		File uploadedfile = null;
		Iterator<FileItem> iter = items.iterator();
		String fileName="";
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (!item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equalsIgnoreCase(fieldname)) {
					fileName = item.getName();
					fileName = UUID.randomUUID()
							+ fileName.substring(fileName.lastIndexOf("."), fileName.length());// rename
																								// filename
					uploadedfile = new File(basepath + path + "/" + fileName);

					item.write(uploadedfile);
					return fileName;

				}
			}
		}
		return fileName;
	}

}
