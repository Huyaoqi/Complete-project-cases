package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.PageBean;
import cn.itcast.bookStore.service.ProductService;
@WebServlet("/showProductByPage")
//分页显示数据
public class ShowProductByPageServlet extends HttpServlet {

	/**
	 * 图书菜单栏
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		String[] categorys = {"文学","生活","计算机","外语","经管","励志","社科","学术","少儿","艺术","原版","科技","考试","生活百科"};
		String _category = request.getParameter("category");
		String category = "全部商品";
		for(int i = 0;i < categorys.length;i++){
			if(_category != null && categorys[i].equals(_category)){category = _category;}
		}
		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount,category);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;

	}

}
