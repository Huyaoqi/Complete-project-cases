package cn.itcast.bookStore.web.servlet.manager;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.service.ProductService;

/**
 * 后台系统
 * 用于编辑商品信息的servlet
 */
@WebServlet("/editProduct")
public class EditProductServlet extends UploadServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建javaBean,将上传数据封装.
		Product pro = new Product();
		Map<String, String> map = upload(request,response);
		pro.setId(map.get("id"));
		pro.setName(map.get("name"));
		pro.setPrice(Double.parseDouble(map.get("price")));
		pro.setCategory(map.get("category"));
		pro.setPnum(Integer.parseInt(map.get("pnum")));
		pro.setImgurl(map.get("imgurl"));
		pro.setDescription(map.get("description"));
		
		ProductService service = new ProductService();

		// 调用service完成修改商品操作
		service.editProduct(pro);
		response.sendRedirect(request.getContextPath() + "/listProduct");
		
	}

}
