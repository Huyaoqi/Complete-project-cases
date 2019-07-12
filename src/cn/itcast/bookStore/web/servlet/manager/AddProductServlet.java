package cn.itcast.bookStore.web.servlet.manager;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.exception.AddProductException;
import cn.itcast.bookStore.service.ProductService;
import cn.itcast.bookStore.utils.IdUtils;

/**
 * 后台系统
 * 用于添加商品的servlet
 */
@WebServlet("/addProduct")
public class AddProductServlet extends UploadServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductService service = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String> map = upload(request,response);
		Product pro = new Product();
		pro.setId(IdUtils.getUUID());
		pro.setName(map.get("name"));
		pro.setPrice(Double.parseDouble(map.get("price")));
		pro.setCategory(map.get("category"));
		pro.setPnum(Integer.parseInt(map.get("pnum")));
		pro.setImgurl(map.get("imgurl"));
		pro.setDescription(map.get("description"));
		try {
			service.addProduct(pro);
		} catch (AddProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加商品完后跳转到商品列表页面
		response.sendRedirect(request.getContextPath() + "/listProduct");
	}

}
